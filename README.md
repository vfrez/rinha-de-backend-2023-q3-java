Para iniciar rode 
mvn spring-boot:run

A porta padrão é 9999

Variaveis de ambiente, com o padrão
    DATASOURCE_SCHEMA: cadastro
    DATASOURCE_USERNAME: root
    DATASOURCE_PASSWORD: root
    SERVER_MAX_POOL: 30
    DATABASE_MAX_POOL: 30

Para criar a imagem no docker 
rodar o arquivo batch
    [generateImage.bat](generateImage.bat)

no dockerfile tem uma config para abrir o monitoramente remoto da JVM, aberto na porta 9010


para rodar o gatling 
entrar na pasta stress-test
rodar o arquivo
    .\run-test.ps1
Rodando no Windows 10 tive problema com portas efemeras
para resolver, tive que aumentar a quantidade delas.

Mostrar 
    netsh int ipv4 show dynamicport tcp
    netsh int ipv4 show dynamicport udp
Valor padrão no windows 10 que tenho
    Porta Inicial      : 49152
    Número de Portas : 16384
Aumentar(max deve ser 65535)
    netsh int ipv4 set dynamicport tcp start=10000 num=55000
    netsh int ipv4 set dynamicport udp start=10000 num=55000