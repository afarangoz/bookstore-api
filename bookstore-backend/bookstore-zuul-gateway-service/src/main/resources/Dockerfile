FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/bookstore-zuul-gateway-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} bookstore-zuul-gateway-service.jar
ENTRYPOINT ["java","-jar","/bookstore-zuul-gateway-service.jar"]