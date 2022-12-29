kalix svc list

kalix logs --raw phototrip

kalix svc components list phototrip

kalix svc components list phototrip

kalix svc c list-entity-ids phototrip com.virtuslab.phototrip.place.api.PlaceService

kalix svc c get-state phototrip com.virtuslab.phototrip.place.api.PlaceService Place1 --output json

kalix svc c list-events phototrip com.virtuslab.phototrip.place.api.PlaceService Place1 --output json

kalix svc views list phototrip

#ROUTE_HOSTNAME="dark-lake-8703.us-east1.kalix.app:443"
#grpcurl $ROUTE_HOSTNAME  com.virtuslab.phototrip.worldmap.view.WorldMapAll/GetAllWorldMaps