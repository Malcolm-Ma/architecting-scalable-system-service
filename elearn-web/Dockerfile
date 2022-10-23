FROM openjdk:17

EXPOSE 8090

ARG JAR_FILE=target/elearn-web-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
