package com.virtuslab.phototrip.worldmap.actions

import com.virtuslab.phototrip.place.domain.PlaceCreated
import kalix.scalasdk.action.Action
import kalix.scalasdk.testkit.ActionResult
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class WorldmapAndPlacesEventsToTopicActionSpec
    extends AnyWordSpec
    with Matchers {

  "WorldmapAndPlacesEventsToTopicAction" must {

    "have example test that can be removed" in {
      val service = WorldmapAndPlacesEventsToTopicActionTestKit(new WorldmapAndPlacesEventsToTopicAction(_))
      pending
      // use the testkit to execute a command
      // and verify final updated state:
      // val result = service.someOperation(SomeRequest)
      // verify the reply
      // result.reply shouldBe expectedReply
    }

    "handle command PlaceCreation" in {
      val service = WorldmapAndPlacesEventsToTopicActionTestKit(new WorldmapAndPlacesEventsToTopicAction(_))
          pending
      // val result = service.placeCreation(PlaceCreated(...))
    }

  }
}
