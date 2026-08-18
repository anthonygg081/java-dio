package com.example.dio.strategy;

import com.example.dio.model.Endereco;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ViaCepStrategy implements EnderecoStrategy {

    private final RestClient restClient;

    public ViaCepStrategy() {
        this.restClient = RestClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }

    @Override
    public Endereco buscarPorCep(String cep) {
        return restClient.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .body(Endereco.class);
    }
}
