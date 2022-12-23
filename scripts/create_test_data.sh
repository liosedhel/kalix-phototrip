#!/bin/sh

PORT=${1:-9000}
grpcurl -d '{"map_id": "Map1", "creator_id": "User1", "description": "Map for User1"}' -plaintext localhost:$PORT com.virtuslab.phototrip.worldmap.api.WorldMapService/Create

sleep 1

grpcurl -d '{"user_id": "User1"}' -plaintext localhost:$PORT com.virtuslab.phototrip.worldmap.view.WorldMapByUserId/GetWorldMaps

sleep 1

grpcurl -plaintext localhost:$PORT com.virtuslab.phototrip.worldmap.view.WorldMapAll/GetAllWorldMaps

sleep 1

grpcurl -d '{"place_id": "Place1", "map_id": "Map1", "description": "First place on map", "coordinates": {"latitude": "50.0662522", "longitude": "19.9415593"}}' -plaintext localhost:$PORT com.virtuslab.phototrip.place.api.PlaceService/CreatePlace

sleep 1

grpcurl -d '{"place_id": "Place1"}' -plaintext localhost:$PORT com.virtuslab.phototrip.place.api.PlaceService/Get

sleep 1

grpcurl -d '{"place_id": "Place2", "map_id": "Map1", "description": "Second place on map", "coordinates": {"latitude": "51.0662522", "longitude": "21.9415593"}}' -plaintext localhost:$PORT com.virtuslab.phototrip.place.api.PlaceService/CreatePlace

sleep 1

grpcurl -d '{"map_id": "Map1"}' -plaintext localhost:$PORT com.virtuslab.phototrip.place.view.PlaceByMapIdView/GetPlaces

grpcurl -d '{"map_id": "Map1"}' -plaintext localhost:$PORT  com.virtuslab.phototrip.worldmap.actions.GetFullMapAction/Get

grpcurl -plaintext localhost:$PORT  com.virtuslab.phototrip.analytics.StatsViewAction/GetStats

grpcurl -d '{"map_id": "Map1", "creator_id": "User1", "description": "Map for User1 description 2"}' -plaintext localhost:$PORT com.virtuslab.phototrip.worldmap.api.WorldMapService/UpdateDescription