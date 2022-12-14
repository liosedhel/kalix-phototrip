package com.virtuslab.phototrip

import com.virtuslab.phototrip.analytics.{ ReadFromAnalyticsTopicAction, ReadFromAnalyticsTopicActionProvider, StatsValueEntity, StatsValueEntityProvider, StatsViewActionImpl, StatsViewActionProvider }
import com.virtuslab.phototrip.place.domain.{ PlaceEventSourcedEntity, PlaceEventSourcedEntityProvider }
import com.virtuslab.phototrip.place.view.{ PlaceByMapIdViewImpl, PlaceByMapIdViewProvider }
import com.virtuslab.phototrip.user.domain.{ UserReplicatedEntity, UserReplicatedEntityProvider }
import com.virtuslab.phototrip.worldmap.actions.{ GetFullMapActionImpl, GetFullMapActionProvider, WorldMapAndPlacesEventsToTopicAction, WorldMapAndPlacesEventsToTopicActionProvider }
import com.virtuslab.phototrip.worldmap.domain.{ WorldMapValueEntity, WorldMapValueEntityProvider }
import com.virtuslab.phototrip.worldmap.view.{ WorldMapAllView, WorldMapAllViewProvider, WorldMapByUserIdView, WorldMapByUserIdViewProvider }

import kalix.scalasdk.Kalix
import org.slf4j.LoggerFactory

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

object Main {

  private val log = LoggerFactory.getLogger("com.virtuslab.phototrip.Main")

  def createKalix(): Kalix = {
    Kalix()
      .register(WorldMapValueEntityProvider(new WorldMapValueEntity(_)))
      .register(PlaceEventSourcedEntityProvider(new PlaceEventSourcedEntity(_)))
      // views
      .register(WorldMapAllViewProvider(new WorldMapAllView(_)).withViewId("world-map-all-v3"))
      .register(WorldMapByUserIdViewProvider(new WorldMapByUserIdView(_)).withViewId("world-map-by-user-id-v3"))
      .register(PlaceByMapIdViewProvider(new PlaceByMapIdViewImpl(_)).withViewId("place-by-map-id-v2"))
      // actions
      .register(GetFullMapActionProvider(new GetFullMapActionImpl(_, "phototrip")))
      // pubsusb
      .register(WorldMapAndPlacesEventsToTopicActionProvider(new WorldMapAndPlacesEventsToTopicAction(_)))
      // analytics
      .register(ReadFromAnalyticsTopicActionProvider(new ReadFromAnalyticsTopicAction(_)))
      .register(StatsValueEntityProvider(new StatsValueEntity(_)))
      .register(StatsViewActionProvider(new StatsViewActionImpl(_)))
      .register(UserReplicatedEntityProvider(new UserReplicatedEntity(_)))
  }

  def main(args: Array[String]): Unit = {
    log.info("starting the Kalix service")
    createKalix().start()
  }

}
