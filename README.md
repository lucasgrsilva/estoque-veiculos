# Estoque-veiculos
Este projeto é uma API para controle de estoque de Veículos sem persistência de dados elaborado utilizando o Spring.

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
