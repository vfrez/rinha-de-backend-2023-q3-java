@ECHO OFF
TITLE Reload Nginx


ECHO ============================
ECHO Reloading nginx.

cd .\nginx
CALL nginx.exe -s reload

ECHO Nginx reloaded.
ECHO ============================
cd ..