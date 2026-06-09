# 🌱 TerraByte – Backend Java Spring Boot
## Global Solution - 2026

## 👥 Dev do projeto
#### NOME: Emanuelly Ventura Do Nascimento

RM562339 - 2TDSPJ

#### NOME: Carolina Nascimento Gonçalves

RM564786 - 2TDSPJ

#### NOME: Julia Sayuri Kina

RM564555 - 2TDSPJ

# 📌 Descrição do Projeto

O TerraByte é um sistema agrícula desenvolvido com Java e Spring boot com o objetivo de auxiliar agricultores no processo de tomada de decisão sobre plantios, utilizando informações de clima, localização geográfica e características do solo para determinar a compatibilidade entre um terreno agrícola e uma cultura específica. Permitindo o cadastro de usuários, terrenos agrícolas e o armazenamento do histórico das análises realizadas.

---

# 🏗️ Solução do Projeto

A solução foi construída utilizando Spring Boot, arquitetura REST API e Spring Data JPA para gerenciamento da API, permitindo comunicação com aplicações frontend web ou mobile e persistência de dados.

## 📂 A estrutura do projeto
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

# 🚀 Link do Deploy

* https://terrabyte-api-4sxj.onrender.com

---

# 📚 Documentação da API

* https://terrabyte-api-4sxj.onrender.com/swagger-ui/index.html

---

# 🎥 Vídeo de apresentação

* LINK VIDEO

---

# 🔗 Endpoints

## • 🔐 AUTH
### ➕ POST
**Login**

https://terrabyte-api-4sxj.onrender.com/auth/login

Recebe um email e uma senha, valida e se correto retorna um Token que deve ser colocado no "Authorize"

* Email: sandra@gmail.com
* Senha: sandraReg123

---

**Refresh**

https://terrabyte-api-4sxj.onrender.com/auth/refresh

Renova o Access Token usando um Refresh Token. 

```bash
{
  "refreshToken": *Refresh Token*
}
```

---

## • 🔹 USUARIO
### 🔍 GET
**Infos**

https://terrabyte-api-4sxj.onrender.com/api/usuario/infos

Retorna as informações pessoais do usuário logado

---

**FetchById**

https://terrabyte-api-4sxj.onrender.com/api/usuario/{*id*}

Retorna um usuário a partir do ID

* ID: 978c415d-7c8b-4b37-af9a-d54fcb1bda46

---

**FetchAll**

https://terrabyte-api-4sxj.onrender.com/api/usuario/listar

Retorna todos os usuários do sistema

---

**testCache**

https://terrabyte-api-4sxj.onrender.com/api/usuario/test-cache

Executa um teste de cache e retorna a quantidade de cadastros, pelo tempo que foi executado

---

### ➕ POST
**Create**

https://terrabyte-api-4sxj.onrender.com/api/usuario

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

### ✏️ PATCH
**Update**

https://terrabyte-api-4sxj.onrender.com/api/usuario/{*id*}

Altera telefone, senha e(ou) url da foto do usuário

```bash
{
  "telefone": "11945414013",
  "senha": "sandraReg1234",
  "urlImg": "https://exemplo.com/NovaFotoSandra.png"
}
```

* ID: 978c415d-7c8b-4b37-af9a-d54fcb1bda46

---

### 🗑️ DELETE
**DeleteById**

https://terrabyte-api-4sxj.onrender.com/api/usuario{*id*}

Apaga um usuário pelo seu id, efetuando o logout automaticamente

* Id: 978c415d-7c8b-4b37-af9a-d54fcb1bda46

---

## • 🔹 TIPO-SOLO
### 🔍 GET
**FetchById**

https://terrabyte-api-4sxj.onrender.com/api/tipoSolo/{*id*}

Retorna um tipo de solo pelo ID

* ID: 0022136f-bacf-45d5-837a-a5d0cf7b7394

---
**FetchAll**

https://terrabyte-api-4sxj.onrender.com/api/tipoSolo/listar

Retorna todos os tipos de solo cadastrados

---
**testCache**

https://terrabyte-api-4sxj.onrender.com/api/tipoSolo/test-cache

Executa um teste de cache e retorna a quantidade de cadastros, pelo tempo que foi executado

---
## • 🔹 PLANTIO
### 🔍 GET
**FetchById**

https://terrabyte-api-4sxj.onrender.com/api/plantio/{*id*}

Retorna um plantio pelo ID

ID: 84485c05-3790-4c10-a47e-7032fc4a88ad

---
**FetchAll**

https://terrabyte-api-4sxj.onrender.com/api/plantio/listar

Retorna todos os plantios cadastrados

---
**FetchByTipoSolo**

https://terrabyte-api-4sxj.onrender.com/api/plantio/solo/{*idTipoSolo*}

Retorna os plantios que tem aquele tipo de solo em especifico

* ID: 0022136f-bacf-45d5-837a-a5d0cf7b7394

---
***FetchByDefensivo**

https://terrabyte-api-4sxj.onrender.com/api/tipoDefensivo/{idDefensivo}

Retorna os plantios que tem aquele defensivo ideal em especifico

---
**testCache**

https://terrabyte-api-4sxj.onrender.com/api/plantio/test-cache

Executa um teste de cache e retorna a quantidade de cadastros, pelo tempo que foi executado

---
## • 🔹 ENDEREÇO
### 🔍 GET
**FetchById**

