# Building and running instructions

## Running

[//]: # (### Using Docker)
[//]: # (```)
[//]: # (docker build . -t calculator:2.0.0)
[//]: # (docker run calculator:2.0.0 -p 8080:8080 --env-file samples/secrets)
[//]: # (```)

### Using JDK 11 & Bash
```
./gradlew build --no-daemon
export $(cat samples/secret) && java -jar build/libs/calculator-2.0.0.jar
```

##Usage
```
TOKEN=`curl -sX POST http://localhost:8080/auth/token -d 'client' -u admin:pass`
curl -sX POST http://localhost:8080/api/v1/calculators/polish-notation -d '+ 1 2' -H "Content-Type: text/plain" -H "Authentication: Bearer $TOKEN"
```

## Notes for production usage
The application relies on TLS termination on a gateway / proxy / ingress.