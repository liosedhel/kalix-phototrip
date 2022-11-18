package com.virtuslab.phototrip.place.domain

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip.place.api.{AddPhotoLinkUrl, CreateNewPlace, CurrentPlace, GetPlace, PlaceEventSourcedEntity, PlaceEventSourcedEntityTestKit}
import kalix.scalasdk.testkit.EventSourcedResult
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class PlaceEventSourcedEntitySpec extends AnyWordSpec with Matchers {
  "The PlaceEventSourcedEntity" should {
    "have example test that can be removed" in {
      val testKit = PlaceEventSourcedEntityTestKit(new PlaceEventSourcedEntity(_))
      pending
      // use the testkit to execute a command:
      // val result: EventSourcedResult[R] = testKit.someOperation(SomeRequest("id"));
      // verify the emitted events
      // val actualEvent: ExpectedEvent = result.nextEventOfType[ExpectedEvent]
      // actualEvent shouldBe expectedEvent
      // verify the final state after applying the events
      // testKit.state() shouldBe expectedState
      // verify the reply
      // result.reply shouldBe expectedReply
      // verify the final state after the command
    }

    "correctly process commands of type CreatePlace" in {
      val testKit = PlaceEventSourcedEntityTestKit(new PlaceEventSourcedEntity(_))
      val mapId = "Map1"
      val placeId1 = "Place1"
      val createNewPlace1 = CreateNewPlace(placeId1, mapId, "Some description", Some(Coordinates("50.0662522", "19.9415593")))
      val result: EventSourcedResult[Empty] = testKit.createPlace(createNewPlace1)
      val actualEvent = result.nextEvent[PlaceCreated] //TODO KB there is no typesafety, nextEvent(Any)
      actualEvent shouldBe PlaceCreated(createNewPlace1.placeId, createNewPlace1.mapId, createNewPlace1.description, createNewPlace1.coordinates)
    }

    "correctly process commands of type AddPhotoLink" in {
      val testKit = PlaceEventSourcedEntityTestKit(new PlaceEventSourcedEntity(_))
      val placeId1 = "Place1"
      val addPhotoLinkUrl = AddPhotoLinkUrl(placeId1, Some(Url("https://super/photo/link.img")))
      val result = testKit.addPhotoLink(addPhotoLinkUrl)
      val actualEvent = result.nextEvent[PhotoLinkAdded]
      actualEvent shouldBe PhotoLinkAdded(addPhotoLinkUrl.photoLink)
    }

    "correctly process commands of type Get" in {
      val testKit = PlaceEventSourcedEntityTestKit(new PlaceEventSourcedEntity(_))
      val mapId = "Map1"
      val placeId1 = "Place1"
      val createNewPlace1 = CreateNewPlace(placeId1, mapId, "Some description", Some(Coordinates("50.0662522", "19.9415593")))
      testKit.createPlace(createNewPlace1)

      val addPhotoLinkUrl = AddPhotoLinkUrl(placeId1, Some(Url("https://super/photo/link.img")))
      testKit.addPhotoLink(addPhotoLinkUrl)

      val currentPlaceResult = testKit.get(GetPlace(placeId1))
      currentPlaceResult.reply shouldBe CurrentPlace(placeId1, mapId, createNewPlace1.description, createNewPlace1.coordinates, photoLinks = addPhotoLinkUrl.photoLink.toList)
    }
  }
}
