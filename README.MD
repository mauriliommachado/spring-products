# Product API

This is a api built with Spring (Boot, MVC, Security and Data).

## Usage

To usage thi you should configure the application.yml file under the resources folder, and set your datasource credentials like so:

```yml
spring:
  application:
    name: products
  datasource:
    url: jdbc:postgresql://localhost/products
    username: postgres
    password: admin
  jpa:
    generate_ddl: true
    properties:
      hibernate:
        temp:
          use_jdbc_metadata_defaults: false

app:
  jwtSecret: secret
  jwtExpiration: 604800000
```

After this is done you can test the API with [this](https://www.getpostman.com/collections/7ae9b46d9fff8ed8b7a1) postman collection
