@ECHO OFF
TITLE Initiate Nginx

ECHO ============================
ECHO Download if not exists on project.

DIR .\nginx > NUL

IF %ERRORLEVEL% EQU 0 (
    ECHO Ja existe Nginx no projeto local
    ECHO ============================
) ELSE (
    CALL curl -o nginx.zip https://nginx.org/download/nginx-1.26.0.zip
    CALL tar -xf nginx.zip
    CALL ren "nginx-1.26.0" "nginx"
    CALL del nginx.zip

    ECHO Nginx Downloaded.
    ECHO ============================
)



ECHO ============================
ECHO Starting nginx.

cd .\nginx
CALL start .\nginx.exe -c .\..\nginx.conf

CALL .\nginx.exe -t

ECHO Nginx started.
ECHO ============================
cd ..