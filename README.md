# Mercato

Football players transfer management application. With this application, users can create a football and add football
players to its club. He should be able to transfer a player to another club.

# Installation

### With Devbox

The project has many dependencies. To avoid installing a lot of tools manually, we added devbox as a package manager to
install them for you in a developer environment which is different to you system environment so also avoid issues like
version conflict.

* Install Devbox   
  please follow that link to install devbox according to your OS: https://www.jetify.com/docs/devbox/installing_devbox/
* Install all in one command: run ```devbox shell```

### Manually

* Install JDK 21 and setup JAVA_HOME environment variable:  https://www.oracle.com/fr/java/technologies/downloads/
* Install Maven 3 and setup MVN_HOME environment variable: https://maven.apache.org/install.html

# Run

Before running the project, you should ensure that you have PostgreSql database at least version 16 installed with
user: **postgres** and password: **postgres**
### Development profile

* If you followed devbox installation and ran ```devbox shell```, just run ```task mercato-api:dev```
* If you used manual installation, run: ```cd mercato-api``` then
  ```mvn spring-boot:run -Dspring-boot.run.profiles=dev```

# Api

A swagger api documentation has been generated and is accessible from that link:
http://localhost:8080/api/swagger-ui/index.html  

# Advanced Search

This project has advanced search for players. This feature has been made with the help of the maven package [Spring
Query Predicate](https://github.com/binark/spring-query-predicate)

# Tests Execution

The integration tests is done with testcontainer. So you need to install Docker before running integration tests.
Docker help to create database install while running integration test from postgres image.

* Execute this command to run unit tests: ```mvn test```
* Execute this command to run unit and integration tests: ```mvn verify```
  A Jacoco report is generated after test execution. It is located at ***target/site/jacoco/index.html***

# Coda Quality

Sonar has been setting up with this project. Then you can check quality report by following this
link: https://sonarcloud.io/project/overview?id=binark_mercato