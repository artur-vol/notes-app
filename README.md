# Notes App

A simple Java web application built for deployment on Apache Tomcat 9 and Microsoft Azure.

## Tech Stack

- Java 17
- Apache Tomcat 9
- Maven
- JSP
- Servlets
- JDBC
- Microsoft SQL Server / Azure SQL Database

## Features

- Create notes
- View notes
- Edit notes
- Delete notes
- Health endpoint for Azure Load Balancer

## Build

```bash
mvn clean package
```

The generated artifact will be:

```
target/notes-app.war
```

Deployment and configuration instructions will be added as the project is developed.
