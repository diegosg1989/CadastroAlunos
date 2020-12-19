<h1 align="center">API - Cadastro de Alunos</h1>

<p align="center">
  <a href="#tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#topologia">Topologia</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#executar">Como executar?</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>

## Topologia

<p align="center">
  <img alt="topologia" src="https://github.com/diegosg1989/CadastroAlunos/blob/main/topologianew.png" width="100%">
</p>

## Tecnologias

Este projeto foi desenvolvido utilisando Java 8, TDD, Mockito, Swagger, Spring, React.js e banco de dados H2

## Projeto

Projeto refetente a um processo seletivo. Foi desenvolvido uma solução simple para cadastro de alunos. 
Além de o projeto ser uma api, ele também consulta uma api externa para inserir os dados no banco.

  - API externa: `https://my-json-server.typicode.com/diegosg1989/ApiAlunosTeste/db`

## Como executar?

  - Baixar o projeto: `$ git clone https://github.com/diegosg1989/CadastroAlunos.git`
  - Importar o projeto na IDE de sua escolha
  
    **Back-end:**

    - Acessar a pasta **alunos**, o arquivo **build.gradle** contém todas as dependencias necessárias para a importação no projeto. Acessa a classe **AlunosApplication.java** e executar como Spring Boot App.
  
    **Front-end:**  
  
    - Acessar a pasta **frontend** para executar com os comandos:
      - `npm install` realizar os imports necessários dos modulos para execução do React.js
      - `npm start`para abrir a aplicação no browser
    
  - Após subir o sistema é possivel acessar a documentação da api no swagger pelo link `http://localhost:8081/swagger-ui/#/`
