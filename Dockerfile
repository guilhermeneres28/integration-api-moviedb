FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-desafio-clickbus-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/api-desafio-clickbus-0.0.1-SNAPSHOT.jar"]