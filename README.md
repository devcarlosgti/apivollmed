# Voll.med API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36)

## Sobre o projeto

A **Voll.med API** é uma aplicação Back-End desenvolvida para o gerenciamento de uma clínica médica. A API disponibiliza funcionalidades para cadastro e gerenciamento de médicos e pacientes, agendamento de consultas e autenticação de usuários.

O projeto foi desenvolvido utilizando Java e Spring Boot, aplicando conceitos de desenvolvimento de APIs REST, persistência de dados, validação, segurança e organização das regras de negócio.

## Funcionalidades

- Cadastro, listagem, detalhamento, atualização e desativação de médicos.
- Listagem paginada de médicos ativos.
- Cadastro, listagem, atualização e desativação de pacientes.
- Listagem paginada de pacientes ativos.
- Agendamento de consultas.
- Validações de agendamento, incluindo disponibilidade de médicos e pacientes, horário de funcionamento da clínica e antecedência mínima.
- Autenticação de usuários com geração de token JWT.
- Proteção de endpoints com Spring Security.
- Tratamento centralizado de erros.
- Versionamento do banco de dados com Flyway.
- Documentação da API com Springdoc OpenAPI / Swagger.

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security
- JWT (java-jwt)
- Bean Validation
- MySQL
- Flyway
- Springdoc OpenAPI / Swagger UI
- Maven
- Lombok

## Estrutura do projeto

- `controller`: endpoints HTTP da aplicação.
- `domain`: entidades, DTOs, repositórios e regras de negócio.
- `infra/security`: configurações de segurança, filtros e geração de tokens JWT.
- `infra/exception`: tratamento de exceções.
- `infra/springdoc`: configuração da documentação da API.

## Como executar o projeto

### Pré-requisitos

- JDK 17
- MySQL

O projeto inclui Maven Wrapper, portanto não é necessário instalar o Maven separadamente.

### 1. Clone o repositório

```bash
git clone https://github.com/devcarlosgti/apivollmed.git
cd apivollmed
```

### 2. Configure o banco de dados

Crie um banco de dados MySQL chamado `vollmed_api`.

Configure a URL, o usuário e a senha do banco no arquivo:

`src/main/resources/application.properties`

Por segurança, utilize suas próprias credenciais e não publique senhas reais no GitHub.

Configure também a variável de ambiente `JWT_SECRET` com um segredo forte para assinatura dos tokens.

### 3. Execute a aplicação

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

As migrações do banco de dados são gerenciadas pelo Fly
