package com.virtuslab.phototrip.domain

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip
import com.virtuslab.phototrip.CreateWorldMap
import com.virtuslab.phototrip.view.WorldMapByUserIdView
import kalix.scalasdk.valueentity.ValueEntity
import kalix.scalasdk.valueentity.ValueEntityContext

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class WorldMapValueEntity(context: ValueEntityContext) extends AbstractWorldMapValueEntity {
  override def emptyState: WorldMap = WorldMap()

  override def create(currentState: WorldMap, createWorldMap: phototrip.CreateWorldMap): ValueEntity.Effect[Empty] = {
    if (isInitialized(currentState)) {
      effects.error(s"Map with id ${createWorldMap.worldmapId} already exists")
    } else if(!isValid(createWorldMap)) {
      effects.error(s"Invalid $createWorldMap command")
    } else {
      effects.updateState(toWorldMap(createWorldMap)).thenReply(Empty.defaultInstance)
    }
  }

  override def get(currentState: WorldMap, getWorldMap: phototrip.GetWorldMap): ValueEntity.Effect[phototrip.CurrentWorldMap] = {
    if (currentState == emptyState) {
      effects.error(s"Map ${getWorldMap.worldmapId} does not exists")
    } else {
      effects.reply(toCurrentWorldMap(currentState))
    }
  }

  private def isInitialized(currentState: WorldMap): Boolean = {
    currentState != emptyState
  }

  private def isValid(createWorldMap: phototrip.CreateWorldMap): Boolean = {
    createWorldMap.userId.nonEmpty
  }

  private def toCurrentWorldMap(worldMap: WorldMap) = {
    phototrip.CurrentWorldMap(worldMap.id, worldMap.name, worldMap.userId)
  }

  private def toWorldMap(createWorldMap: phototrip.CreateWorldMap): WorldMap = {
    WorldMap(createWorldMap.worldmapId, createWorldMap.name, createWorldMap.userId)
  }

}

