FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} cards-service.jar
ENTRYPOINT ["java", "-jar", "cards-service.jar"]