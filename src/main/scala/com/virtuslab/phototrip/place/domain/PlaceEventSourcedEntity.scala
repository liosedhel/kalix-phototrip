package com.virtuslab.phototrip.place.domain

import com.virtuslab.phototrip.place.api

import com.google.protobuf.empty.Empty
import kalix.scalasdk.eventsourcedentity.{ EventSourcedEntity, EventSourcedEntityContext }
import org.slf4j.LoggerFactory

class PlaceEventSourcedEntity(context: EventSourcedEntityContext) extends AbstractPlaceEventSourcedEntity {

  private val log = LoggerFactory.getLogger(classOf[PlaceEventSourcedEntity])
  override def emptyState: Place =
    Place() // TODO KB_remark the state cannot be modeled as FSM, meaning each time you should check if Place was created or not

  override def createPlace(currentState: Place, createNewPlace: api.CreateNewPlace): EventSourcedEntity.Effect[Empty] = {
    if (currentState.placeId != "") {
      effects.reply(Empty.defaultInstance)
    } else {
      effects
        .emitEvent(
          PlaceCreated(
            createNewPlace.placeId,
            createNewPlace.mapId,
            createNewPlace.description,
            createNewPlace.coordinates
          )
        )
        .thenReply(_ => Empty.defaultInstance)
    }
  }

  override def placeCreated(currentState: Place, placeCreated: PlaceCreated): Place =
    Place(placeCreated.placeId, placeCreated.mapId, placeCreated.description, placeCreated.coordinates, Nil)

  override def addPhotoLink(currentState: Place, addPhotoLink: api.AddPhotoLinkUrl): EventSourcedEntity.Effect[Empty] =
    effects.emitEvent(PhotoLinkAdded(addPhotoLink.photoLink)).thenReply(_ => Empty.defaultInstance)

  override def photoLinkAdded(currentState: Place, photoLinkAdded: PhotoLinkAdded): Place =
    currentState.addPhotoLinks(photoLinkAdded.photoLink.get)

  override def get(currentState: Place, getPlace: api.GetPlace): EventSourcedEntity.Effect[api.CurrentPlace] = {
    log.info(s"Current state is: $currentState")
    effects.reply(
      api.CurrentPlace(
        placeId = currentState.placeId,
        mapId = currentState.mapId,
        description = currentState.description,
        coordinates = currentState.coordinates,
        photoLinks = currentState.photoLinks
      )
    )
  }

}
