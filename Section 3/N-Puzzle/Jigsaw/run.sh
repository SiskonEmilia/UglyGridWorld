#!/bin/sh
mkdir -p build
javac -encoding UTF-8 -d build -cp src src/Runners/*.java

if [ $1 -eq 0 ]
then
  java -cp build Runners.RunnerDemo
elif [ $1 -eq 1 ]
then
  java -cp build Runners.RunnerPart1
elif [ $1 -eq 2 ]
then
  java -cp build Runners.RunnerPart2
fi

status=$?
if [ $status != 0 ]; then
    echo "Test Fail."
else
    echo "Test Pass."
fi
