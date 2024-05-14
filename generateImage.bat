@ECHO OFF
TITLE Generate Docker Image

ECHO Generate Docker Image.

ECHO ============================
ECHO Packing application.

ECHO
CALL mvn clean package -DskipTests
ECHO

ECHO Packed application.
ECHO ============================

ECHO ============================
ECHO Build and generate image.

ECHO
CALL docker build -t cadpessoa:0.0.1 .
ECHO

ECHO Image generated.
ECHO ============================

CALL docker images | findstr cadpessoa