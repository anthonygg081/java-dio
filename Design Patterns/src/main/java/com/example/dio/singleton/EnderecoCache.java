package com.example.dio.singleton;

import com.example.dio.model.Endereco;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EnderecoCache {

    private final Map<String, Endereco> cache = new ConcurrentHashMap<>();

    public Endereco get(String cep) {
        return cache.get(cep);
    }

    public void put(String cep, Endereco endereco) {
        cache.put(cep, endereco);
    }

    public boolean contains(String cep) {
        return cache.containsKey(cep);
    }
}
