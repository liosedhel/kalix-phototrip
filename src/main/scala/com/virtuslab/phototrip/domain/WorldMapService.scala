package com.virtuslab.phototrip.domain

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip
import com.virtuslab.phototrip.WorldMapState
import kalix.scalasdk.valueentity.ValueEntity
import kalix.scalasdk.valueentity.ValueEntityContext

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class WorldMapService(context: ValueEntityContext) extends AbstractWorldMapService {
  override def emptyState: WorldMap = WorldMap()

  override def create(currentState: WorldMap, createWorldMap: phototrip.CreateWorldMap): ValueEntity.Effect[Empty] = {
    if (currentState == emptyState) {
      effects.error(s"Map with id ${createWorldMap.worldmapId} already exists")
    } else {
      effects.updateState(WorldMap(createWorldMap.worldmapId, createWorldMap.name)).thenReply(Empty.defaultInstance)
    }
  }

  override def get(currentState: WorldMap, getWorldMap: phototrip.GetWorldMap): ValueEntity.Effect[phototrip.WorldMapState] = {
    if (currentState != emptyState) {
      effects.error(s"Map ${getWorldMap.worldmapId} does not exists")
    } else {
      effects.reply(WorldMapState(currentState.id, currentState.name))
    }
  }

}

