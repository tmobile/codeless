#!/bin/ksh
full_path=`python -c "import os; print(os.path.realpath('$0'))"`
script_path=`dirname "$full_path"`

clear
echo ===============================================
echo Executing suite $1
echo ===============================================
cd "$script_path/../lib"
java -jar codeless_test-0.0.9-jar-with-dependencies.jar -suite="$1" -datasheet="$2"

echo ===============================================
echo Collecting log files
echo ===============================================
cd "$script_path/../"
rm -rf logs
mkdir -p logs
mv -f "$script_path/../lib/etrlink" "$script_path/../logs"
mv -f "$script_path/../lib/console.log" "$script_path/../logs"
mv -f "$script_path/../lib/suites" "$script_path/../logs/debug"
mv -f "$script_path/../lib/target/debug.log" "$script_path/../logs/debug"
mv -f "$script_path/../lib/target/screenshots" "$script_path/../logs"
rm -rf "$script_path/../lib/target"
rm -rf "$script_path/../lib/test-output"
