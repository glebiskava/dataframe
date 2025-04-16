FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/dataframe-1.0-SNAPSHOT-jar-with-dependencies.jar /app/demo.jar
CMD ["java", "-jar", "demo.jar"]
