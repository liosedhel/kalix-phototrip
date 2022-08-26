package com.virtuslab.phototrip

import com.google.protobuf.empty.Empty
import kalix.scalasdk.testkit.KalixTestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import org.scalatest.time.Span
import org.scalatest.wordspec.AnyWordSpec

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class WorldMapServiceIntegrationSpec
    extends AnyWordSpec
    with Matchers
    with BeforeAndAfterAll
    with ScalaFutures {

  implicit private val patience: PatienceConfig =
    PatienceConfig(Span(5, Seconds), Span(500, Millis))

  private val testKit = KalixTestKit(Main.createKalix()).start()

  private val client = testKit.getGrpcClient(classOf[WorldMapService])

  "WorldMapService" must {

    "have example test that can be removed" in {
      val createWorldMap = CreateWorldMap("1", "My new worldmap", "UID1")

      whenReady(client.create(createWorldMap)){ empty =>
        empty shouldBe new Empty()
      }
      whenReady(client.get(GetWorldMap(worldmapId = createWorldMap.worldmapId))) { map =>
        map shouldBe CurrentWorldMap(createWorldMap.worldmapId, createWorldMap.name, createWorldMap.userId)
      }
    }

  }

  override def afterAll(): Unit = {
    testKit.stop()
    super.afterAll()
  }
}
