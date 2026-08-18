# Projeto de API Inteligente com Spring Boot e Spring AI

Este repositório reúne o material do track de aprendizado da DIO e o projeto final da trilha de Spring AI, focado em uma API para processamento de comandos de voz relacionados a transações financeiras.

A ideia principal é demonstrar a integração entre:

- Spring Boot
- Spring AI
- OpenAI / modelos de linguagem
- Transcrição de áudio
- Tool Calling
- Geração de voz a partir da resposta final
- Persistência com JPA e MySQL

---

## Visão geral

O projeto implementa uma solução em camadas que permite:

1. Receber um arquivo de áudio via API REST.
2. Converter áudio em texto usando transcrição.
3. Interpretar a intenção do usuário com IA.
4. Executar operações reais sobre transações financeiras.
5. Consultar ou criar registros persistidos.
6. Retornar uma resposta em áudio para o cliente.

O fluxo principal é:

```text
Áudio -> Transcrição -> IA -> Tool Calling -> Persistência/Consulta -> Resposta em voz
```

---

## Objetivo do projeto

Criar uma API inteligente para processamento de comandos financeiros em linguagem natural, com suporte a voz. Exemplos de comandos esperados:

- "Gastei 45 reais no mercado hoje"
- "Paguei 80 reais no restaurante"
- "Mostre minhas despesas de alimentação"
- "Cadastre uma compra de 120 reais em farmácia"

---

## Estrutura do repositório

```text
.
├── 00-domain-driven-design/
├── 01-spring-web/
├── 02-spring-data/
├── 03-spring-security/
├── 04-spring-cloud-openfeign/
├── 05-spring-ai/
│   ├── src/
│   ├── build.gradle
│   ├── compose.yml
│   ├── gradlew
│   ├── settings.gradle
│   └── README.md
├── .gitignore
├── README.md
└── .gitattributes
```

O módulo principal da aplicação inteligente está em:

- [05-spring-ai](05-spring-ai/README.md)

---

## Stack tecnológica

- Java 24/25
- Spring Boot 3.x / 4.x
- Spring AI
- Spring Web
- Spring Data JPA
- MySQL
- OpenAI API
- Gradle

---

## Requisitos

Antes de executar o projeto, você precisará de:

- JDK 24 ou superior
- Gradle
- Docker
- Conta e chave da OpenAI
- MySQL em execução localmente ou via Docker Compose

---

## Configuração do ambiente

1. Clone o repositório
2. Acesse a pasta do projeto final:

```bash
cd 05-spring-ai
```

3. Configure a chave da API OpenAI:

```bash
export OPENAI_API_KEY="sua_chave_aqui"
```

No Windows PowerShell:

```powershell
$env:OPENAI_API_KEY="sua_chave_aqui"
```

4. Suba o banco MySQL:

```bash
docker compose up -d
```

---

## Como executar

Na pasta do módulo:

```bash
./gradlew bootRun
```

Para rodar os testes:

```bash
./gradlew test
```

---

## Endpoints principais

A API expõe rotas REST para:

- criação de transações
- consulta de transações
- upload de áudio
- processamento com IA
- retorno de resposta em texto ou áudio

---

## Fluxo da aplicação

1. O cliente envia um áudio.
2. A aplicação faz a transcrição com `TranscriptionModel`.
3. O texto é interpretado pelo `ChatClient`.
4. A IA chama ferramentas reais da aplicação.
5. A lógica executa a operação financeira.
6. A resposta final pode ser convertida em voz com `TextToSpeechModel`.

---

## Observações

Este é um projeto educacional que combina arquitetura, APIs REST e integração com IA, seguindo boas práticas de organização por camadas.

O objetivo principal é entender como usar Spring AI de forma disciplinada em uma aplicação realista, sem perder a separação entre domínio, casos de uso e infraestrutura.

---

## Autor

Projeto baseado no track de aprendizado da Digital Innovation One (DIO), adaptado para o cenário de API inteligente com processamento de voz e IA.

---

## Próximos passos

- configurar o repositório como privado no GitHub
- criar o remote do projeto pessoal
- fazer o push para o GitHub
- documentar endpoints e exemplos de uso em um arquivo adicional de documentação
