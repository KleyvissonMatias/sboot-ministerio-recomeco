# API do Ministério Recomeço

A API do Ministério Recomeço é uma aplicação que oferece recursos para gerenciar informações relacionadas ao Ministério, seguindo uma arquitetura hexagonal e utilizando as tecnologias mais recentes do ecossistema Spring Boot.

## Pré-requisitos

Antes de começar, certifique-se de que você tenha instalado o seguinte em seu ambiente de desenvolvimento:

- Java 11 ou superior
- Maven
- MySQL
- Git (opcional, se você planeja clonar o repositório)

## Configuração do Banco de Dados

Configurado no application.yml

spring:
  on-profiles: local
  datasource:
    url: jdbc:mysql://localhost:3306/ministeriodb?useSSL=false
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: usuario
    password: senha

    
A API utiliza o PostgreSQL como banco de dados padrão. Certifique-se de criar um banco de dados e configurar as informações de conexão no arquivo `application.properties` ou `application.yml` do Spring Boot.

## Executando a Aplicação

Para executar a aplicação, siga estas etapas:

1. Clone o repositório (se você não o fez anteriormente):

   ```bash
   git clone https://github.com/seu-usuario/sboot-ministerio-recomeco.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd sboot-ministerio-recomeco
   ```

3. Compile o projeto usando o Maven:

   ```bash
   mvn clean install
   ```

4. Execute a aplicação:

   ```bash
   java -jar target/sboot-ministerio-recomeco-1.0.0.jar
   ```

A API estará disponível em `[http://localhost:8080](http://localhost:8080/swagger-ui/index.html#/)`.

## Arquitetura Hexagonal

Este projeto segue a arquitetura hexagonal (também conhecida como arquitetura de porta e adaptador), que separa a aplicação em camadas distintas:

- **Domínio**: Contém as regras de negócio e a lógica de domínio da aplicação.
- **Aplicação**: Contém os casos de uso da aplicação que interagem com o domínio.
- **Adaptadores**: Contém os adaptadores que permitem que a aplicação interaja com elementos externos, como o banco de dados.

A estrutura de pacotes reflete essa divisão de responsabilidades.

## Endpoints da API

A API fornece os seguintes endpoints:

- `v1/api/ministerio-recomeco`: CRUD para gerenciar informações relacionadas ao Ministério.
- `v1/api/ministerio-recomeco/vida`: CRUD para gerenciar vidas que irão fazer parte da nossa Igreja.
- `v1/api/ministerio-recomeco/celula` CRUD para gerenciar celulas que fazem parte da nossa Igreja.
