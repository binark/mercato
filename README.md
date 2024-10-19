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

### Development profile

* If you followed devbox installation and ran ```devbox shell```, just run ```task mercato-api:dev```
* If you used manual installation, run: ```cd mercato-api``` then
  ```mvn spring-boot:run -Dspring-boot.run.profiles=dev```