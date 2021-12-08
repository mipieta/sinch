# Building and running instructions

### Using Docker
```
docker build -t calculator
docker run -i calculator < samples/simple.txt
```
### Using JDK 17
```
./gradlew build --no-daemon
java -jar build/libs/calculator-1.0.0.jar < samples/simple.txt
```
