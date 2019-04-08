# xy-inc

Projeto consiste em criar serviços para auxiliar pessoas na localização de ponto de interesse, em todo momento no projeto foi utilizado a palava (POI) para abreviar "ponto de interesse".

Para desenvolvimento do projeto foi utilizado o Maven para gerenciamento das dependências. Utilizei o Sring Boot para facilitar a criação e configuração do projeto.

Na persistenência de dados utilizei o H2 que facilita bastante a persistência dos modelos criados dinamicamente, além disso o H2 permite obter um acesso rapido aos dados para simulações.

Para expor os serviços REST foi utilizado um RestController do Spring-web (PoiController.java).


## 1. Requisitos e Configurações

Para executar o projeto é necessária a instalação das seguintes ferramentas:
### 1. JDK 1.8
### 2. Maven 3.5.0+


## 2. Instalação

No diretório raiz executar o comando:

mvn spring-boot:run

Pronto, agora o sistema está rodando na porta 8080.


## 3. Utilização
Como um dos objetivos da aplicação é que seja consumida por outras aplicações, foi disponibilizada uma documentação dos serviços. Esta documentação é acessível na URL http://localhost:8080/swagger-ui.html

### 1. Serviço para cadastrar pontos de interesse

POST - http://localhost:8080/api/v1/pois/  - Isso criará um novo poi.

example | model

{
  "coordenadaX": 0,
  "coordenadaY": 0,
  "name": "string",
  "poi_url": "string"
}


### 2. Serviço para listar todos os POIs cadastrados.

GET - http://localhost:8080/api/v1/pois/  - Isto irá obter uma lista de pois.

example | model

No parameters


### 3. Serviço para listar POIs por proximidade

GET - http://localhost:8080/api/v1/pois/{coordenadaX}&{coordenadaY}  - Isto irá obter uma lista de clientes próximos com a coordenada.

example | model

coordenadaX integer($int32)
coordenadaY integer($int32)
