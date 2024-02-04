FROM openjdk:17
ARG JAR_FILE=target/gil-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]