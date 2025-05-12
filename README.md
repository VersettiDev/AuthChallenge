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
    "password": "hello!"
}
```
**Response:**
``` json
{
	"message": "User created successfully",
	"user": {
		"id": 1,
		"username": "versetti",
		"password": "aGVsbG8h",
		"email": "versetti@email.com"
	}
}
```
### 2. Login
``` 
POST /auth/login
```
**Request:**
``` json
{
    "username": "versetti",
    "password": "aGVsbG8h" (Obrigatório a senha codificada)
}
```
**Response:**
``` json
{
	"user": {
		"id": 1,
		"username": "versetti",
		"email": "versetti@email.com"
	},
	"token": "eyJhbGciOiJIUzI1NiJ9...."
}
```
## ❗ Possíveis Erros
### Usuário já existe:
``` json
{
    "message": "Username already exists"
}
```
### Credenciais inválidas:
``` json
{
    "error": "Username or password is incorrect"
}
```
## 📝 Notas Importantes
1. **Senhas:**
    - Devem ser enviadas em Base64
    - São armazenadas de forma criptografada (BCrypt) no banco de dados.

2. **Autenticação:**
    - Utiliza JWT para gerenciar tokens de autorização.
## 💾 Banco de Dados
O projeto utiliza H2 Database (em memória) para facilitar os testes e a execução local.
### Configuração do Banco (application.properties):
``` properties
spring.datasource.url=jdbc:h2:mem:authdb
spring.datasource.username=admin
spring.datasource.password=123
spring.h2.console.enabled=true
```
## 📄 Licença
Este projeto está sob a licença MIT.
## 📞 Contato
Para dúvidas ou sugestões sobre o desafio, entre em contato através do [LinkedIn](seu-linkedin) ou abra uma issue no repositório.
