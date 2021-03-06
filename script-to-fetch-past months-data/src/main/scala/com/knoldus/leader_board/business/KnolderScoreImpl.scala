package com.knoldus.leader_board.business

import com.knoldus.leader_board.{GetContributionCount, GetScore}
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

class KnolderScoreImpl(config: Config) extends KnolderScore with LazyLogging {
  /**
   * Calculates score of each knolder.
   *
   * @return List of scores of knolders.
   */
  override def calculateScore(counts: List[GetContributionCount]): List[GetScore] = {
    logger.info("Calculating score of each knolder.")
    val scorePerBlog = config.getInt("scorePerBlog")
    val scorePerWebinar = config.getInt("scorePerWebinar")
    val scorePerKnolx = config.getInt("scorePerKnolx")
    val scorePerTechHub = config.getInt("scorePerTechHub")
    val scorePerOsContribution = config.getInt("scorePerOsContribution")
    val scorePerConference = config.getInt("scorePerConference")
    val scorePerBook = config.getInt("scorePerBook")
    val scorePerResearchPaper = config.getInt("scorePerResearchPaper")

    counts.map(count => GetScore(count.knolderId, count.knolderName,
      count.numberOfBlogs * scorePerBlog +
        (count.numberOfKnolx * scorePerKnolx) + (count.numberOfWebinar * scorePerWebinar) + (count.numberOfTechHub * scorePerTechHub)
        + (count.numberOfOSContribution * scorePerOsContribution) + (count.numberOfConferences * scorePerConference)
        + (count.numberOfBooks * scorePerBook) + (count.numberOfResearchPapers * scorePerResearchPaper)))
      .sortBy(knolder => knolder.score).reverse
  }
}
