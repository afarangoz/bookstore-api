FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/bookstore-user-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} bookstore-user-service.jar
ENTRYPOINT ["java","-jar","/bookstore-user-service.jar"]