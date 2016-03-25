### ParkAssist project client code
*******
Author: Kranthi Kiran, Mandar Jamble
Date: Mar, 2016
Steps to install:
- download the code
- Use Eclipse IDE to setup the java project
- Use Eclipse to build the jar spark.jar
- You may directly run using spark.jar

Usage:
*****
java -classpath D:/projects/iot/pi4j-1.0/pi4j-1.0/lib/*.jar;spark.jar com.iot.samples.ExecuteSensor

sudo java -jar spark.jar ReadPirSensor 10,07

sudo java -jar spark.jar multiplesensors 27,23-28,24-29,25