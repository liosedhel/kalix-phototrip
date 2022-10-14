package com.virtuslab.phototrip.actions

import com.virtuslab.phototrip.GetWorldMap
import kalix.scalasdk.action.Action
import kalix.scalasdk.action.ActionCreationContext

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class GetFullMapActionImpl(creationContext: ActionCreationContext) extends AbstractGetFullMapAction {

  override def get(getFullMap: GetFullMap): Action.Effect[FullWorldMap] = {
    effects.reply(FullWorldMap(getFullMap.mapId))
  }
}

