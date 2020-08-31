package com.knoldus.leader_board.business

import com.knoldus.leader_board.infrastructure.ReadContribution
import com.knoldus.leader_board.{IndianTime, TwelveMonthsScore}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer

class TwelveMonthsContributionImpl(readContribution: ReadContribution) extends TwelveMonthsContribution with LazyLogging {
  /**
   * calculating last twelve months data of specific knolder.
   *
   * @return last twelve month score with the month and year of the specific knolder.
   */

  override def lastTwelveMonthsScore(knolderId: Int, index: Int): Option[List[TwelveMonthsScore]] = {
    logger.info("calculating last twelve months score")

    def calculateMonthsScore(scoreList: ListBuffer[TwelveMonthsScore], monthsIndex: Int, id: Int): Option[List[TwelveMonthsScore]] = {
      if (monthsIndex > 12) {
        Option(scoreList.toList)
      } else {
        val monthValue = IndianTime.currentTime.minusMonths(monthsIndex).getMonthValue
        val monthName = IndianTime.currentTime.minusMonths(monthsIndex).getMonth.toString
        val year = IndianTime.currentTime.minusMonths(monthsIndex).getYear
        val monthScore = readContribution.fetchKnoldersWithTwelveMonthContributions(monthValue, year, id)
        monthScore.flatMap(score =>
          calculateMonthsScore(scoreList :+ TwelveMonthsScore(monthName, year, score._1, score._2, score._3, score._4, score._5, score._6),
            monthsIndex + 1, id))
      }
    }

    calculateMonthsScore(ListBuffer.empty, index, knolderId)
  }
}
