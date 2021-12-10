Tutorial do repositório oficial.
https://spring.io/guides/gs/spring-boot-docker/

Para execução da apliicação basta copiar o arquivo docker-compose.yml que está no diretório docker/docker-compose-files para a pasta raiz do projeto e remover o nome de ambiente no arquivo. (-dev, -prod e -hom)

Copie também o Dokerfile diretório docker/server/ para a pasta raiz do projeto e remover o nome de ambiente no arquivo.

Você deve também ir no diretório docker/environments e copiar o arquivo de configuração para a pasta resuources dentro de application, após a copia remova o nome de ambiente.

No diretório raiz do projeto, execute o comando:

## docker-compose up

-- Para conectar no workbanch basta segui os seguintes comando:

## docker exec -it mysql-api-contrate /bin/bash
- entre no container do mysql
##  mysql -uroot -p -A
- entre no mysql
##  SENHA NO ARQUIVO DOCKER-COMOPOSE
- digite a senha acima
##  select user ,host from mysql.user;
- verifique se usuário root está localhost
##  update mysql.user set host='%' where user='root';
- altere para '%'
##  flush privileges;
- reset
##  exit
- volte para o mysql
##  exit
- volte para o container
##  mysql -uroot -p -P3306 -h127.0.0.1
- disponibilie o localhost
##  SENHA NO ARQUIVO DOCKER-COMOPOSE
- digite a senha