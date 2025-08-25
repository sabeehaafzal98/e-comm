# Build
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
# run from root pom so reactor builds shared-modules too
RUN mvn -q -DskipTests -f /workspace/pom.xml -pl order-service -am package

# Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/order-service/target/quarkus-app/ /app/
EXPOSE 8082
ENV JAVA_OPTS="-Dquarkus.http.port=8081 -Dquarkus.profile=prod"
CMD ["java","-jar","/app/quarkus-run.jar"]
