package com.virtuslab.phototrip.worldmap.view

import com.virtuslab.phototrip.worldmap.domain.WorldMapState
import kalix.scalasdk.view.View.UpdateEffect
import kalix.scalasdk.view.ViewContext

class WorldMapByUserIdView(context: ViewContext) extends AbstractWorldMapByUserIdView {

  override def emptyState: WorldMapView = WorldMapView.defaultInstance

  override def updateWorldMap(state: WorldMapView, worldMapState: WorldMapState): UpdateEffect[WorldMapView] = {
    effects.updateState(WorldMapView(worldMapState.mapId, worldMapState.creatorId, worldMapState.description))
  }
}
