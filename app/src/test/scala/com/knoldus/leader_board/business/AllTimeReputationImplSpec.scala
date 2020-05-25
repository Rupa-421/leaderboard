package com.knoldus.leader_board.business

import com.knoldus.leader_board._
import com.knoldus.leader_board.infrastructure._
import org.mockito.MockitoSugar
import org.scalatest.flatspec.AnyFlatSpec

class AllTimeReputationImplSpec extends AnyFlatSpec with MockitoSugar {
  val mockKnolderRank: KnolderRank = mock[KnolderRankImpl]
  val mockKnolderScore: KnolderScore = mock[KnolderScoreImpl]
  val mockReadBlog: ReadBlog = mock[ReadBlogImpl]
  val mockReadAllTimeReputation: ReadAllTimeReputation = mock[ReadAllTimeReputationImpl]
  val allTimeReputation: AllTimeReputation =
    new AllTimeReputationImpl(mockReadBlog, mockKnolderRank, mockKnolderScore, mockReadAllTimeReputation)

  "get all time reputation" should "return knolder reputation of each knolder along with their knolder id" in {
    val scorePerKnolder = List(GetScore(1, "Mukesh Gupta", 10))
    val blogCounts = List(GetCount(1, "Mukesh Gupta", 2))

    when(mockReadBlog.fetchKnoldersWithBlogs)
      .thenReturn(List(GetCount(1, "Mukesh Gupta", 2)))

    when(mockKnolderScore.calculateScore(blogCounts))
      .thenReturn(scorePerKnolder)

    when(mockKnolderRank.calculateRank(scorePerKnolder))
      .thenReturn(List(Reputation(1, "Mukesh Gupta", 10, 1)))

    when(mockReadAllTimeReputation.fetchKnolderIdFromAllTimeReputation(1))
      .thenReturn(Option(1))

    val reputationOfKnolders = List(KnolderReputation(Some(1), Reputation(1, "Mukesh Gupta", 10, 1)))

    assert(allTimeReputation.getKnolderReputation == reputationOfKnolders)
  }
}
