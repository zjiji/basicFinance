# Simple REST api example

This is a sample of basic finance application which provides REST apis for operations like create user, create user
account with/without transaction amount.
## Repository 
You can clone the project from : 
https://github.com/zjiji/basicFinance
To be able to build and start the project you will need to navigate to main folder.

## Building / Prerequisite
To build this project on your local machine you will need to have the following development tools installed: 

- Java JDK (v21)
- Maven (v3+)
- Docker (docker daemon needs to be running)

This command will build the project and produce the jar file under /target folder
```shell script
mvn clean install
```
You can run project directly with this command : 
```shell script
mvn clean  spring-boot:run
```
After building the project you can run via  docker-compose
```shell script
docker-compose up
```

## frameworks used

- Spring boot

## Data source

- H2
- You can reach H2 console via  http://localhost:8080/h2-console/
- JDBC URL is defined in [application properties](src/main/resources/application.properties)


## using REST API

To view the REST API endpoints check the swagger
documentation [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

