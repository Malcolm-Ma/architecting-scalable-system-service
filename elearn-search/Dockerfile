FROM openjdk:17

EXPOSE 8091

ARG JAR_FILE=target/elearn-search-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]

#"-Dspring.profiles.active=prod"