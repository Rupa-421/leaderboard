package com.knoldus.leader_board.business

import com.knoldus.leader_board.infrastructure.FetchData
import com.knoldus.leader_board.{GetRank, GetScore}

class OverallRankImpl(fetchData: FetchData) extends OverallRank {

  /**
   * Calculates overall rank of each knolder based on their overall score.
   *
   * @return List of GetRank case class objects, which specifies overall rank for each knolder.
   */
  override def calculateRank: Vector[GetRank] = {
    val listOfScores: List[GetScore] = fetchData.fetchScores
    val rankList = Vector.empty[GetRank]

    @scala.annotation.tailrec
    def getRank(listOfScores: List[GetScore], rankList: Vector[GetRank], rankListIndex: Int): Vector[GetRank] = {
      listOfScores match {
        case Nil => rankList
        case _ :: Nil => rankList
        case first :: second :: Nil =>
          if (second.score != first.score) {
            rankList :+ GetRank(second.knolderId, rankList(rankListIndex).rank + 1)
          }
          else {
            rankList :+ GetRank(second.knolderId, rankList(rankListIndex).rank)
          }
        case first :: second :: rest =>
          if (second.score != first.score) {
            getRank(second :: rest, rankList :+ GetRank(second.knolderId, rankList(rankListIndex).rank + 1),
              rankListIndex + 1)
          }
          else {
            getRank(second :: rest, rankList :+ GetRank(second.knolderId, rankList(rankListIndex).rank),
              rankListIndex + 1)
          }
      }
    }

    val firstKnolderId = listOfScores match {
      case Nil => 0
      case knolder :: Nil => knolder.knolderId
      case knolder :: _ => knolder.knolderId
    }
    getRank(listOfScores, rankList :+ GetRank(firstKnolderId, 1), 0)
  }
}