package com.virtuslab.phototrip.place.api

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip.place.api
import com.virtuslab.phototrip.place.domain.Place
import kalix.scalasdk.eventsourcedentity.EventSourcedEntity
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
      pending
      // val result: EventSourcedResult[Empty] = testKit.createPlace(CreateNewPlace(...))
    }

    "correctly process commands of type AddPhotoLink" in {
      val testKit = PlaceEventSourcedEntityTestKit(new PlaceEventSourcedEntity(_))
      pending
      // val result: EventSourcedResult[Empty] = testKit.addPhotoLink(AddPhotoLinkUrl(...))
    }

    "correctly process commands of type Get" in {
      val testKit = PlaceEventSourcedEntityTestKit(new PlaceEventSourcedEntity(_))
      pending
      // val result: EventSourcedResult[CurrentPlace] = testKit.get(GetPlace(...))
    }
  }
}
