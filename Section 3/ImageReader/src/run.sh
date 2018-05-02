#!/bin/sh
javac -cp .:../lib/appframework-1.0.3.jar:../lib/ImageReader.jar:../lib/swing-worker-1.1.jar ImageReaderRunner.java ImplementImageIO.java ImplementImageProcessor.java
java -cp .:../lib/appframework-1.0.3.jar:../lib/ImageReader.jar:../lib/swing-worker-1.1.jar ImageReaderRunner