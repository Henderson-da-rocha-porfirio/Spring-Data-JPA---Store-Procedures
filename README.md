# FullStack Spring and React
###Entendendo o Fluxo de Execução do Spring ao corrermos ( run ) a nossa aplicação:
#### 1. MAVEN CLASSPATH: Ele começa buscando em nossa classpath as dependências do Maven que foram inicializadas na instalação do projeto como o Spring:
##### a. Se não usarmos outro fornecedor(vendor) ORM ( Object Relational Mapping = processo de mapeamento de uma classe java à tabela do database e seus campos ou membros para as colunas das tabelas existentes. ), ele usará o hibernate por default.
##### b. Busca também pelo connector sql ( ou mysql se estiver usando ).
#### 2. APPLICATION PROPERTIES: Percebe que nós estamos buscando conectar o sql database e busca pelo application.properties.
##### a. E acha as informações relativas a conexão como url, username, password e etc.
#### 3. PACKAGES: Escaneia todos os sub-packages do package principal em que a classe Main estar presente.
##### a. Por isso esta classe precisa estar com a annotation @SpringBootApplication para ela escanear tanto o Package quanto os sub-packages.
#### 4. REPOSITORY: O Spring Boot terá acesso a todas as informações e/ou os dados Spring que possuem a informação para criar a JPA entity manage factory usando as informações de data source que nós informamos para ele na properties.
##### a. Ele sabe como conectar-se e também estabelecer uma conexão ao database.
##### b. Dessa forma, ele criará uma EntityManager que é a classe chave no JPA.
##### c. E quando executa nossa Interface ProdutoRepositorio com o método respository.save, Spring cria internamente uma implementação, uma implementação representante ( proxy ) dessa classe que retornará os métodos invocados em EntityManager.
##### d. Então, quando invocamos métodos como "save" ou um "find one" ou um "delete", internamente, Spring está invocando os métodos de EntityManager através de uma classe que gera em tempo real a implementação nessa interface em particular e executa as operações de gerenciamento necessárias no Database.
##### e. Com isso, evitamos todos os códigos boiler plates ( vários códigos e estruturas ) e configuração ao simplesmente usar Spring Data que simplifica nossas operações de Database para as nossas aplicações.

