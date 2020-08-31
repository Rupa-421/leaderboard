package com.knoldus.leader_board.business

import com.knoldus.leader_board._
import com.knoldus.leader_board.infrastructure._
import org.mockito.MockitoSugar
import org.scalatest.flatspec.AnyFlatSpec

class QuarterlyReputationImplSpec extends AnyFlatSpec with MockitoSugar {
  val mockKnolderScore: KnolderScore = mock[KnolderScoreImpl]
  val mockReadContribution: ReadContribution = mock[ReadContributionImpl]
  val mockReadQuarterlyReputation: ReadQuarterlyReputation = mock[ReadQuarterlyReputationImpl]
  val quarterlyReputation: QuarterlyReputation =
    new QuarterlyReputationImpl(mockReadContribution, mockKnolderScore, mockReadQuarterlyReputation)

  "get quarterly reputation" should "return knolder reputation of each knolder along with their knolder id" in {
    val scoresForFirstMonth = List(GetScore(1, "Mukesh Gupta", 215), GetScore(2, "anjali", 180))
    val firstMonthBlogCount = List(GetContributionCount(1, "Mukesh Gupta", 3, 2,1,1,1,1), GetContributionCount(2, "anjali", 2, 1,1,1,1,1))

    when(mockReadContribution.fetchKnoldersWithQuarterFirstMonthContributions)
      .thenReturn(List(GetContributionCount(1, "Mukesh Gupta", 3, 2,1,1,1,1), GetContributionCount(2, "anjali", 2, 1,1,1,1,1)))
    when(mockKnolderScore.calculateScore(firstMonthBlogCount))
      .thenReturn(scoresForFirstMonth)

    val scoresForSecondMonth = List(GetScore(1, "Mukesh Gupta", 190), GetScore(2, "anjali", 180))
    val secondMonthBlogCount = List(GetContributionCount(1, "Mukesh Gupta", 4, 1,1,1,1,1), GetContributionCount(2, "anjali", 2, 1,1,1,1,1))

    when(mockReadContribution.fetchKnoldersWithQuarterSecondMonthContributions)
      .thenReturn(List(GetContributionCount(1, "Mukesh Gupta", 4, 1,1,1,1,1), GetContributionCount(2, "anjali", 2, 1,1,1,1,1)))
    when(mockKnolderScore.calculateScore(secondMonthBlogCount))
      .thenReturn(scoresForSecondMonth)

    val scoresForThirdMonth = List(GetScore(1, "Mukesh Gupta", 190), GetScore(2, "anjali", 185))
    val thirdMonthBlogCount = List(GetContributionCount(1, "Mukesh Gupta", 4, 1,1,1,1,1), GetContributionCount(2, "anjali", 3, 1,1,1,1,1))

    when(mockReadContribution.fetchKnoldersWithQuarterThirdMonthContributions)
      .thenReturn(List(GetContributionCount(1, "Mukesh Gupta", 4, 1,1,1,1,1), GetContributionCount(2, "anjali", 3, 1,1,1,1,1)))
    when(mockKnolderScore.calculateScore(thirdMonthBlogCount))
      .thenReturn(scoresForThirdMonth)

    when(mockReadQuarterlyReputation.fetchKnolderIdFromQuarterlyReputation(1))
      .thenReturn(Option(1))
    when(mockReadQuarterlyReputation.fetchKnolderIdFromQuarterlyReputation(2))
      .thenReturn(Option(2))

    val reputationOfKnolders = List(KnolderStreak(Some(1), GetStreak(1, "Mukesh Gupta", "190-190-215")),
      KnolderStreak(Some(2), GetStreak(2, "anjali", "185-180-180")))

    assert(quarterlyReputation.getKnolderQuarterlyReputation == reputationOfKnolders)
  }
}
