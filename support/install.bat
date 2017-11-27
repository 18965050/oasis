@echo off
echo [INFO] Install archetype to local repository.

cd %~dp0
cd ..
call mvn clean install -Dmaven.test.skip=true
cd %~dp0
cd archetype
call mvn clean install
pause