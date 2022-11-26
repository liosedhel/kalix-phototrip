package com.virtuslab.phototrip.analytics

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip.analytics
import kalix.scalasdk.testkit.ValueEntityResult
import kalix.scalasdk.valueentity.ValueEntity
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatsValueEntitySpec
    extends AnyWordSpec
    with Matchers {

  "StatsValueEntity" must {

    "have example test that can be removed" in {
      val service = StatsValueEntityTestKit(new StatsValueEntity(_))
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

    "handle command MapUpdate" in {
      val service = StatsValueEntityTestKit(new StatsValueEntity(_))
      pending
      // val result = service.mapUpdate(NewMap(...))
    }

    "handle command PlaceCreation" in {
      val service = StatsValueEntityTestKit(new StatsValueEntity(_))
      pending
      // val result = service.placeCreation(NewPlace(...))
    }

    "handle command Get" in {
      val service = StatsValueEntityTestKit(new StatsValueEntity(_))
      pending
      // val result = service.get(GetStats(...))
    }

  }
}
