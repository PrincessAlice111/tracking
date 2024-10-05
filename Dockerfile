FROM eclipse-temurin:22-jdk AS build
RUN apt-get update && apt-get install --yes maven
COPY ./tracking/src /app/src
COPY ./tracking/pom.xml /app/pom.xml
WORKDIR /app
RUN mvn clean install

FROM openjdk:22-jdk
COPY --from=build /app/target/tracking-0.0.1-SNAPSHOT.jar /app/app.jar
COPY ./wallet /app/wallet
WORKDIR /app
ENTRYPOINT [ "java", "-jar", "app.jar" ]
