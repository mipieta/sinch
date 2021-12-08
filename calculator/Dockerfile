FROM openjdk:17-slim AS builder
COPY . /usr/calculator
WORKDIR /usr/calculator
RUN ./gradlew build --no-daemon

FROM openjdk:17-slim
RUN mkdir /app
COPY --from=builder /usr/calculator/build/libs/*.jar /app/calculator.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/calculator.jar"]
