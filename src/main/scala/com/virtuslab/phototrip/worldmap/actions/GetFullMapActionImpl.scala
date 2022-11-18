package com.virtuslab.phototrip.worldmap.actions

import akka.stream.scaladsl.Sink
import com.virtuslab.phototrip.place.view.{ByWorldMapIdRequest, PlaceByMapIdView}
import com.virtuslab.phototrip.worldmap.api.GetWorldMap
import kalix.scalasdk.action.Action
import kalix.scalasdk.action.ActionCreationContext
class GetFullMapActionImpl(creationContext: ActionCreationContext) extends AbstractGetFullMapAction {
  override def get(getFullMap: GetFullMap): Action.Effect[FullWorldMap] = {

    val placesView = actionContext.getGrpcClient(classOf[PlaceByMapIdView], "phototrip")
    val fullWorldMap = for {
      map <- components.worldMapValueEntity.get(GetWorldMap(getFullMap.mapId)).execute()
      places <- placesView.getPlaces(ByWorldMapIdRequest(map.mapId)).runWith(Sink.seq)(actionContext.materializer())
    } yield {
      FullWorldMap(map.mapId, map.creatorId, map.description, places)
    }

    effects.asyncReply(fullWorldMap)
  }

}

