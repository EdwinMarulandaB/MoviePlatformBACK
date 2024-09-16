# Movie Platform Backend

Este proyecto es una API REST para la gestión de películas desarrollada con Spring Boot.

## Características

- CRUD de películas.
- Gestión de base de datos con MySQL.
- Integración con Spring Data JPA.
- Soporte para desarrollo de API REST.

## Requisitos

- Java 21
- Maven
- MySQL

## Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/EdwinMarulandaB/MoviePlatformBACK.git

## Dependencias principales
- Spring Boot 3.3.3
- Spring Boot Starter Web: Para crear la API REST.
- Spring Boot Starter Data JPA: Para la persistencia de datos.
- MySQL Connector: Para la conexión con MySQL.
- Lombok: Para reducir el boilerplate del código.
  
## Scripts
- mvn clean install: Compila el proyecto y descarga las dependencias.
- mvn spring-boot:run: Inicia el servidor Spring Boot.

## Notas
Crear la base de datos en MySQL:
CREATE DATABASE MoviePlatform;

Nota: No es necesario crear tablas ni cargar datos. La aplicación se encargará de generar el esquema y cargar los datos iniciales automáticamente.

Configurar las propiedades de conexión a la base de datos en src/main/resources/application.properties. Asegúrate de ajustar el nombre de usuario y la contraseña según tu configuración local de MySQL:

properties
spring.application.name=MoviePlatform
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/MoviePlatform
spring.datasource.username=root
spring.datasource.password=tu_contraseña
  
