# Delivery Api
API REST com o framework Spring Boot do Java controle de delivery 

## Requisitos

- Código versionado em repositório Git
- Java 8
- Spring Boot 2
- Banco de Dados Firebird
- API RESTful
- Maven
- OpenAPI 3.0 (Swagger)

## Banco de Dados

- Foi utilizado no projeto o banco de dados Firebird necessario instala-lo e  mudar a url no 
arquivo application.properties no atributo spring.datasource.url e colocar o caminho do banco.
- Anexei o banco de dados junto ao projeto (usurario e senha padrão)


No  HEADERS da requisição é obrigatório passar key Authorization. Exemplo:
```cmd
Authorization Bearer token(obtido a partir do response da route login)
```
É obrigatório ter um espaço entre "Bearer" e o token.

## Routes

Route para buscar todos os registros (method get):
  
  Listar todos
- http://localhost:8080/api/cliente 
- http://localhost:8080/api/entrega
- http://localhost:8080/api/pedido 

Route para buscar por id (method get):

  Buscar por id
- http://localhost:8080/api/cliente/1 .
- http://localhost:8080/api/entrega/1 
- http://localhost:8080/api/pedido/1 

Route para salvar ou atualizar registros:

- http://localhost:8080/api/cliente Para salvar (method post) e alterar (method put) -> salvar cliente.
Body:
```json
{
    "nome":"Caio Wilquer",
    "cpf": "000.000.000-00",
    "email":"teste@gmail.com",
    "telefone":"(62)99999999"

}
```
- http://localhost:8080/api/entrega Para salvar (method post) e alterar (method put) -> salvar entrega.
Body:
```json
{
    "rua":"Av graça",
    "numero": 00,
    "cep":"740870-20",
    "complemento":"null",
    "taxa_entrega": 10.0,
    "cidade":"Aparecida",
    "bairro":"Satelite"
}
```
- http://localhost:8080/api/pedido Para salvar (method post) e alterar (method put) ->  pedido por id.
Body:
```json
{
  "descricao":"",
  "valor_total": 2.99,
  "entregue":false,
  "data_pedido": "2023-06-16",
  "cliente":{
    "id": 1,
    "nome":"Caio Wilquer",
    "cpf": "000.000.000-00",
    "email":"teste@gmail.com",
    "telefone":"(62)99999999"

  },
  "entrega": {
    "id": 1,
    "rua":"Av graça",
    "numero": 00,
    "cep":"740870-20",
    "complemento":"null",
    "taxa_entrega": 10.0,
    "cidade":"Aparecida",
    "bairro":"Satelite"
  }
}
```
Route para deletar registros (method delete):

  Deletar por id
- http://localhost:8080/api/cliente/1
- http://localhost:8080/api/entrega/1
- http://localhost:8080/api/pedido/1 

