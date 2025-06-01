---

# ChallengeLiterAlura

## Descrição

Projeto Java Spring Boot que consome a API pública [Gutendex](https://gutendex.com) para buscar livros e autores, armazenando esses dados em um banco PostgreSQL. Permite consultas, listagem e exibição de estatísticas sobre livros e autores.

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* HttpClient (Java 11+)
* Jackson (para desserialização JSON)
* Maven

---

## Funcionalidades

1. **Busca de livros por título e autor**

    * Consulta a API Gutendex para buscar livros com base no título ou nome do autor.
    * Os resultados são armazenados localmente e persistidos no banco de dados.

2. **Persistência em banco de dados**

    * Dados de livros e autores são mapeados para entidades JPA e armazenados no PostgreSQL.
    * Relação entre livros e autores é mantida via chave estrangeira.

3. **Listagem de livros e autores**

    * Listar todos os livros buscados (tanto em memória quanto no banco).
    * Listar autores únicos dos livros buscados.
    * Listar autores vivos em determinado ano (baseado em consultas derivadas no banco).

4. **Estatísticas**

    * Exibir a quantidade de livros armazenados em um determinado idioma.
    * Suporte para idiomas limitados (ex: `en` para inglês e `pt` para português).

---

## Estrutura do Projeto

* `client` — Classe para comunicação com API Gutendex, retornando objetos mapeados.
* `model` — Classes DTO para desserialização dos dados JSON da API.
* `entity` — Classes JPA que representam as tabelas do banco (LivroEntity e AutorEntity).
* `repository` — Interfaces Spring Data JPA para operações de banco de dados.
* `Main` — Classe principal com interface de linha de comando para interação com o usuário.

---

## Configuração

### Banco de Dados

* Utiliza PostgreSQL.
* Configurações no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Rodando o Projeto

* Certifique-se que o PostgreSQL está rodando e o banco `literalura` criado.
* Atualize usuário e senha no `application.properties`.
* Execute a aplicação Spring Boot.
* Use o menu no terminal para buscar livros, listar autores, e exibir estatísticas.

---

## Como usar

* **Buscar livros:** Informe título ou autor para buscar na API e salvar localmente.
* **Listar livros:** Exibe os livros armazenados na memória durante a execução atual.
* **Listar autores:** Mostra os autores únicos dos livros buscados.
* **Autores vivos:** (Funcionalidade a implementar) Listar autores vivos em um ano específico.
* **Estatísticas:** Informar um idioma para saber a quantidade de livros naquele idioma no banco.

---

## Próximos Passos

* Implementar a funcionalidade para listar autores vivos usando consulta derivada no banco.
* Adicionar opções para atualizar e remover livros e autores.
* Melhorar validação e tratamento de exceções na interação com o usuário.
* Interface gráfica para melhorar a usabilidade.

---

## Autor

Gustavo Bione Martins
---