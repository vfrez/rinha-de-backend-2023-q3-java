FROM openjdk:17-alpine

# Set the working directory to /app
WORKDIR /app

ARG JAR_FILE=target/*.jar

# Copy the source code to the container - <fonte> <destino>
COPY ${JAR_FILE} ./application.jar

EXPOSE 9010

#Expondo porta para acessar dados da JVM remotamente
#Limitande uso da JVM a 75% da RAM do host
ENTRYPOINT ["java", \
"-XX:MaxRAMPercentage=75", \
"-XX:+UseG1GC", \
"-XX:+ParallelRefProcEnabled", \
"-XX:+AlwaysPreTouch", \
"-XX:+ExitOnOutOfMemoryError", \
"-jar", \
"./application.jar"]

