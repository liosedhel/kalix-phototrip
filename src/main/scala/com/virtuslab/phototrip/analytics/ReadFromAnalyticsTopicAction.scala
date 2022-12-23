package com.virtuslab.phototrip.analytics

import com.virtuslab.phototrip.worldmap.actions.PlaceCreatedMessage
import com.virtuslab.phototrip.worldmap.actions.WorldMapUpdatedMessage

import com.google.protobuf.empty.Empty
import kalix.scalasdk.action.Action
import kalix.scalasdk.action.ActionCreationContext
import org.slf4j.LoggerFactory

class ReadFromAnalyticsTopicAction(creationContext: ActionCreationContext)
  extends AbstractReadFromAnalyticsTopicAction {

  private val log = LoggerFactory.getLogger(classOf[ReadFromAnalyticsTopicAction])

  override def placeCreation(placeCreatedMessage: PlaceCreatedMessage): Action.Effect[Empty] = {
    log.info(s"PubSub:Read $placeCreatedMessage")
    effects.forward(
      components.statsValueEntity.placeCreation(NewPlace(StatsValueEntity.key, placeCreatedMessage.placeId))
    )
  }

  override def worldMapUpdate(worldMapUpdatedMessage: WorldMapUpdatedMessage): Action.Effect[Empty] = {
    log.info(s"PubSub:Read $worldMapUpdatedMessage")
    effects.forward(components.statsValueEntity.mapUpdate(NewMap(StatsValueEntity.key, worldMapUpdatedMessage.mapId)))
  }

}
