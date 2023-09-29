# Rinha Backend com Spring Webflux

46k+

## Solução

- [Spring Boot 3.1.4] Última versão
- [Spring Webflux] Trabalhar de forma reativa com as requisições
- [Spring Data JDBC] - Usar diretamente JDBC
- [JDK 20]

## Requisitos

- JDK 17 - Para executar o gatling

## Executar App

```sh
cd config/
docker-compose up --build --force-recreate -d
```

## Executar o Gatling
```sh
curl -o gatling.zip https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/3.9.5/gatling-charts-highcharts-bundle-3.9.5-bundle.zip
unzip gatling.zip
mkdir $HOME/gatling
sudo mv gatling-charts-highcharts-bundle-3.9.5-bundle $HOME/gatling/3.9.5
```

```sh
mkdir $HOME/projects
cd $HOME/projects
git clone https://github.com/zanfranceschi/rinha-de-backend-2023-q3.git
cd rinha-de-backend-2023-q3/stress-test
./run-test.sh
```
## Resultado

Após execução o resultado pode ser obtido no caminho abaixo.

```sh
$HOME/projects/rinha-de-backend-2023-q3/stress-test/user-files/results
```
![Screenshot 2023-09-28 at 20.45.41.png](img%2FScreenshot%202023-09-28%20at%2020.45.41.png)

## Observação

Durante a Rinha Backend foi identificado um **bug** com o _Docker_ e foi sugerido alterar a configuração do **Network** no _docker-compose.yml_ para:

```sh
network_mode: "host"
```

Entretando no Macbook não consegui corrigir o erro(como pode ser percebido no Print).
Ficarei grato pela possível ajuda. Um erro totalmente intermitente, por exemplo, ao iniciar o processo Docker no SO a execução é rápida, caso execute várias vezes seguidas se torna lenta.

Com [Orbstack](https://orbstack.dev) consigo um desempenho melhor, mas ainda não é satisfatório.