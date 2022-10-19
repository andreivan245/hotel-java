# Hotel Java

## 🏨 Sobre
Esse projeto se trata de um sistema de reserva de Hotel, onde o usuário consegue de forma simples ter um controle completo e detalhado de suas reservas e hóspedes. O banco de dados foi feito utilizando PostgreSQL e hospedado no Heroku de forma gratuita.

## 🛠️ Funcionalidades
- Registro de Reservas e Hóspedes
- Edição de Reservas e Hóspedes existentes
- Excluir todos os tipos de registro
- Busca de Reservas e Hóspesdes registrados

## 🖥️ Tecnologias Utilizadas:

- Java
- Eclipse
- PostgreSQL
- Biblioteca JCalendar
- Plugin WindowBuilder 
- Heroku
- DBeaver

## ⚠️ Importante! ⚠️

☕ Use o Java na versão 8 ou superior para ter compatibilidade. 

O Usuário e a Senha para acessar o sistema:
Usuário = admin
Senha= admin

Para fazer a busca de Reservas deve se utlizar o NÚMERO DE RESERVA.
Para fazer a busca de Hóspedes deve se utlizar o SOBRENOME.


## ⬇️ Instalação


Requer o [Java](https://www.java.com/pt-BR/) instalado e o [Git](https://git-scm.com/).

Para a instalação escolha uma pasta para clonar o repositório e digite o seguinte comando

```sh
git clone https://github.com/andreivan245/Hotel.git
```
E por fim execute o arquivo Hotel.jar


Ou se preferir baixe diretamente [aqui](https://github.com/andreivan245/Hotel/archive/refs/heads/main.zip) o zip do projeto.

Em seguida extraia

E por fim execute o arquivo Hotel.jar


## 💁 Infomações Adicionais

Para deletar algum registro basta ir na aba de busca e selecionar o registro que você queira apagar e em seguida clicar em "DELETAR".

Para editar algum registro basta clicar duas vezes no campo do registro que deseja editar e inserindo o novo valor, após isso com a linha editada ainda selecionada clique em "EDITAR".

Para fazer uma busca é importante verificar a aba a qual você está e inserir no campo de buscar o valor correta ou seja se você estiver na aba "Reservas" dever inserir um número de reserva no campo de pesquisa, se você estiver na aba "Hóspedes" deve inserir o sobrenome no campo de pesquisa.

Para retornar todos os valores das tabelas após fazer uma pesquisa basta clicar no botão "RESTAURAR".

Não é possível alterar os valores dos campos "Número de Reserva" e "Número Hóspedes"

O valor da diária do hotel é por padrão 60, então ao fazer uma reserva o sistema calcula automaticamente o valor total da reserva.