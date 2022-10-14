package com.virtuslab.phototrip.worldmap.api

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip.worldmap.api.{CreateWorldMap, CurrentWorldMap, GetWorldMap}
import com.virtuslab.phototrip.domain.WorldMap
import kalix.scalasdk.valueentity.{ValueEntity, ValueEntityContext}

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class WorldMapValueEntity(context: ValueEntityContext) extends AbstractWorldMapValueEntity {
  override def emptyState: WorldMap = WorldMap()

  override def create(currentState: WorldMap, createWorldMap: CreateWorldMap): ValueEntity.Effect[Empty] = {
    if (isInitialized(currentState)) {
      effects.error(s"Map with id ${createWorldMap.mapId} already exists")
    } else if(!isValid(createWorldMap)) {
      effects.error(s"Invalid $createWorldMap command")
    } else {
      effects.updateState(toWorldMap(createWorldMap)).thenReply(Empty.defaultInstance)
    }
  }

  override def get(currentState: WorldMap, getWorldMap: GetWorldMap): ValueEntity.Effect[CurrentWorldMap] = {
    if (currentState == emptyState) {
      effects.error(s"Map ${getWorldMap.worldmapId} does not exists")
    } else {
      effects.reply(toCurrentWorldMap(currentState))
    }
  }

  private def isInitialized(currentState: WorldMap): Boolean = {
    currentState != emptyState
  }

  private def isValid(createWorldMap: CreateWorldMap): Boolean = {
    createWorldMap.creatorId.nonEmpty
  }

  private def toCurrentWorldMap(worldMap: WorldMap) = {
    CurrentWorldMap(mapId = worldMap.mapId, creatorId = worldMap.creatorId, description = worldMap.description)
  }

  private def toWorldMap(createWorldMap: CreateWorldMap): WorldMap = {
    WorldMap(mapId = createWorldMap.mapId, creatorId = createWorldMap.creatorId, description = createWorldMap.description)
  }

}

