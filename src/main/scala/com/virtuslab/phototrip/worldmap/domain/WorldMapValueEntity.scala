package com.virtuslab.phototrip.worldmap.domain

import com.virtuslab.phototrip.worldmap.api
import com.virtuslab.phototrip.worldmap.api.UpdateWorldMapDescription

import com.google.protobuf.empty.Empty
import kalix.scalasdk.valueentity.{ ValueEntity, ValueEntityContext }

class WorldMapValueEntity(context: ValueEntityContext) extends AbstractWorldMapValueEntity {
  override def emptyState: WorldMapState = WorldMapState()

  override def create(currentState: WorldMapState, createWorldMap: api.CreateWorldMap): ValueEntity.Effect[Empty] = {
    if (isInitialized(currentState)) {
      effects.error(s"Map with id ${createWorldMap.mapId} already exists")
    } else if (!isValid(createWorldMap)) {
      effects.error(s"Invalid $createWorldMap command")
    } else {
      effects.updateState(toWorldMap(createWorldMap)).thenReply(Empty.defaultInstance)
    }
  }

  override def updateDescription(
    currentState: WorldMapState,
    updateWorldMapDescription: UpdateWorldMapDescription
  ): ValueEntity.Effect[Empty] = {
    effects
      .updateState(currentState.copy(description = updateWorldMapDescription.description))
      .thenReply(Empty.defaultInstance)
  }

  override def get(
    currentState: WorldMapState,
    getWorldMap: api.GetWorldMap
  ): ValueEntity.Effect[api.CurrentWorldMap] = {
    if (currentState == emptyState) {
      effects.error(s"Map ${getWorldMap.mapId} does not exist")
    } else {
      effects.reply(toCurrentWorldMap(currentState))
    }
  }

  private def isInitialized(currentState: WorldMapState): Boolean = currentState != emptyState

  private def isValid(createWorldMap: api.CreateWorldMap): Boolean = createWorldMap.creatorId.nonEmpty

  private def toCurrentWorldMap(worldMap: WorldMapState) =
    api.CurrentWorldMap(mapId = worldMap.mapId, creatorId = worldMap.creatorId, description = worldMap.description)

  private def toWorldMap(createWorldMap: api.CreateWorldMap): WorldMapState = {
    WorldMapState(
      mapId = createWorldMap.mapId,
      creatorId = createWorldMap.creatorId,
      description = createWorldMap.description
    )
  }

}
