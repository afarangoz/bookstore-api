FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/bookstore-book-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} bookstore-book-service.jar
ENTRYPOINT ["java","-jar","/bookstore-book-service.jar"]