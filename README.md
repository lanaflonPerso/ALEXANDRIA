# ALEXANDRIA

BUILD :
mvn clean package spring-boot:repackage

RUN :
mvn spring-boot:run

or :
java -jar ALEXANDRIA_M-1.0-SNAPSHOT.jar

TO CHECK LATEST DEPENDENCIES :
mvn versions:display-dependency-updates

TO CHECK LATEST PLUGINS :
mvn versions:display-plugin-updates
