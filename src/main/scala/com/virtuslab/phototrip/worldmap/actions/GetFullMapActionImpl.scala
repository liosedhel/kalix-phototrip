package com.virtuslab.phototrip.worldmap.actions

import com.virtuslab.phototrip.place.view.{ ByWorldMapIdRequest, PlaceByMapIdView }
import com.virtuslab.phototrip.worldmap.api.GetWorldMap

import akka.stream.scaladsl.Sink
import kalix.scalasdk.action.Action
import kalix.scalasdk.action.ActionCreationContext

class GetFullMapActionImpl(creationContext: ActionCreationContext, placesService: String)
  extends AbstractGetFullMapAction {

  override def get(getFullMap: GetFullMap): Action.Effect[FullWorldMap] = {

    val placesView = actionContext.getGrpcClient(classOf[PlaceByMapIdView], placesService)
    val fullWorldMap = for {
      map    <- components.worldMapValueEntity.get(GetWorldMap(getFullMap.mapId)).execute()
      places <- placesView.getPlaces(ByWorldMapIdRequest(map.mapId)).runWith(Sink.seq)(actionContext.materializer())
    } yield FullWorldMap(map.mapId, map.creatorId, map.description, places)

    effects.asyncReply(fullWorldMap)
  }

}
