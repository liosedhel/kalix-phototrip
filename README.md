# Photo Trip

## Running on Kalix platform

First, install [Kalix CLI](https://docs.kalix.io/kalix/install-kalix.html)

Build, publish and deploy script:
```bash
#!/bin/sh

sbt Docker/publish -Ddocker.username=liosedhel &&
docker tag liosedhel/phototrip:$1 &&
docker push liosedhel/phototrip:$1 &&

kalix service deploy phototrip liosedhel/phototrip:$1
kalix services expose my-service
```

Management:
```bash
kalix projects list
kalix logs --raw phototrip
kalix svc list
kalix svc components list phototrip
```

For more details about the project see: [https://slides.com/liosedhel/phototrip-with-kalix](https://slides.com/liosedhel/phototrip-with-kalix)

## Running Locally

In order to run your application locally, you must run the Kalix proxy. The included `docker-compose.yml` file contains the configuration required to run the proxy for a locally running application.
It also contains the configuration to start a local Google Pub/Sub emulator that the Kalix proxy will connect to.
To start the proxy, run the following command from this directory:

```
docker-compose up
```

To start the application locally, start it from your IDE or use:

```
sbt run
```

Discover services:
```bash
$ grpcurl -plaintext localhost:9000 list
```

To interact with local phototrip app see: [scripts/create_test_data.sh](scripts/create_test_data.sh)
For example:
```bash
grpcurl -d '{"user_id": "User1", "nick": "kalix", "email": "kalix@kalix.com"}' -plaintext localhost:$PORT com.virtuslab.phototrip.user.api.UserService/Create
```

