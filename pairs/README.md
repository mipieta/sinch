# Building and running instructions

### Using Docker
```
docker build . -t pairs
docker run -i pairs < samples/simple.txt
```
### Using JDK 17
```
./gradlew build --no-daemon
java -jar build/libs/pairs-1.0.0.jar < samples/simple.txt
```
