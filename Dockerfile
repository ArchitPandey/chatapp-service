FROM adoptopenjdk/openjdk11
WORKDIR /app
ARG JAR_FILE=chat-0.0.1-SNAPSHOT.jar
COPY ./target/$JAR_FILE app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]