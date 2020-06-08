package com.knoldus.leader_board.business

import akka.actor.Actor
import com.knoldus.leader_board.infrastructure.WriteAllTimeReputation
import com.typesafe.scalalogging.LazyLogging

class AllTimeReputationActor(allTimeReputation: AllTimeReputation,
                             writeAllTimeReputation: WriteAllTimeReputation) extends Actor with LazyLogging {
  override def receive: Receive = {
    case "write all time reputation" => val allTimeReputations = allTimeReputation.getKnolderReputation
      logger.info("Storing or updating all time reputation of knolder.")
      writeAllTimeReputation.insertAllTimeReputationData(allTimeReputations)
      writeAllTimeReputation.updateAllTimeReputationData(allTimeReputations)
      sender() ! "all time reputation saved"
    case _ => sender() ! "invalid message"
  }
}
