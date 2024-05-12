FROM openjdk:17-alpine

# Set the working directory to /app
WORKDIR /app

ARG JAR_FILE=target/*.jar

# Copy the source code to the container - <fonte> <destino>
COPY ${JAR_FILE} ./application.jar

EXPOSE 9010

#Expondo porta para acessar dados da JVM remotamente
#Limitande uso da JVM a 512Mb
ENTRYPOINT ["java", \
"-Xms512m", \
"-Xmx512m", \
"-Dcom.sun.management.jmxremote=true", \
"-Dcom.sun.management.jmxremote.local.only=false", \
"-Dcom.sun.management.jmxremote.authenticate=false", \
"-Dcom.sun.management.jmxremote.ssl=false", \
"-Dcom.sun.management.jmxremote.port=9010", \
"-Dcom.sun.management.jmxremote.rmi.port=9010",\
"-Djava.rmi.server.hostname=localhost", \
"-jar", \
"./application.jar"]

