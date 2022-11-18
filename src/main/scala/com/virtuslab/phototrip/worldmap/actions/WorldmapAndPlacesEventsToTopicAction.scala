package com.virtuslab.phototrip.worldmap.actions

import com.virtuslab.phototrip.place.domain.PlaceCreated
import kalix.scalasdk.action.Action
import kalix.scalasdk.action.ActionCreationContext

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class WorldmapAndPlacesEventsToTopicAction(creationContext: ActionCreationContext) extends AbstractWorldmapAndPlacesEventsToTopicAction {

  override def placeCreation(placeCreated: PlaceCreated): Action.Effect[PlaceCreated] = {
    effects.reply(placeCreated)
  }
}

