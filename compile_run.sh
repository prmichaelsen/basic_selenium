#!bin/bash

# this script only works if you have your dependencies set up the same way
# as below. If this is not the case, please consult ./README.txt

echo Compiling
javac -cp "/selenium/selenium-server-standalone-3.0.1.jar;/selenium/client-combined-3.0.1-nodeps.jar;/JUNIT/junit-4.12.jar;/JUNIT/hamcrest-core-1.3.jar;.;" *.java -d bin -Xlint:deprecation 

echo Running
cd bin
sh run.sh
