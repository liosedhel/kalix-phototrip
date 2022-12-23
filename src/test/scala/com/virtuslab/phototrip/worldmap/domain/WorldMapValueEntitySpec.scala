package com.virtuslab.phototrip.worldmap.domain

import com.virtuslab.phototrip.worldmap.api.{CreateWorldMap, CurrentWorldMap, GetWorldMap}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class WorldMapValueEntitySpec
    extends AnyWordSpec
    with Matchers {

  "WorldMapValueEntity" must {

    "handle command Create" in {
      val worldMapValueEntity = WorldMapValueEntityTestKit(new WorldMapValueEntity(_))
      val createMessage = CreateWorldMap(mapId = "Map1", creatorId = "Creator1", description = "First map")
      val result = worldMapValueEntity.create(createMessage)
      result.isError shouldBe false
      worldMapValueEntity.currentState() shouldBe WorldMapState(createMessage.mapId, createMessage.creatorId, createMessage.description)
    }

    "handle command Get" in {
      val worldMapValueEntity = WorldMapValueEntityTestKit(new WorldMapValueEntity(_))
      val createMessage = CreateWorldMap(mapId = "Map1", creatorId = "Creator1", description = "First map")
      worldMapValueEntity.create(createMessage)
      val result = worldMapValueEntity.get(GetWorldMap(createMessage.mapId))
      result.reply shouldBe CurrentWorldMap(createMessage.mapId, createMessage.creatorId, createMessage.description)
    }

  }
}
