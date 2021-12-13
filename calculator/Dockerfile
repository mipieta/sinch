FROM openjdk:11-slim AS builder
COPY . /usr/calculator
WORKDIR /usr/calculator
RUN ./gradlew build --no-daemon

FROM openjdk:11-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=builder /usr/calculator/build/libs/*.jar /app/calculator.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/calculator.jar"]
