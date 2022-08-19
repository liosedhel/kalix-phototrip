package com.virtuslab.phototrip.domain

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip
import kalix.scalasdk.testkit.ValueEntityResult
import kalix.scalasdk.valueentity.ValueEntity
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class WorldMapSpec
    extends AnyWordSpec
    with Matchers {

  "WorldMap" must {

    "have example test that can be removed" in {
      val service = WorldMapTestKit(new WorldMap(_))
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

    "handle command create" in {
      val service = WorldMapTestKit(new WorldMap(_))
      pending
      // val result = service.create(phototrip.CreateWorldMap(...))
    }

    "handle command get" in {
      val service = WorldMapTestKit(new WorldMap(_))
      pending
      // val result = service.get(phototrip.GetWorldMap(...))
    }

  }
}
