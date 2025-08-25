
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
# run from root pom.xml so the reactor sees all modules
RUN mvn -q -DskipTests -f /workspace/pom.xml -pl shipping-service -am package

# Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/shipping-service/target/quarkus-app/ /app/
EXPOSE 8084
ENV JAVA_OPTS="-Dquarkus.http.port=8084 -Dquarkus.profile=prod"
CMD ["java","-jar","/app/quarkus-run.jar"]
