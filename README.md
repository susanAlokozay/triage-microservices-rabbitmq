# Triage System with Spring Boot & RabbitMQ

This project simulates an emergency room triage system using microservices and RabbitMQ.

## Microservices

- **triage-service** (Producer): Registers patients via REST API and sends messages to RabbitMQ
- **atencion-leve-service**: Listens to `pacientes.leve`
- **atencion-grave-service**: Listens to `pacientes.grave`
- **atencion-critico-service**: Listens to `pacientes.critico`
- **supervisor-service**: Listens to all patients `pacientes.*`

## Technologies

- Java 17
- Spring Boot
- RabbitMQ (Topic Exchange)
- REST API (Postman)
