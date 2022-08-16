# Liquibase Demo

This repository demonstrates the benefits and power of using Liquibase

## Prerequisites
* Java
* Maven
* Docker
* Liquibase CLI

### MacOS QuickStart
Run `brew list` to see what dependencies you have; if you are missing any run any of the following commands to add it.
```
brew install openjdk
brew install maven
brew install liquibase
brew install docker
```

## MySQL
In the `docker` folder there are 3 scripts that can be used to quickly start a MySQL database and create/drop the 
schema.
* start-mysql.sh
* create-schema.sh
* drop-schema.sh

## Building
The build is using Maven following Maven standards.  Simply build with `mvn clean install`

## Running
There are multiple ways to run this depending on how you want to perform database migrations.  The program will persist 
a set of Customer objects to the DB (if none exist), fetch them, and then echo them out

### LiquibaseCLI Managed
Run `java -jar target/liquibase-demo-1.0.0-SNAPSHOT.jar --spring.liquibase.enable=false`  You should see DB related 
errors.

Execute `liquibase --changelog-file=standaloneChangelog.xml update` to perform a database migration.

Run `java -jar target/liquibase-demo-1.0.0-SNAPSHOT.jar --spring.liquibase.enable=false` and the database errors go 
away.

### Maven Managed
Run `mvn liquibase:update`

Maven will automatically run Liquibase migrations for you.

### Spring Managed
Run `java -jar target/liquibase-demo-1.0.0-SNAPSHOT.jar`

Spring will automatically run Liquibase migrations for you.