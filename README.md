# Api De Pagamento

## Tecnologias Utilizadas

- Java
- Spring Boot
- JPA
- Hibernate
- Postgresql
- Lombok
- Swagger
- ModelMapper
- Gson
- Docker
- Junit
- Mockito
- Hamcrest
  
## Configuração

### Docker
1. Clone o repósitorio
2. Instale o docker (https://www.docker.com/products/docker-desktop/)
3. Abra o docker
4. Abra o terminal
5. Navegue até a api_pagamento_2024
6. Digite docker-compose up -d
    1. A imagem da aplicação será executada, ou seja, se tornará um container
    2. A imagem do postgresql será executada, ou seja, se tornará um container
    3. O jar da aplicaçào será gerado, executado e inserido em uma imagem

## Execução

### Docker
1. A api está disponível no localhost:8081
- Obs: Se o código for modificado e você desejar usar o host exposto pelo docker:
    1. Digite docker-compose build --no-cache para uma nova imagem da aplicação ser gerada
    2. Digite docker-compose up -d

### Ide  
1. Abra a pasta api_pagamento_2024 em uma IDE (Ex: IntelliJ IDEA) 
2. Navegue pela IDE até ApiPagamentoApplication
3. Aperte o botão play localizado ao lado de "public class ApiPagamentoRedisJsonApplication"
4. A api está disponível no localhost:8080

### Mvn
1. Abra o cmd
2. Navegue até a pasta api_pagamento_2024
3. Rode ./mvnw spring-boot:run
4. A api está disponível no localhost:8080

## EndPoints
  
  ### localhost:8080/transacao/v1/pagamento
  
  Request:
  
  ```
  { 
   "cartao": "4444********1234",
   "descricao": {
      "valor": "500.55",
      "dataHora": "01/05/2021 18:00:00",
      "estabelecimento": "PetShop Mundo cão"
    },
    "formaPagamento":{
        "tipo":"AVISTA",
        "parcelas": "1"
    }
  }
  ```

  Response:
  
  ```
  {
    "id": 1,
    "cartao": "4444********1234",
    "descricao": {
        "valor": "500.55",
        "dataHora": "01/05/2021 18:00:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "AUTORIZADO"
    },
    "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
    }
  }
  ```
  
  ### localhost:8080/transacao/v1/estorno/1
  
  Response: 
  
  ```
  {
    "id": 1,
    "cartao": "4444********1234",
    "descricao": {
        "valor": "500.55",
        "dataHora": "01/05/2021 18:00:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "NEGADO"
    },
    "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
    }
  }
  ```
  
  ### localhost:8080/transacao/v1/1
  
  Response:
  
  ```
  {
    "id": 1,
    "cartao": "4444********1234",
    "descricao": {
        "valor": "500.55",
        "dataHora": "01/05/2021 18:00:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "NEGADO"
    },
    "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
    }
  }
  ```
  
  ### localhost:8080/transacao/v1
  
  Response:
  
  ```
  [
    {
        "id": 1,
        "cartao": "4444********1234",
        "descricao": {
            "valor": "500.55",
            "dataHora": "01/05/2021 18:00:00",
            "estabelecimento": "PetShop Mundo cão",
            "nsu": "1234567890",
            "codigoAutorizacao": "147258369",
            "status": "NEGADO"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": "1"
        }
    },
    {
        "id": 2,
        "cartao": "4444********1234",
        "descricao": {
            "valor": "500.55",
            "dataHora": "01/05/2021 18:00:00",
            "estabelecimento": "PetShop Mundo cão",
            "nsu": "1234567890",
            "codigoAutorizacao": "147258369",
            "status": "AUTORIZADO"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": "1"
        }
    }
  ]
  ```
