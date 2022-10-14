package com.virtuslab.phototrip.worldmap.domain

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip
import com.virtuslab.phototrip.worldmap.api.WorldMapValueEntity
import kalix.scalasdk.testkit.ValueEntityResult
import kalix.scalasdk.valueentity.ValueEntity
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class WorldMapValueEntitySpec
    extends AnyWordSpec
    with Matchers {

  "WorldMapValueEntity" must {

    "have example test that can be removed" in {
      val service = WorldMapValueEntityTestKit(new WorldMapValueEntity(_))
      pending
      // use the testkit to execute a command
      // and verify final updated state:
      // val result = service.someOperation(SomeRequest)
      // verify the reply
      // val reply = result.getReply()
      // reply shouldBe expectedReply
      // verify the final state after the command
      // service.currentState() shouldBe expectedState
    }

    "handle command Create" in {
      val service = WorldMapValueEntityTestKit(new WorldMapValueEntity(_))
      pending
      // val result = service.create(phototrip.CreateWorldMap(...))
    }

    "handle command Get" in {
      val service = WorldMapValueEntityTestKit(new WorldMapValueEntity(_))
      pending
      // val result = service.get(phototrip.GetWorldMap(...))
    }

  }
}
