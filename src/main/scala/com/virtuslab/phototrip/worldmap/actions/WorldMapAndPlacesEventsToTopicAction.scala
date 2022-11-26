package com.virtuslab.phototrip.worldmap.actions

import com.virtuslab.phototrip.place.domain.PlaceCreated
import com.virtuslab.phototrip.worldmap.domain.WorldMapState
import kalix.scalasdk.action.Action
import kalix.scalasdk.action.ActionCreationContext
import org.slf4j.LoggerFactory


/**
 * Read events from the store and publish to defined pub-sub
 */
class WorldMapAndPlacesEventsToTopicAction(creationContext: ActionCreationContext)
  extends AbstractWorldMapAndPlacesEventsToTopicAction {

  private val log = LoggerFactory.getLogger(classOf[WorldMapAndPlacesEventsToTopicAction])

  override def placeCreation(placeCreated: PlaceCreated): Action.Effect[PlaceCreatedMessage] = {
    log.info(s"PubSub:Write $placeCreated")
    effects.reply(
      PlaceCreatedMessage(
        placeCreated.placeId,
        placeCreated.mapId,
        placeCreated.description,
        placeCreated.coordinates.map(c => Coordinates(c.latitude, c.latitude))
      )
    )
  }
  override def worldMapUpdate(worldMapState: WorldMapState): Action.Effect[WorldMapUpdatedMessage] = {
    log.info(s"PubSub:Write $worldMapState")
    effects.reply(
      WorldMapUpdatedMessage(
        worldMapState.mapId,
        worldMapState.creatorId,
        worldMapState.description
      )
    )
  }
}

