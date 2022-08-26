package com.virtuslab.phototrip

import com.virtuslab.phototrip.domain.WorldMapValueEntity
import com.virtuslab.phototrip.view.{WorldMapAllView, WorldMapByUserIdView}
import kalix.scalasdk.Kalix
import org.slf4j.LoggerFactory

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

object Main {

  private val log = LoggerFactory.getLogger("com.virtuslab.phototrip.Main")

  def createKalix(): Kalix = {
    // The KalixFactory automatically registers any generated Actions, Views or Entities,
    // and is kept up-to-date with any changes in your protobuf definitions.
    // If you prefer, you may remove this and manually register these components in a
    // `Kalix()` instance.
    KalixFactory.withComponents(
      createWorldMapValueEntity = new WorldMapValueEntity(_),
      createWorldMapAllView = new WorldMapAllView(_),
      createWorldMapByUserIdView = new WorldMapByUserIdView(_)
    )
  }

  def main(args: Array[String]): Unit = {
    log.info("starting the Kalix service")
    createKalix().start()
  }
}
