FROM openjdk:17-alpine
COPY build/libs/practice2-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]