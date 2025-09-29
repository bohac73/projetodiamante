# 💎 Projetodiamante

Aplicação web **Full MVC com Spring Boot**, utilizando **Thymeleaf**, **Spring Data JPA**, **Flyway**, **PostgreSQL**, **Docker Compose** e autenticação via **OAuth2 (GitHub Login)**.

Este projeto é um exemplo simples de **ToDo App** (lista de tarefas) desenvolvido em Java, com arquitetura organizada e pronto para rodar em ambiente containerizado.

---

## 🚀 Tecnologias

- **Java 17+**
- **Spring Boot 3**
    - Spring MVC + Thymeleaf
    - Spring Data JPA (Hibernate)
    - Spring Security + OAuth2 Client (Login com GitHub)
- **Flyway** (migrações de banco de dados)
- **PostgreSQL** (banco de dados relacional)
- **Docker Compose** (infraestrutura containerizada)
- **Maven** (gerenciador de dependências)

---

## ⚙️ Pré-requisitos

- **Git**
- **Java 17+**
- **Maven 3.8+**
- **Docker + Docker Compose**

---

## 🛠️ Como rodar o projeto

### 1. Clone o repositório
```bash
git clone https://github.com/bohac73/projetodiamante.git
cd projetodiamante

2. Configure as variáveis de ambiente

Crie um arquivo .env na raiz do projeto (ou configure direto no docker-compose.override.yml):

DB_HOST=db
DB_PORT=5432
DB_NAME=todo_db
DB_USER=todo_user
DB_PASS=todo_pass

GITHUB_CLIENT_ID=seu_client_id
GITHUB_CLIENT_SECRET=seu_client_secret

DB_HOST=db
DB_PORT=5432
DB_NAME=todo_db
DB_USER=todo_user
DB_PASS=todo_pass

GITHUB_CLIENT_ID=seu_client_id
GITHUB_CLIENT_SECRET=seu_client_secret

⚠️ O GITHUB_CLIENT_ID e GITHUB_CLIENT_SECRET são obtidos em
GitHub → Settings → Developer Settings → OAuth Apps.

	•	Homepage URL: http://localhost:8080
	•	Authorization callback URL: http://localhost:8080/login/oauth2/code/github

A aplicação ficará disponível em:

👉 http://localhost:8080

🔑 Autenticação com GitHub
	1.	Clique em “Entrar com GitHub” na tela inicial.
	2.	Faça login com sua conta GitHub e autorize a aplicação.
	3.	Você será redirecionado para o app já autenticado.

⸻

🐳 Serviços do Docker Compose
	•	db → PostgreSQL
	•	app → Spring Boot (ToDo App)

O docker-compose.yml já inclui:
	•	Healthcheck no Postgres (pg_isready)
	•	Dependência de inicialização entre serviços

⸻

📌 Migrações com Flyway

As migrações do banco estão em src/main/resources/db/migration.
Ao iniciar, o Flyway executa automaticamente os scripts Vx__*.sql.

⸻

✨ Funcionalidades
	•	Criar, listar e remover tarefas (ToDo)
	•	Persistência no PostgreSQL via Spring Data JPA
	•	Autenticação via GitHub OAuth2
	•	Migrações automáticas com Flyway
	•	Ambiente Dockerizado pronto para desenvolvimento