@ECHO OFF
TITLE Generate Docker Image

ECHO Generate Docker Image.

ECHO ============================
ECHO Packing application.
ECHO ============================

ECHO
CALL mvn clean package -DskipTests
ECHO

ECHO ============================
ECHO Build and generate image.
ECHO ============================

ECHO
CALL docker build -t cadpessoa:0.0.1 .
ECHO

ECHO Finished generation.
ECHO

CALL docker images | findstr cadpessoa

PAUSE