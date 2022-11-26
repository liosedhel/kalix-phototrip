package com.virtuslab.phototrip

import com.google.protobuf.empty.Empty
import com.virtuslab.phototrip.worldmap.api.{CreateWorldMap, CurrentWorldMap, GetWorldMap, WorldMapService}
import kalix.scalasdk.testkit.KalixTestKit
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec

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

      whenReady(client.create(createWorldMap)) { empty =>
        empty shouldBe new Empty()
      }
      whenReady(client.get(GetWorldMap(worldmapId = createWorldMap.mapId))) { map =>
        map shouldBe CurrentWorldMap(createWorldMap.mapId, createWorldMap.creatorId, createWorldMap.description)
      }
    }

  }

  override def afterAll(): Unit = {
    testKit.stop()
    super.afterAll()
  }
}
