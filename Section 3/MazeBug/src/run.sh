#!/bin/sh
javac -cp .:../lib/gridworld.jar MazeBug.java MazeBugRunner.java
java -cp .:../lib/gridworld.jar MazeBugRunner