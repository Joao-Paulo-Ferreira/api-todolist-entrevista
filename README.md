# 📋 API de Gestão de Tarefas (To-Do List)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## 🎯 Sobre o Projeto
Esta é uma API RESTful desenvolvida como parte de um desafio técnico para demonstrar proficiência no ecossistema Java (Spring Boot) e na modelagem relacional de bases de dados. O sistema permite a gestão completa de tarefas (CRUD), aplicando regras de negócio para o controlo de estados e prazos.

O foco principal deste desenvolvimento recaiu sobre a **qualidade do código, arquitetura em camadas e adoção de boas práticas de mercado**.

## ✨ Diferenciais Técnicos e Arquitetura
Para garantir uma aplicação robusta e pronta para produção, foram implementadas as seguintes práticas:

* **Gestão Manual da Base de Dados:** O esquema da base de dados foi desenhado manualmente para garantir a aplicação de restrições de integridade (`CONSTRAINTS` e `CHECK`) e a criação de índices (`INDEX`) para otimização de performance nas procuras. O script encontra-se no ficheiro `script_banco.sql`.
* **Tratamento Global de Exceções (`@RestControllerAdvice`):** Erros de validação e regras de negócio violadas retornam mensagens JSON padronizadas e amigáveis, isolando a *stacktrace* do servidor.
* **Validação Rigorosa de Dados:** Utilização da biblioteca `jakarta.validation` (`@NotBlank`, `@FutureOrPresent`) para garantir a integridade dos dados logo na entrada da API.
* **Isolamento de Estado e Regras de Negócio:** Alterações críticas, como a conclusão de uma tarefa, possuem endpoints específicos (verbo `PATCH`) e validações próprias na camada de serviço (`Service`).
* **Testes Unitários:** Implementação de testes automatizados com JUnit 5 e Mockito para garantir a fiabilidade das regras de negócio.

## ⚙️ Pré-requisitos (MySQL)

Este projeto utiliza o MySQL como base de dados. Para o executar localmente, certifique-se de ter uma instância do MySQL a correr (por norma na porta `3306`).

1. Crie uma base de dados vazia chamada `todolist_db`:
   ```sql
   CREATE DATABASE todolist_db;