package com.virtuslab.phototrip.analytics

import com.google.protobuf.empty.Empty
import kalix.scalasdk.valueentity.ValueEntity
import kalix.scalasdk.valueentity.ValueEntityContext

object StatsValueEntity {
  val key = "stats"
}

class StatsValueEntity(context: ValueEntityContext) extends AbstractStatsValueEntity {
  override def emptyState: Stats = Stats()

  override def mapUpdate(currentState: Stats, newMap: NewMap): ValueEntity.Effect[Empty] = {
    effects.updateState(currentState.addMaps(newMap.mapId)).thenReply(Empty.defaultInstance)
  }

  override def placeCreation(currentState: Stats, newPlace: NewPlace): ValueEntity.Effect[Empty] =
    effects.updateState(currentState.addPlaces(newPlace.placeId)).thenReply(Empty.defaultInstance)

  override def get(currentState: Stats, getStats: GetStats): ValueEntity.Effect[Stats] =
    effects.reply(currentState)

}

