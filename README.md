<h1>Gerenciamento de Projetos e Membros - Backend (Java & Spring)</h1>

API backend para o sistema de gerenciamento de Projetos e membros construída usando Java 17, Spring Boot e PostgreSQL.

<h2>Requisitos Técnicos</h2>

Java 17<br>
Spring Boot<br>
PostgreSQL<br>
Recursos da API<br>

<h2>Endpoints</h2>

POST /projetos: Cria um novo projeto.<br>
GET /projetos: Retorna todos os projetos.<br>
GET /projetos/{id}: Retorna detalhes de um projeto específico.<br>
PUT /projetos/{id}: Atualiza um projeto existente.<br>
DELETE /projetos/{id}: Exclui um projeto.<br>
GET /projetos/{id}/risco: Obtém o risco associado a um projeto.<br>
POST /projetos/{projetoId}/membros: Adiciona um membro a um projeto.<br>
POST /membros: Cria um novo membro.

<h2>Configuração e Execução</h2>

<h3>Pré-requisitos</h3>

Java 17: Certifique-se de ter uma versão adequada do JDK instalada.<br>
Maven: Para gerenciar as dependências do projeto.<br>
PostgreSQL: Certifique-se de ter o PostgreSQL instalado e configurado.<br>

<h2>Passos para Configuração</h2>

Clone o Repositório
git clone https://github.com/Leuciobatista/desafio_code_group.git
cd proj
Configuração do Banco de Dados
Configure a conexão do banco de dados em src/main/resources/application.properties:


Execução
Utilizando o Maven, você pode executar o projeto com o seguinte comando:

./mvnw spring-boot:run<br>
O servidor estará disponível em http://localhost:8080.

<h2>Documentação da API</h2><br>
http://localhost:8080/swagger-ui/index.html


<h2>Licença</h2>
Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para obter detalhes.
