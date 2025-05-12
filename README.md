# Auth Challenge - Desafio Técnico
## 📚 Descrição

Solução implementada para o desafio técnico de autenticação, desenvolvida utilizando Java Spring Boot. O sistema fornece uma API REST para criar/autenticar usuário retornado seu token e suas informações.

## ⚙️ Stack's utilizadas
- Java 24
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Maven
- H2 Database

## 🎁 Como Executar
1. Clone o repositório:
```bash
git clone https://github.com/VersettiDev/AuthChallenge.git
cd auth-challenge
```
2. Execute o projeto:
``` bash
mvn spring-boot:run
```
- O servidor iniciará em `http://localhost:8080`
## 🧶 Endpoints Disponíveis
### 1. Registro de Usuário
``` 
POST /v1/user
```
**Request:**
``` json
{
    "username": "versetti",
    "email": "versetti@email.com",
    "password": "senha123"
}
```
