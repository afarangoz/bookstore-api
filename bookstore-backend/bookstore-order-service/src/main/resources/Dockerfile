FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/bookstore-order-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} bookstore-order-service.jar
ENTRYPOINT ["java","-jar","/bookstore-order-service.jar"]