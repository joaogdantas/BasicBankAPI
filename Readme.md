# BasicBankAPI

#### Tecnologias necessárias
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

Uma API que criei como projeto de estudo e para portifólio, simula de forma bem básica o sistema de um banco, o objetivo do projeto é poder demonstrar meus conhecimentos de desenvolvedor backend com o uso certas tecnologias como Java/Spring, uma arquitetura de software baseada nos princípios de DDD, a criação de API's REST usando também o spring framework, o uso e sincronização de banco de dados PostgreSQL, o versionamento de código com Git através do GitHub utizando commits atômicos, o que ajuda a facilitar a compreensão dos desenvolvedores e de quem quer entender o código, além do gerenciamento de dependências (nesse projeto usei Maven), bem como a documentação de API usando Swagger. 

![Documentação da API em Swagger](https://user-images.githubusercontent.com/90569772/251213940-6363225f-46e0-40a6-a520-ba58dd097edf.png)

## Visão Geral

Fiz a modelagem de forma bem simples já que o objetivo não era criar uma aplicação complexa e com múltiplas funcionalidades, mas sim demonstrar meus conhecimentos de criação de API's. Essa API possui quatro entidades: Account (Conta), Address (Endereço), Credit Card (Cartão de crédito) e Customer (Cliente). Ela serve para registrar, atualizar e apagar essas entidades do banco de dados PostgreSQL. Eles são relacionados da seguinte forma: O Customer ele possui uma Account e um Adress, em uma relação @OneToOne, dessa forma, para a criação de um Customer, é preciso criar primeiro um Address e uma Account para ele, que serão passados na criação do mesmo pelas suas chaves primárias, que são postalCode (CEP) e accountNumber (Número da conta), respectivamente.

O Credit Card, por sua vez, possui uma relação @ManyToOne com a Account, ou seja, é necessário ter uma conta criada para se criar uma cartão de crédito associada a ela, uma conta pode possuir mais de um cartão de crédito.

## Recursos
Os recursos foram pensados com base em um levantamento de requisitos:

- Account:
  - Criar conta; 
  - Retornar conta (todas criadas no banco ou uma específica baseada no seu número);
  - Realizar uma operação em uma conta específica (operações disponíveis: "deposit" e "withdraw");
  - Atualizar agência de uma conta;
  - Deletar conta.
	
- Address
  - Criar endereço;
  - Retornar todos os endereços;
  - Atualizar endereço
  - Deletar endereço

- Credit Card
  - Criar cartão de crédito (Passando o número da conta a qual ele está associado);
  	- Para isso algumas validações são feitas:
   		- O número do cartão deve conter exatamente 16 números, passando somente números, sem pontos ou espaços;
       		- O CVV deve conter exatamente 3 números;
           	- A data de expiração tem que estar correta no formato (MM/yy) e deve ser uma data válida, ou seja, expiração após a data atual.
  - Retornar os cartões de crédito associados a uma conta (Por questões de segurança foi criado um método para retornar apenas os últimos 4 dígitos do cartão, substituindo o restante por "*");
  - Atualizar limite de crédito do cartão;
  - Deletar cartão associado a uma conta.

- Customer
  - Cadastrar cliente (Passando o número de sua conta e o código postal do endereço que são associados a ele);
  - Retornar cliente pelo seu número de documento;
  - Retornar todos os clientes;
  - Atualizar dados do cliente;
  - Deletar cliente.

## Instalação
- Clone o projeto com:
``git clone https://github.com/joaogdantas/BasicBankAPI.git``

- Executar com o comando:
 ``mvn spring-boot:run``
 ou
 ``./mvnw spring-boot:run``

Após isso a documentação em swagger ficará disponível no link abaixo, podendo ser acessada e testada:
 ``http://localhost:8080/swagger-ui/index.html``

## Próximos passos

Alguns pontos que ainda preciso adicionar que poderiam ser úteis, mas como se tratava de uma criação de API simples para demonstração e estudo, acabei não adicionando ainda.

- Por motivos de facilitar o acesso a API, acabei não adicionando nada de spring security, isso seria algo a adicionar futuramente;
- Ainda necessário serem adicionados testes unitários;
- Adicionar novas entidades para aumentar a complexidade, como funcionário, por exemplo;
- Fazer uma interface gráfica à API.

## Meu Linkedin:

https://www.linkedin.com/in/joaogdantas/

### FIQUE À VONTADE PARA CONTRIBUIR
