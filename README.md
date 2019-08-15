# API Gestão de Contatos #


* A API GestaoContatos fornce recursos para um CRUD de contatos - Adiciona, busca, remove, lista produtos através de serviços rest usando Json content-type.
(Especificação seguida para desenvolvimento da API - https://github.com/arilsonsantos/meta/blob/token/Contato.yaml)

* Version 1.0.0

#### Recursos disponíveis

###### GET 
* /v1/user/contatos         -  Retorna uma lista de objetos do tipo Contato
* /v1/user/contatos/{id}    -  Retorna um único objeto do tipo Contato


###### POST
* /v1/admin/contatos        -  Adiciona um registro do tipo Contato


###### PUT
* /v1/admin/contatos/{id}   -  Atualiza um registro do tipo Contato


###### DELETE 
* /v1/admin/contatos/{id}   -  Deleta um contato


#### Setup 

1. Faça um clone do repositório
2. git clone https://github.com/arilsonsantos/meta


#### Execução da aplicação

Acesse o diretório criado pelo clone -> "meta" e digite o comando abaixo:

Windows
* mvnw spring-boot:run

Linux/Mac
* ./mvnw spring-boot:run


#### Após, no browser, acesso a seguinte URL e siga as instruções:

##### http://localhost:8080/gestaocontato/swagger-ui.html#/

