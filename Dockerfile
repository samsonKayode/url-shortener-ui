## STAGE 1: Build the project ##
FROM maven:3.8.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
RUN mvn dependency:go-offline
COPY src /workspace/src
RUN mvn package

##STAGE 2: RUN IT!!!##
FROM openjdk:8-jdk-alpine
COPY --from=build /workspace/target/*.jar url-shortener-frontend.jar
EXPOSE 7001
ENTRYPOINT ["java", "-jar", "url-shortener-frontend.jar"]