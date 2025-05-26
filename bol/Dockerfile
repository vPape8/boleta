FROM openjdk:17-jdk-alpine
ARG JAR_FILE=../target/bol-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} bol-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bol-0.0.1-SNAPSHOT.jar"]
# Expose the port the app runs on
EXPOSE 8080