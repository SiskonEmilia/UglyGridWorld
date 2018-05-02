#!/bin/sh
javac -cp .:../lib/junit.jar:../lib/swing-worker-1.1.jar:../lib/appframework-1.0.3.jar:../lib/ImageReader.jar ImageReaderTest.java ImplementImageIO.java ImplementImageProcessor.java
java -cp .:../lib/junit.jar:../lib/swing-worker-1.1.jar:../lib/appframework-1.0.3.jar:../lib/ImageReader.jar org.junit.runner.JUnitCore ImageReaderTest