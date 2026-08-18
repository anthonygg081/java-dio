package com.example.dio.facade;

import com.example.dio.model.Cliente;
import com.example.dio.model.Endereco;
import com.example.dio.repository.ClienteRepository;
import com.example.dio.repository.EnderecoRepository;
import com.example.dio.singleton.EnderecoCache;
import com.example.dio.strategy.EnderecoStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteFacade {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoStrategy enderecoStrategy;
    private final EnderecoCache enderecoCache;

    public ClienteFacade(ClienteRepository clienteRepository,
                         EnderecoRepository enderecoRepository,
                         EnderecoStrategy enderecoStrategy,
                         EnderecoCache enderecoCache) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoStrategy = enderecoStrategy;
        this.enderecoCache = enderecoCache;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        Endereco endereco = resolverEndereco(cliente.getEndereco().getCep());
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Endereco endereco = resolverEndereco(clienteAtualizado.getEndereco().getCep());
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEndereco(endereco);

        return clienteRepository.save(clienteExistente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private Endereco resolverEndereco(String cep) {
        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("CEP obrigatório");
        }

        if (enderecoCache.contains(cep)) {
            return enderecoCache.get(cep);
        }

        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(cep);
        if (enderecoSalvo.isPresent()) {
            enderecoCache.put(cep, enderecoSalvo.get());
            return enderecoSalvo.get();
        }

        Endereco enderecoConsultado = enderecoStrategy.buscarPorCep(cep);
        if (enderecoConsultado == null || enderecoConsultado.getLogradouro() == null) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado");
        }

        Endereco enderecoPersistido = enderecoRepository.save(enderecoConsultado);
        enderecoCache.put(cep, enderecoPersistido);
        return enderecoPersistido;
    }
}
