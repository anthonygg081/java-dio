# API REST com Padrões de Projeto em Spring

Este projeto foi desenvolvido para consolidar o uso de padrões de projeto em uma API REST com Spring Boot.

## Padrões aplicados

- Singleton: `EnderecoCache`
- Strategy: `EnderecoStrategy` e `ViaCepStrategy`
- Facade: `ClienteFacade`

## Tecnologias

- Java 17
- Spring Boot 3.3.3
- Spring Web
- Spring Data JPA
- H2 Database

## Como executar

```bash
mvn spring-boot:run
```

## Endpoints principais

### Clientes

- `GET /clientes`
- `GET /clientes/{id}`
- `POST /clientes`
- `PUT /clientes/{id}`
- `DELETE /clientes/{id}`

### Exemplo de payload

```json
{
  "nome": "Ana Souza",
  "endereco": {
    "cep": "01001000"
  }
}
```

A aplicação busca o endereço no ViaCEP e salva em cache e banco local.
