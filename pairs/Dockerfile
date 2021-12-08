FROM openjdk:17-slim AS builder
COPY . /usr/pairs
WORKDIR /usr/pairs
RUN ./gradlew build --no-daemon

FROM openjdk:17-slim
RUN mkdir /app
COPY --from=builder /usr/pairs/build/libs/*.jar /app/pairs.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/pairs.jar"]
