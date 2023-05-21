# Autores
Edson Junio Bonfim Pinto, Julio Cesar de Paula Ferreira, Lucas Gabriel Rios da Silva, Vitor Barreto Souza.

# Estoque Veículos
Este projeto é uma API para controle de estoque de Veículos sem persistência de dados elaborado utilizando o Spring.
O sistema possui serviços de recuperar, cadastrar e deletar relacionados às entidades Cliente, Veiculo e Venda. Além disso, foi implementado o Controller VeiculosFipe, que possui integração com a API externa [FIPE API HTTP REST](https://deividfortuna.github.io/fipe/)), para recuperar dados de marcas e veículos da tabela FIPE.
## Endpoints:

### Clientes:
- **GET /api/clientes**: Lista os clientes cadastrados no sistema;
- **GET /api/clientes/{id}**: Retorna o cliente com o `{id}` correspondente. Caso não esteja cadastrado, lança uma excessão;
- **POST /api/clientes**: Adiciona o Cliente recebido como parâmetro ao sistema;
- **DELETE** /api/clientes/{id}: Deleta o cliente com o `{id}` correspondente. Caso não esteja cadastrado, lança uma excessão;

### Veículos:
- **GET /api/veiculos**: Lista os veículos cadastrados no sistema;
- **GET /api/veiculos/{modelo}**: Lista os veículos cadastrados no sistema que são do `{modelo}`;
- **GET api/veiculos/ano?anoMax=&anoMin="**: Lista os veículos que possuem ano de fabricação entre `anoMin` e `anoMax`;
- **GET /api/veiculos/carros/{marca}**: Lista os carros cadastrados no sistema que pertêncem à marca `{marca}`;
- **GET /api/veiculos/motos/{marca}**: Lista as motos cadastradas no sistema que pertêncem à marca `{marca}`;
- **GET /api/veiculos/chassi/{chassi}**: Retorna o veículo com o `{chassi}` correspondente. Caso não esteja cadastrado, lança uma excessão;
- **POST /api/veiculos/motos**: Adiciona a Moto recebida como parâmetro ao sistema;
- **POST /api/veiculos/carros**: Adiciona o Carro recebido como parâmetro ao sistema;
- **DELETE /api/veiculos/{id}:** Deleta o veículo com o `{id}` correspondente. Caso não esteja cadastrado, lança uma excessão;

### Veículos Fipe (Recupera dados utilizando a API externa [FIPE API HTTP REST](https://deividfortuna.github.io/fipe/))
- **GET /api/veiculos/fipe/{tipoVeiculo}/marcas**: Lista as marcas do `{tipoVeiculo}` (carros ou motos) cadastradas na tabela FIPE;
- **GET /api/veiculos/fipe/{tipoVeiculo}/marcas/{codMarca}**: Lista os modelos da marca do `{tipoVeiculo}` (carros ou motos) identificada pelo `{codMarca}`;
- **GET /api/veiculos/fipe/{tipoVeiculo}/marcas/{codMarca}/modelos/{codModelo}/ano/{ano}**: Retorna o modelo do `{tipoVeiculo}` (carros ou motos) identificado pelo `{codModelo}`, do `{ano`} e pertencente à marca `{codMarca}`;

### Vendas:
- **GET /api/vendas**: Lista as vendas cadastradas no sistema;
- **GET /api/vendas/cliente/{cpf}**: Lista as vendas do cliente identificado pelo `{cpf}`;
- **GET /api/vendas/periodo?dataFim=&dataInicio=**: Retorna a soma total do valor em vendas ocorridas entre `{dataInicio}` e `{dataFim}`;
- **GET /api/vendas/relatoriomodelo?dataFim=&dataInicio=&modelo=&valorMinimo**: Lista as vendas do `{modelo}` ocorridas entre `{dataInicio}` e `{dataFim}` e que tenham `{valorMinimo}`;
- **GET /api/vendas/veiculo/{modelo}**: Lista as vendas do `{modelo}`;
- **POST /api/vendas**: Adiciona a venda recebida como parâmetro no sistema;
- **DELETE /api/vendas/{id}**: Deleta a venda de `{id}` correspondente. Caso não esteja cadastrada, lança uma excessão;

## Requisitos
1. IDE IntelliJ
2. JDK 17

## Passo a Passo para execução
1. Abra o projeto no IntelliJ
2. Certifique-se que a JDK escolhida é a versão 17. Isso pode ser feito indo no menu Run -> Edit Configurations.
3. Execute o projeto.
4. Acesse os endpoints por meio do link http://localhost:8080/swagger-ui.html#/

## Estrutura do Projeto
As classes elaboradas para o projeto estão localizadas em src/main/java/br/com/concessionaria.
Dentro desse grupo, estão os packages:
- **configuration:** Contém a classe de configuração do Swagger;
- **controller:** Contém os Controllers REST da aplicação;
- **domain/entity:** Contém as Entidades utilizadas no projeto;
- **domain/dto:** Contém os DTOs (Data Transfer Objects) utilizados;
- **exception:** Contém as excessões elaboradas para o projeto;
- **repository:** Contém as classes de repositório sem persistência de dados, armazenando os dados em ArrayLists;
- **service:** Contém as classes de serviço;
