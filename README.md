# API Java com Spring Boot e Hibernate

## Para iniciar iniciar o projeto, acesse a pasta 'SPRINGBOOT' do projeto pelo terminal e execute:

- Instalar os pacotes:

```
mvn install
```

- Iniciar o projeto

```
mvn spring-boot:run
```

Após a execução do comando `mvn spring-boot:run` a API estará disponivel no endereço http://localhost:8080/

A porta `8080` do projeto pode ser alterada no arquivo `.\src\main\resources\application.properties` no fragmento abaixo (caso o mesmo não existe, basta inclui-lo com a porta desejada).

```
server.port=8080
```

## Com a API rodando, utilize um API Client como Insominia ou Postman por exemplo.

### Estará disponível os seguintes endereços

Para incluir um novo Veículo (Post) localhost:8080/veiculos/incluir

```
{
    "nome": "Nome do Carro",
    "marca": "Marca do Carro",
	"modelo": "Modelo do Carro",
    "dataFabricação": "2020/2021",
    "consumoMedioCidade": 6.0,
    "consumoMedioRodovias": 9.0
}
```

Para listar Veículo (Get) localhost:8080/veiculos/listar

Para buscar um Veículo pelo Id (Get) localhost:8080/veiculos/buscar/{id}

Para verificar o consumo de um determinado Veículo pelo Id(Post) localhost:8080/viagem
Dados utilizado:

- IdVeiculo (Long) -> Id do Veículo cadastrado em localhost:8080/veiculos/incluir
- precoGasolina (Double) -> Preço da gasolina
- kmCidade (Double) -> Kilometros rodados na cidade
- kmRodovia (Double) -> Kilometros rodados na Rodovia

```
{
    "IdVeiculo": 5,
    "precoGasolina": 7.39,
    "kmCidade": 10.01,
    "kmRodovia": 20.01
}
```

Para obter a lista de todos veículos cadastrado do mais econômico para o menos econômico acessar (Post) localhost:8080/viagem/listar

```
{
    "precoGasolina": 7.39,
    "kmCidade": 10.01,
    "kmRodovia": 20.01
}
```
