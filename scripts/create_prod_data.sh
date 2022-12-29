#!/bin/sh

ROUTE_HOSTNAME="dark-lake-8703.us-east1.kalix.app:443"
REST_ROUTE_HOSTNAME="https://${ROUTE_HOSTNAME}"
echo "Creating user"
grpcurl -d '{"user_id": "User1", "nick": "kalix", "email": "kalix@kalix.com"}' $ROUTE_HOSTNAME com.virtuslab.phototrip.user.api.UserService/Create

echo "Creating new Map1"
grpcurl -d '{"map_id": "Map1", "creator_id": "User1", "description": "Map for User1"}' $ROUTE_HOSTNAME com.virtuslab.phototrip.worldmap.api.WorldMapService/Create

echo "Read all maps for User1 from view"
grpcurl -d '{"user_id": "User1"}' $ROUTE_HOSTNAME  com.virtuslab.phototrip.worldmap.view.WorldMapByUserId/GetWorldMaps

echo "Read all maps in the system from view"
grpcurl $ROUTE_HOSTNAME  com.virtuslab.phototrip.worldmap.view.WorldMapAll/GetAllWorldMaps

echo "Create new place Place1 for Map1"
grpcurl -d '{"place_id": "Place1", "map_id": "Map1", "description": "First place on map", "coordinates": {"latitude": "50.0662522", "longitude": "19.9415593"}}' $ROUTE_HOSTNAME  com.virtuslab.phototrip.place.api.PlaceService/CreatePlace

echo "Read Place1 from entity"
grpcurl -d '{"place_id": "Place1"}' $ROUTE_HOSTNAME  com.virtuslab.phototrip.place.api.PlaceService/Get

echo "Create new place Place2 for Map1"
grpcurl -d '{"place_id": "Place2", "map_id": "Map1", "description": "Second place on map", "coordinates": {"latitude": "51.0662522", "longitude": "21.9415593"}}' $ROUTE_HOSTNAME  com.virtuslab.phototrip.place.api.PlaceService/CreatePlace

echo "Read all places from view"
grpcurl -d '{"map_id": "Map1"}' $ROUTE_HOSTNAME  com.virtuslab.phototrip.place.view.PlaceByMapIdView/GetPlaces

echo "Read Map1 with attached places from view"
grpcurl -d '{"map_id": "Map1"}' $ROUTE_HOSTNAME  com.virtuslab.phototrip.worldmap.actions.GetFullMapAction/Get

echo "Read Map1 with attached places from view via http" #join via direct GRPC call to places
curl "${REST_ROUTE_HOSTNAME}/worldmaps-full/Map1"

echo "Read all stats from analytics system"
grpcurl $ROUTE_HOSTNAME  com.virtuslab.phototrip.analytics.StatsViewAction/GetStats

echo "Read all stats from analytics system via http"

curl "${REST_ROUTE_HOSTNAME}/analytics/stats/all"


#update map description
grpcurl -d '{"map_id": "Map1", "description": "Map for User1 description 2"}' $ROUTE_HOSTNAME com.virtuslab.phototrip.worldmap.api.WorldMapService/UpdateDescription

curl -XDELETE "${REST_ROUTE_HOSTNAME}/analytics/stats/reset"