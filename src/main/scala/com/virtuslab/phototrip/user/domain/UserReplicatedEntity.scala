package com.virtuslab.phototrip.user.domain

import com.virtuslab.phototrip.user.api
import com.virtuslab.phototrip.user.api.CreateNewUser

import com.google.protobuf.empty.Empty
import kalix.scalasdk.replicatedentity.ReplicatedEntity
import kalix.scalasdk.replicatedentity.ReplicatedEntityContext
import kalix.scalasdk.replicatedentity.ReplicatedRegister

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

class UserReplicatedEntity(context: ReplicatedEntityContext) extends AbstractUserReplicatedEntity {

  override def emptyValue: User = User()

  override def create(
    currentData: ReplicatedRegister[User],
    createNewUser: CreateNewUser
  ): ReplicatedEntity.Effect[Empty] = {
    effects
      .update(currentData.set(User(createNewUser.userId, createNewUser.email, createNewUser.nick)))
      .thenReply(Empty.defaultInstance)
  }

  def get(currentData: ReplicatedRegister[User], getUser: api.GetUser): ReplicatedEntity.Effect[User] = {
    currentData.get match {
      case Some(user) => effects.reply(user)
      case None       => effects.error("User does not exist", io.grpc.Status.Code.NOT_FOUND)
    }
  }

}
