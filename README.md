# Rinha Backend com Spring Webflux

46k+

## Solu��o

- [Spring Boot 3.1.4] �ltima vers�o
- [Spring Webflux] Trabalhar de forma reativa com as requisi��es
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

Ap�s execu��o o resultado pode ser obtido no caminho abaixo.

```sh
$HOME/projects/rinha-de-backend-2023-q3/stress-test/user-files/results
```
![Screenshot 2023-09-28 at 20.45.41.png](img%2FScreenshot%202023-09-28%20at%2020.45.41.png)

## Observa��o

Durante a Rinha Backend foi identificado um **bug** com o _Docker_ e foi sugerido alterar a configura��o do **Network** no _docker-compose.yml_ para:

```sh
network_mode: "host"
```

Entretando no Macbook n�o consegui corrigir o erro(como pode ser percebido no Print).
Ficarei grato pela poss�vel ajuda. Um erro totalmente intermitente, por exemplo, ao iniciar o processo Docker no SO a execu��o � r�pida, caso execute v�rias vezes seguidas se torna lenta.

Com [Orbstack](https://orbstack.dev) consigo um desempenho melhor, mas ainda n�o � satisfat�rio.