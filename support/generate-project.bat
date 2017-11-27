@echo off
echo [INFO] Generating project in ../generated-projects

cd %~dp0
cd ..
if not exist  generated-projects mkdir generated-projects
cd generated-projects
call mvn archetype:generate -DarchetypeGroupId=cn.xyz -DarchetypeArtifactId=chaos-web-quickstart-archetype -DarchetypeVersion=0.2.0

pause
