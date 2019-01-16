echo OFF
set current_dir="%CD%"
set script_path="%~dp0"

cls
echo ===============================================
echo Executing suite %1
echo ===============================================
cd "%script_path%\..\lib"
java "-DdataSheet=" -jar codeless_test-0.0.3-SNAPSHOT-jar-with-dependencies.jar -suite="%1"

echo ===============================================
echo Collecting log files
echo ===============================================
cd "%script_path%\..\"
rmdir /s /q logs
md logs
move /Y "%script_path%\..\lib\etrlink" "%script_path%\..\logs"
move /Y "%script_path%\..\lib\console.log" "%script_path%\..\logs"
move /Y "%script_path%\..\lib\suites" "%script_path%\..\logs\debug"
move /Y "%script_path%\..\lib\target\debug.log" "%script_path%\..\logs\debug"
move /Y "%script_path%\..\lib\target\screenshots" "%script_path%\..\logs"
rmdir "%script_path%\..\lib\target" /S /Q
rmdir "%script_path%\..\lib\test-output" /S /Q
cd "%current_dir%"