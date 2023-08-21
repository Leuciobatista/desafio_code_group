# Desafio Técnico CodeGroup

## Introdução

Esta aplicação tem como objetivo gerenciar projetos, membros e pessoas. Através desta plataforma, é possível criar, editar, listar e remover projetos, associar membros a projetos, gerenciar pessoas e muito mais.

## Configuração

### Requisitos Prévios

- Java 17.
- Um sistema de gerenciamento de banco de dados PostgreSQL.

### Passos para Configuração

1. Certifique-se de ter o Java instalado e configurado corretamente.
2. Configure o banco de dados utilizando o seguinte script para criação das tabelas

```
-- ----------------------------------------------------- 
-- Table Pessoa 
-- ----------------------------------------------------- 
CREATE TABLE pessoa 
( id bigserial NOT NULL, 
nome character varying(100) NOT NULL, 
datanascimento date, 
cpf character varying(14), 
funcionario boolean, 
CONSTRAINT pk_pessoa PRIMARY KEY (id)); 
-- ----------------------------------------------------- 
-- Table Projeto 
-- ----------------------------------------------------- 
CREATE TABLE projeto ( 
id bigserial NOT NULL, 
nome VARCHAR(200) NOT NULL, 
data_inicio DATE , 
data_previsao_fim DATE , 
data_fim DATE , 
descricao VARCHAR(5000) , 
status VARCHAR(45) , 
orcamento FLOAT , 
risco VARCHAR(45) , 
idgerente bigserial NOT NULL, 
CONSTRAINT pk_projeto PRIMARY KEY (id), 
CONSTRAINT fk_gerente FOREIGN KEY (idgerente) 
REFERENCES pessoa (id) MATCH SIMPLE 
ON UPDATE NO ACTION ON DELETE NO ACTION) 
-- ----------------------------------------------------- 
-- Table Membros 
-- ----------------------------------------------------- 
CREATE TABLE membros 
( idprojeto bigserial NOT NULL, 
idpessoa bigint NOT NULL, 
CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto), 
CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa) 
REFERENCES projeto (id) MATCH SIMPLE 
ON UPDATE NO ACTION ON DELETE NO ACTION, 
CONSTRAINT fk_pessoa FOREIGN KEY (idpessoa) 
REFERENCES pessoa (id) MATCH SIMPLE 
ON UPDATE NO ACTION ON DELETE NO ACTION); 
```

3. Clone o repositório da aplicação.
```
https://github.com/LeonardoMendoncaDev/desafio-tecnico-code-group
```

4. Execute o comando de inicialização.
```
./mvnw spring-boot:run
```
## Uso

Uma vez que a aplicação esteja configurada e em execução, você pode começar a criar projetos, adicionar membros e gerenciar pessoas. A interface do usuário ou os endpoints da API permitirão estas ações.
Dentro da aplicação você pode encontrar o arquivo **api.http** que contém as chamadas para os endpoints encontrados na aplicação.
Outra opção é utilizar o Swagger para fazer as chamadas. Após subir a aplicação acesse:
```
http://localhost:8080/swagger-ui/index.html
```

## Entidades e seus Relacionamentos

- **Pessoa**: Representa uma pessoa individual, com atributos como nome, idade, etc.
- **Projeto**: Representa um projeto, contendo informações como nome, descrição, risco e status.
- **Membro**: Representa um membro associado a um projeto.
- **MembroProjeto**: Representa a associação entre um membro e um projeto.

## Endpoints

Aqui estão alguns dos principais endpoints da API:

- `GET /projetos`: Recuperar todos os projetos
- `GET /projetos/:id`: Recuperar um projeto por id
- `POST /projetos`: Criar um projeto
- `PUT /projetos/:id`: Atualizar um projeto 
- `DELETE /projetos/:id`: Excluir um projeto 
- `GET /projetos/:id/risco`: Obter o risco de um projeto por id 
- `POST /projetos/:id/membros`: Adicionar um membro 
- `POST /membros`: Recuperar todas as pessoas

## Considerações Adicionais

- Certifique-se de manter o banco de dados seguro e realizar backups regularmente.
- Evite compartilhar informações sensíveis através da API.
