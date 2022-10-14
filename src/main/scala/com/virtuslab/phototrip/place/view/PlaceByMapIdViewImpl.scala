package com.virtuslab.phototrip.place.view

import com.virtuslab.phototrip.place.api.CurrentPlace
import com.virtuslab.phototrip.place.domain.{PhotoLinkAdded, PlaceCreated}
import kalix.scalasdk.view.View.UpdateEffect
import kalix.scalasdk.view.ViewContext

class PlaceByMapIdViewImpl(context: ViewContext) extends AbstractPlaceByMapIdView {

  override def emptyState: CurrentPlace = CurrentPlace()

  override def processPlaceCreated(
    state: CurrentPlace, placeCreated: PlaceCreated): UpdateEffect[CurrentPlace] =
    effects.updateState(CurrentPlace(placeCreated.placeId, placeCreated.mapId, placeCreated.description, placeCreated.coordinates))

  override def processPhotoLinkAdded(
    state: CurrentPlace, photoLinkAdded: PhotoLinkAdded): UpdateEffect[CurrentPlace] =
    effects.updateState(state.addPhotoLinks(photoLinkAdded.photoLink.get))
}
