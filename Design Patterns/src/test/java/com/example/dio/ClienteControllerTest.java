package com.example.dio;

import com.example.dio.facade.ClienteFacade;
import com.example.dio.model.Cliente;
import com.example.dio.model.Endereco;
import com.example.dio.repository.ClienteRepository;
import com.example.dio.repository.EnderecoRepository;
import com.example.dio.singleton.EnderecoCache;
import com.example.dio.strategy.EnderecoStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({ClienteFacade.class, EnderecoCache.class})
class ClienteControllerTest {

    @Autowired
    private ClienteFacade clienteFacade;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        EnderecoStrategy enderecoStrategy() {
            return cep -> {
                Endereco endereco = new Endereco();
                endereco.setCep(cep);
                endereco.setLogradouro("Praça da Sé");
                endereco.setComplemento("");
                endereco.setBairro("Sé");
                endereco.setLocalidade("São Paulo");
                endereco.setUf("SP");
                endereco.setIbge("3550308");
                endereco.setGia("");
                endereco.setDdd("11");
                endereco.setSiafi("7107");
                return endereco;
            };
        }
    }

    @Test
    void deveSalvarClienteComEnderecoConsultado() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Souza");

        Endereco endereco = new Endereco();
        endereco.setCep("01001000");
        cliente.setEndereco(endereco);

        Cliente salvo = clienteFacade.salvar(cliente);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getEndereco()).isNotNull();
        assertThat(salvo.getEndereco().getCep()).isEqualTo("01001000");
        assertThat(enderecoRepository.findById("01001000")).isPresent();
        assertThat(clienteRepository.findAll()).hasSize(1);
    }

    @Test
    void deveBuscarClientePorId() {
        Endereco endereco = new Endereco();
        endereco.setCep("01001000");
        endereco.setLogradouro("Praça da Sé");
        endereco.setBairro("Sé");
        endereco.setLocalidade("São Paulo");
        endereco.setUf("SP");
        enderecoRepository.save(endereco);

        Cliente cliente = new Cliente();
        cliente.setNome("Ana Silva");
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);

        Cliente encontrado = clienteFacade.buscarPorId(cliente.getId()).orElseThrow();

        assertThat(encontrado.getNome()).isEqualTo("Ana Silva");
        assertThat(encontrado.getEndereco().getCep()).isEqualTo("01001000");
    }
}
