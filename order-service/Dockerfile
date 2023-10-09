FROM maven:3.8.6 AS build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17

COPY target/*.jar app.jar

EXPOSE 9393

ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app.jar"]
