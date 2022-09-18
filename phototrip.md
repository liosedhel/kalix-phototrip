## Deployment

```bash
#!/bin/sh

sbt Docker/publish -Ddocker.username=liosedhel &&
docker tag liosedhel/phototrip:latest liosedhel/phototrip:$1
docker push liosedhel/phototrip:$1 &&
kalix service deploy phototrip liosedhel/phototrip:$1
```


## Running proxy

```bash
kalix service proxy phototrip --grpcui
```

## Playing around with phototrip

```bash
$ grpcurl -plaintext localhost:8080 list                                        
com.virtuslab.phototrip.WorldMapService
com.virtuslab.phototrip.view.WorldMapAll
com.virtuslab.phototrip.view.WorldMapByUserId
grpc.reflection.v1alpha.ServerReflection
```

```bash
$ grpcurl -plaintext localhost:8080 list com.virtuslab.phototrip.WorldMapService 
com.virtuslab.phototrip.WorldMapService.create
com.virtuslab.phototrip.WorldMapService.get
```

```bash
$ grpcurl -plaintext localhost:8080 describe com.virtuslab.phototrip.CreateWorldMap
com.virtuslab.phototrip.CreateWorldMap is a message:
message CreateWorldMap {
  string worldmap_id = 1 [(.kalix.field) = { entity_key:true }];
  string name = 2;
  string user_id = 3;
}
```

```bash
$ grpcurl -d '{"user_id": "user_1"}' -plaintext localhost:8080 com.virtuslab.phototrip.view.WorldMapByUserId/GetWorldMaps
```

```bash
$ grpcurl -d '{"user_id": "user_1"}' -plaintext localhost:8080 com.virtuslab.phototrip.view.WorldMapByUserId/GetWorldMaps
```