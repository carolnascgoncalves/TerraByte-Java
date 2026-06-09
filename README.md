# 🌱 TerraByte – Backend Java Spring Boot
## Global Solution - 2026

## 🗣️ Dev do projeto
#### NOME: Emanuelly Ventura Do Nascimento

RM562339 - 2TDSPJ

#### NOME: Carolina Nascimento Gonçalves

RM564786 - 2TDSPJ

#### NOME: Julia Sayuri Kina

RM564555 - 2TDSPJ

# Descrição do Projeto

O TerraByte é um sistema agrícula desenvolvido com Java e Spring boot com o objetivo de auxiliar agricultores no processo de tomada de decisão sobre plantios, utilizando informações de clima, localização geográfica e características do solo para determinar a compatibilidade entre um terreno agrícola e uma cultura específica. Permitindo o cadastro de usuários, terrenos agrícolas e o armazenamento do histórico das análises realizadas.

---

# Solução do Projeto

A solução foi construída utilizando Spring Boot, arquitetura REST API e Spring Data JPA para gerenciamento da API, permitindo comunicação com aplicações frontend web ou mobile e persistência de dados.

## A estrutura do projeto
Essa separação melhora a manutenção, reutilização e organização do código.

```bash
src/main/java
├── anticorruptionlayer
│   ├── interfaces
│   └── services (API)
│
├── datasource
│   └── repositories
│
├── domainmodel
│   ├── entities
│   ├── enums
│   └── services
│
├── infrastructure
│   ├── config
│   ├── Dataloader
│   └── GlobalExceptionValidationHandler
│
├── resources
│   ├── dtos
│   └── resources
│
├── services
│   ├── interfaces
│   └── implementações
│
└── BackendApplication.java
```

---

# Link do Deploy

* link do deploy q vem do render

---

# Documentação da API

* link do swagger

# Endpoints

## • AUTH
### POST
**Login**

/auth/login

Recebe um email e uma senha, valida e se correto devolva um Token

* Email: sandra@gmail.com
* Senha: sandraReg123

---

**Refresh**

auth/refresh

Renova o Access Token usando um Refresh Token.

```bash
{
  "refreshToken": *Refresh Token*
}
```

---

## • USUARIO
### GET
**Infos**

/api/usuario/infos

Retorna as informações pessoais do usuário logado

---

**FetchById**

/api/usuario/{*id*}

Retorna um usuário a partir do ID

---

**FetchAll**

/api/usuario/listar

Retorna todos os usuários do sistema

**testCache**

/api/usuario/test-cache

Executa um teste de cache e retorna a quantidade de cadastros, pelo tempo que foi executado

---

### POST
**Create**

/api/usuario

Cria um usuário no sistema

```bash
{
  "nome": "Carolina Nascimento",
  "dataNascimento": "2006-11-20",
  "telefone": "11947163194",
  "sexo": "F",
  "email": "carolrsc@gmail.com",
  "senha": "carolNasc123"
}
```

---

### PATCH
**Update**

/api/usuario/{*id*}

Altera telefone, senha e(ou) url da foto do usuário

```bash
{
  "telefone": "11945414013",
  "senha": "sandraReg1234",
  "urlImg": "https://exemplo.com/NovaFotoSandra.png"
}
```

---

### DELETE
**DeleteById**

/api/usuario{*id*}

Apaga um usuário pelo seu id, efetuando o logout automaticamente

* Id: 978c415d-7c8b-4b37-af9a-d54fcb1bda46

---

# Instrução de execução

---

# Funcionalidades

---

# Tecnologias Utilizadas

## ☕ Backend

* Java 21;
* Spring Boot 3.5;
* Spring Web;
* Spring Security;
* Spring Data JPA;
* Hibernate;
* Maven.

---

## 🗄️ Banco de Dados

* H2 Database

---

## 🔧 Ferramentas

* IntelliJ IDEA;
* Postman;
* Swagger/OpenAPI;
* Git;
* GitHub;
* Docker.

---

## 🔐 Segurança
* Spring Security;
* JWT (JSON Web Token).

---

## 📄 Documentação da API
* OpenAPI 3;
* Swagger UI (SpringDoc OpenAPI).

---

## ✅ Validação de Dados
* Bean Validation;
* Hibernate Validator.

---

## ⚡ Cache
* Spring Cache.

---



bash
https://github.com/carolnascgoncalves/TerraByte-Java.git
