# SISTEMA DE GERENCIAMENTO DE ALOCAÇÃO DE QUADRA - SGAQ

<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-diagramas">Diagramas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-instalação">Instalação</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="#-funcionalidades">Funcionalidades</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="#-documentação">Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-licença">Licença</a>
</p>


## ✍️ Autores

| [![Davi Kreppel](https://avatars.githubusercontent.com/u/66695188?v=4)](https://github.com/DaviKpp) | [![Gustavo Detomi](https://avatars.githubusercontent.com/u/31541906?v=4)](https://github.com/Gudetomi) | [![Leandro Souza](https://avatars.githubusercontent.com/u/48530574?v=4)](https://github.com/Lsouz44) | [![Matheus Nascimento](https://avatars.githubusercontent.com/u/23366884?v=4)](https://github.com/matheuznsilva) | [![Sidney Júnior](https://avatars.githubusercontent.com/u/51861308?v=4)](https://github.com/sidjr2) |
|:-:|:-:|:-:|:-:|:-:|
| [Davi Kreppel](https://github.com/DaviKpp) | [Gustavo Detomi](https://github.com/gudetomi) | [Leandro Souza](https://github.com/Lsouz44) | [Matheus Nascimento](https://github.com/matheuznsilva) | [Sidney Junior](https://github.com/sidjr2) |


## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- ![Java](https://img.shields.io/badge/Java-red)
- ![JavaScript](https://img.shields.io/badge/JavaScript-yellow)
- ![Springboot](https://img.shields.io/badge/Springboot-green)
- ![Bootstrap](https://img.shields.io/badge/Bootstrap-purple)
- ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-darkgreen)
- ![Mysql](https://img.shields.io/badge/MySQL-blue)


## 💻 Projeto

O Sistema de Gerenciamento de Alocação de Quadra (SGAQ) é uma sistema web desevolvido buscando otimizar o processo de reserva e utilização da quadra esportiva da Universidade Federal de São João del-Rei (UFSJ). O software oferece funcionalidades de reserva de horários, gerenciamento de equipamentos, registro de presença. Ele é destinado a diferentes perfis de usuários, como professores, atléticas, alunos, funcionários e comunidade em geral. 💰


## 📊 Diagramas

### Diagrama de Casos de Uso

![Diagrama de Casos de Uso](https://github.com/sidjr2/SGAQ---ODS/blob/master/Diagramas/1%20-%20Diagrama%20casos%20de%20uso.jpg)

##

### Diagrama de Implantação

![Diagrama de Implantação](https://github.com/sidjr2/SGAQ---ODS/blob/master/Diagramas/2%20-%20Diagrama%20de%20implantação.jpg)

##

### Diagrama do Banco de Dados

![Diagrama do Banco de Dados](https://github.com/sidjr2/SGAQ---ODS/blob/master/Diagramas/3%20-%20Diagrama%20do%20banco%20de%20dados.png)


## ⚙️ Instalação

Para instalar o sistema, siga os seguintes passos:

1. Clone o repositório do GitHub:

    ```bash
    git clone https://github.com/sidjr2/SGAQ---ODS
    ```
    
2. Entre na pasta e limpe o projeto:

    ```bash
    cd .\SGAQ---ODS\
    cd .\sgaq5.9\
    cd .\sgaq\
    .\mvnw clean
    ```

3. Alterar as linhas do arquivo "application.properties", para puxar o seu banco de dados:

    ```bash
    spring.datasource.url = jdbc:mysql://localhost:3306/sgaq?
    useTimezone=true&serverTimezone=UTC-3
    spring.datasource.username = sgaq
    spring.datasource.password = 12345
    ```

4. Dentro do projeto execute o seguinte comando para instalar as depedências necessárias: [No Windows]

   ```bash
    .\mvnw install
    ```

5. Rode o projeto:

    ```bash
    .\mvnw spring-boot:run
    ```


## 🌐 Uso

O sistema pode ser acessado no seguinte endereço:

[http://localhost:8080](http://localhost:8080)

Para fazer login, use um usuário e a senha já cadastrado.


## ✔️ Funcionalidades

O sistema oferece as seguintes funcionalidades:

1. *Cadastro de Usuário:* Permite que os usuários façam um cadastro no sistema.
2. *Cadastro de Quadra:* Um usuário administrador poderá cadastrar quadras no sistema.
3. *Cadastro de Equipamentos:* Um usuário administrador poderá cadastrar equipamentos no sistema.
4. *Reservar Quadra:* Os usuários podem fazer a reserva de uma quadra em um horário disponível.
5. *Solicitar Equipamento:* Os usuários podem fazer a reserva de equipamento para sua reserva.
6. *Registro de presença:* Um usuário administrador poderá controlar a presença nas reservas.
7. *Punição:* O sistema aplica automaticamente uma punição quando uma reserva não teve sua presença registrada.
8. *Contatar suporte:* Direciona o usuário ao setor responsável.


## 📄 Documentação

Consulte o arquivo PDF [ODS-SGAQ](https://github.com/sidjr2/SGAQ---ODS/blob/master/Diagramas/SGAQ%20-%20Oficina%20e%20Desenvolvimento%20de%20Software.pdf) incluído neste repositório.


## :memo: Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](https://github.com/sidjr2/SGAQ---ODS/blob/master/LICENCE) para mais detalhes.

---

Feito com ♥ by Davi Kreppel, Gustavo Detomi, Leandro Souza, Matheus Nascimento e Sidney Junior. :wave:
