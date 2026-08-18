package com.example.dio.strategy;

import com.example.dio.model.Endereco;

public interface EnderecoStrategy {
    Endereco buscarPorCep(String cep);
}
