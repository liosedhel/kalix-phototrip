package com.virtuslab.phototrip.analytics

import com.google.protobuf.empty.Empty
import kalix.scalasdk.action.Action
import kalix.scalasdk.action.ActionCreationContext

class StatsViewActionImpl(creationContext: ActionCreationContext) extends AbstractStatsViewAction {

  override def getStats(empty: Empty): Action.Effect[Stats] =
    effects.forward(components.statsValueEntity.get(GetStats(StatsValueEntity.key)))

  override def reset(empty: Empty): Action.Effect[Empty] =
    effects.forward(components.statsValueEntity.reset(Empty.defaultInstance))
}
