# Rodar aplicação sem Nginx

Iniciar aplicação
```cmd
mvn spring-boot:run
```

---

# Rodar aplicação com Nginx

Baixar Nginx localmente (Este procedimento tambem é feito pelo **startNginx.bat**)
```cmd
curl -o nginx.zip https://nginx.org/download/nginx-1.26.0.zip
tar -xf nginx.zip
ren "nginx-1.26.0" "nginx"
```

Iniciar Nginx 
```cmd
.\startNginx.bat
```

Iniciar aplicação
```cmd
mvn spring-boot:run
```

Caso seja necessário alterar **nginx.conf**, rode
```cmd
.\nginx\nginx.exe -s reload
```


> Nota: Recomendável rodar com maven(mvn spring-boot:run) para carregar as JVM environment variables. <br>
> Caso rode sem o maven é aconselhável passar "-Xmx512M -Xms512M -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:+AlwaysPreTouch -XX:+ExitOnOutOfMemoryError" 

> A porta padrão é 9999 <br>
> No dockerfile tem uma config para abrir o monitoramento remoto da JVM, aberto na porta 9010. Pra usar com ferramentas como VisualVM

## Rodar usando duas instancias
Por ter tido um desempenho ruinzinho, deixei um nginx.conf e um docker-compose.yaml com as configs necessárias para rodar.
Talvez fique melhor adicionando o cache fora da aplicação, como um servidor redis, assim daria para fazer as consultas por termo usando cache. Mas vai ficar assim mesmo :D



---


## Variáveis de ambiente
- DATASOURCE_SCHEMA - default cadastro
- DATASOURCE_USERNAME - default root
- DATASOURCE_PASSWORD - default root
- SERVER_MAX_POOL - default 30
- DATABASE_MAX_POOL - default 30
- DATABASE_SEARCH_TERM - default false


## Gerar imagem docker 

Rodar o arquivo batch <br>
Será gerada uma imagem com o nome **cadpessoa**
```cmd
.\generateImage.bat
```


## Baixar gatling usado nos testes de performance
```cmd
cd .\stress-test
curl -o gatling.zip https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/3.9.5/gatling-charts-highcharts-bundle-3.9.5-bundle.zip
tar -xf gatling.zip
ren "gatling-charts-highcharts-bundle-3.9.5" "gatling"
```

## Rodando testes do gatling
Entrar na pasta stress-test <br>
Rodar o arquivo **run-test.ps1**
```cmd
cd \stress-test
.\run-test.ps1
```


## Pontos importantes vistos no processo
Rodando no Windows 10 tive problema com portas efêmeras <br>
para resolver, tive que aumentar a quantidade delas.

> Ao rodar no linux eu vi que essa limitação também existe e precisa ser alterada em certos cenários

Mostrar quantas estão livres para uso no Windows
```cmd
netsh int ipv4 show dynamicport tcp
netsh int ipv4 show dynamicport udp
```

Aumentar(max deve ser 65535)
```cmd
netsh int ipv4 set dynamicport tcp start=10000 num=55000
netsh int ipv4 set dynamicport udp start=10000 num=55000
```

## Explicação das variáveis de JVM usadas
**-XX:+UseG1GC** <br>
habilitar o uso do coletor de lixo G1 (Garbage First

**-XX:+ParallelRefProcEnabled** <br>
significa que o processamento paralelo de referências está habilitado por padrão durante a coleta de lixo na JVM

**-XX:+AlwaysPreTouch** <br>
utilizada para tocar cada página na heap de Java durante a inicialização da JVM. Essa ação carrega todas as páginas na memória antes de entrar no método main(), o que pode ajudar a reduzir a latência causada pela alocação de memória física durante a execução do programa

**-XX:+ExitOnOutOfMemoryError** <br>
faz com que a JVM saia imediatamente quando ocorrer um erro de falta de memória (OutOfMemoryError)