https://terrabyte-api-4sxj.onrender.com/api/endereco/{*id*}

Retorna um endereço pelo ID

* ID: 75cf458a-d881-4baf-8e42-6db6bc7ad4ee

---
**FetchAll**

https://terrabyte-api-4sxj.onrender.com/api/endereco/listar

Retorna todos os endereços cadastrados do usuário

---

**testCache**

https://terrabyte-api-4sxj.onrender.com/api/endereco/test-cache

Executa um teste de cache e retorna a quantidade de cadastros, pelo tempo que foi executado

---

### ✏️ PATCH
**update**

https://terrabyte-api-4sxj.onrender.com/api/endereco/{*id*}

Altera o nome de um endereço cadastrado a partir de um ID

* ID: 75cf458a-d881-4baf-8e42-6db6bc7ad4ee

```bash
{
  "nome": "Sitio Primavera Reformado"
}
```

---
### 🗑️ DELETE
**DeleteById**

https://terrabyte-api-4sxj.onrender.com/api/endereco/{*id*}

Deleta um endereço a partir de um ID

* ID: 75cf458a-d881-4baf-8e42-6db6bc7ad4ee

---
### ➕ POST
**Create**

https://terrabyte-api-4sxj.onrender.com/api/endereco

Cria um endereço no sistema

```bash
{
  "nome": "Fazendinha do rancho",
  "cep": "69940-000"
}
```

---
## • 🔹 DEFENSIVO
### 🔍 GET
**FetchAllById**

https://terrabyte-api-4sxj.onrender.com/api/defensivo/{*id*}

Retorna um defensivo pelo ID

* ID: 7f0f35a4-4d1c-45f5-a2f8-3e9f1ab00004

---
**FetchAll**

https://terrabyte-api-4sxj.onrender.com/api/defensivo/listar

Retorna todos os defensivos cadastrados

---
**FetchByTipo**

https://terrabyte-api-4sxj.onrender.com/api/defensivo/{*nome-tipo*}

Retorna defensivos que tem um tipo especifico

* nomeTipo: herbicida

---
**testCache**

https://terrabyte-api-4sxj.onrender.com/api/defensivo/test-cache

Executa um teste de cache e retorna a quantidade de cadastros, pelo tempo que foi executado

---
## • 🔹 ANALISE
### 🔍 GET
**FetchById**

https://terrabyte-api-4sxj.onrender.com/api/analise/{*id*}

Retorna uma analise pelo ID

* ID: 87b5c5d5-f3b4-4ee5-9f3d-c2996d0d505e

---
**FetchAll**

https://terrabyte-api-4sxj.onrender.com/api/analise/listar

Retorna todas as analises realizadas por um usuario

---
**testCache**

https://terrabyte-api-4sxj.onrender.com/api/analise/test-cache

Executa um teste de cache e retorna a quantidade de cadastros, pelo tempo que foi executado

---
## ➕ POST
**Create**

https://terrabyte-api-4sxj.onrender.com/api/analise

Cria uma analise

* IdEndereco: 97abc9f5-ec88-44c3-85a8-a678c9d23973
* IdPlantio: 4fc06c06-e576-4b00-8c55-56d6d82d5979
---

# 🚀 Instrução de execução
## Pré-requisitos

Instalar:

* Java 21 ou superior
* Maven 3.8+
* IntelliJ IDEA
* Git

## Clonar o repositório

```bash
git clone https://github.com/carolnascgoncalves/TerraByte-Java.git
cd TerraByte-Java
```

## Execução do projeto

### ▶️ Pela IDE

Abra o projeto no IntelliJ IDEA

Instale a extensão "Lombok"

Aguarde a instalação das dependências Maven

Executar a classe "BackendApplication.java"

### ▶️ Por Api na WEB

Os serviços estão disponíveis em: 

* API Base Url:

    https://terrabyte-api-4sxj.onrender.com

* Swagger:

    https://terrabyte-api-4sxj.onrender.com/swagger-ui/index.html


## Autenticação

Para acesso dos endpoints protegidos, faça login em:

* POST /auth/login

Copie o accessToken

No Swagger, clique em Authorize e cole


---

# ⚙️ Funcionalidades

## 👤 Usuario

* Cadastro de usuários no sistema
* Autenticação via JWT
* Atualização de dados pessoais
* Remoção de conta
* Consulta de informações do usuário logado

---

## 🌱 Endereço (Terrenos)
* Cadastro de propriedades rurais
* Atualização do nome do terreno
* Listagem de propriedades vinculadas ao usuário
* Exclusão de terrenos

---

## 🌾 Plantios
* Listagem de culturas agrícolas disponíveis
* Busca de plantios por tipo de solo
* Consulta de defensivos ideais para cada cultura
* Integração com análises agrícolas

---

## 🧪 Análises agrícolas
* Criação de análises entre:
    terreno
    plantio
    tipo de solo
* Avaliação de compatibilidade agrícola
* Histórico de análises por usuário

---

## 🌍 Tipos de solo
* Listagem de tipos de solo cadastrados
* Consulta por ID
* Base para cálculo de compatibilidade agrícola

---

## 🧴 Defensivos agrícolas
* Listagem de defensivos
* Consulta por tipo (herbicida, inseticida etc.)
* Associação com culturas agrícolas

---

# ⚡ Tecnologias Utilizadas

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