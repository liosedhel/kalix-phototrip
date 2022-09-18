package com.virtuslab.phototrip.domain

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip
import com.virtuslab.phototrip.AddPhotoLinkUrl
import kalix.scalasdk.eventsourcedentity.EventSourcedEntity
import kalix.scalasdk.eventsourcedentity.EventSourcedEntityContext

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class PlaceEventSourcedEntity(context: EventSourcedEntityContext) extends AbstractPlaceEventSourcedEntity {
  override def emptyState: Place = Place() //TODO KB_remark the state cannot be modeled as FSM, meaning each time you should check if Place was created or not

  override def createPlace(currentState: Place, createNewPlace: phototrip.CreateNewPlace): EventSourcedEntity.Effect[Empty] =
    effects.emitEvent(
      PlaceCreated(createNewPlace.placeId, createNewPlace.mapId, createNewPlace.description, createNewPlace.coordinates)
    ).thenReply(_ => Empty.defaultInstance)

  override def placeCreated(currentState: Place, placeCreated: PlaceCreated): Place =
    Place(placeCreated.placeId, placeCreated.mapId, placeCreated.description, placeCreated.coordinates, Nil)

  override def addPhotoLink(currentState: Place, addPhotoLink: AddPhotoLinkUrl): EventSourcedEntity.Effect[Empty] =
    effects.emitEvent(PhotoLinkAdded(addPhotoLink.photoLink)).thenReply(_ => Empty.defaultInstance)

  override def photoLinkAdded(currentState: Place, photoLinkAdded: PhotoLinkAdded): Place =
    currentState.addPhotoLinks(photoLinkAdded.photoLink.get)

  override def get(currentState: Place, getPlace: phototrip.GetPlace): EventSourcedEntity.Effect[phototrip.CurrentPlace] =
    effects.reply(phototrip.CurrentPlace(
      placeId = currentState.placeId,
      mapId = currentState.mapId,
      description = currentState.description,
      coordinates = currentState.coordinates,
      photoLinks = currentState.photoLinks)
    )

}
