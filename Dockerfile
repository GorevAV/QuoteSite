FROM openjdk:17-alpine
EXPOSE 8080
ARG JAR_FILE=target/QuoteSite-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} quote-site.jar
ENTRYPOINT ["java","-jar","quote-site.jar"]