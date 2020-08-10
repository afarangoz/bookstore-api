FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/bookstore-eureka-server-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} bookstore-eureka-server-service.jar
ENTRYPOINT ["java","-jar","/bookstore-eureka-server-service.jar"]