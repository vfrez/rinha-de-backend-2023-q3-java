@ECHO OFF
TITLE Stop Nginx

ECHO ============================
ECHO Stoping nginx.

cd .\nginx
CALL start .\nginx.exe -s quit

ECHO Nginx Stoped.
ECHO ============================
cd ..

ECHO caso não tenha funcionado use
ECHO @taskkill /f /im nginx.exe