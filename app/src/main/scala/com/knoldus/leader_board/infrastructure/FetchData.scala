package com.knoldus.leader_board.infrastructure

import java.sql.{Connection, SQLException, Timestamp}

import com.knoldus.leader_board.{AuthorScore, DatabaseConnection, GetAuthorScore, GetScore}
import scalikejdbc._

class FetchData(databaseConnection: DatabaseConnection) {
  implicit val connection: Connection = databaseConnection.connection

  /**
   * Fetches Maximum publication date of blog present in blog table.
   *
   * @return Maximum publication date of blog wrapped in option.
   */
  def fetchMaxBlogPublicationDate: Option[Timestamp] = {
    implicit val session: AutoSession.type = AutoSession
    try {
      sql"SELECT MAX(published_on) FROM blog".map(rs => rs.timestamp(1)).single().apply()
    }
    catch {
      case ex: SQLException => throw new SQLException(ex)
      case ex: Exception => throw new Exception(ex)
    }
    finally {
      session.close()
    }
  }

  /**
   * Fetches overall score of each knolder from all_time table.
   *
   * @return List of GetScore case class objects, which specifies overall score for each knolder.
   */
  def fetchScores: List[GetScore] = {
    implicit val session: AutoSession.type = AutoSession
    try {
      val listOfscores =
        SQL("SELECT knolder_id, overall_score FROM all_time")
          .map(rs => GetScore(rs.int("knolder_id"), rs.int("overall_score"))).list().apply()
      listOfscores.sortBy(getScore => getScore.score).reverse
    }
    catch {
      case ex: SQLException => throw new SQLException(ex)
      case ex: Exception => throw new Exception(ex)
    }
    finally {
      session.close()
    }
  }

  /**
   * Fetches primary key i.e. id in knolder table.
   *
   * @param scores AuthorScore case class object which contains score of each knolder.
   * @return Knolder id wrapped in option.
   */
  def fetchKnolderIdFromKnolder(scores: AuthorScore): Option[Int] = {
    implicit val session: AutoSession.type = AutoSession
    try {
      SQL("SELECT id FROM knolder WHERE wordpress_id = ?").bind(scores.authorLogin)
        .map(rs => rs.int("id")).single().apply()
    }
    catch {
      case ex: SQLException => throw new SQLException(ex)
      case ex: Exception => throw new Exception(ex)
    } finally {
      session.close()
    }
  }

  /**
   * Fetches foreign key i.e. knolder id in all_time table.
   *
   * @param scores   AuthorScore case class object which contains score of each knolder.
   * @param authorId Knolder id wrapped in option.
   * @return Knolder id wrapped in option.
   */
  def fetchKnolderIdFromAllTime(scores: AuthorScore, authorId: Option[Int]): Option[Int] = {
    implicit val session: AutoSession.type = AutoSession
    try {
      SQL("SELECT knolder_id FROM all_time WHERE knolder_id = ?").bind(authorId)
        .map(rs => rs.int("knolder_id")).single().apply()
    }
    catch {
      case ex: SQLException => throw new SQLException(ex)
      case ex: Exception => throw new Exception(ex)
    }
    finally {
      session.close()
    }
  }

  /**
   * Queries all_time table to get full name, overall score and overall rank of each knolder.
   *
   * @return List of GetAuthorScore case class objects, which specifies full name, overall score and
   *         overall rank of each knolder.
   */
  def fetchAllTimeData: List[GetAuthorScore] = {
    implicit val session: AutoSession.type = AutoSession
    try {
      val fetchReputation =
        SQL("SELECT full_name, overall_score, overall_rank FROM all_time " +
          "INNER JOIN knolder ON all_time.knolder_id = knolder.id ORDER BY overall_score ASC")
          .map(rs => GetAuthorScore(rs.string("full_name"), rs.int("overall_score")
            , rs.int("overall_rank"))).list.apply()
      fetchReputation.reverse
    }
    catch {
      case ex: SQLException => throw new SQLException(ex)
      case ex: Exception => throw new Exception(ex)
    }
    finally {
      session.close()
    }
  }
}
