package com.knoldus.leader_board.infrastructure

import java.sql.{Connection, PreparedStatement, Timestamp}
import java.time.Instant

import com.knoldus.leader_board.{DatabaseConnection, KnolderBlog}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterEach, DoNotDiscover}

@DoNotDiscover
class ReadBlogImplSpec extends DBSpec with BeforeAndAfterEach {
  val databaseConnection = new DatabaseConnection(ConfigFactory.load())
  implicit val connection: Connection = databaseConnection.connection
  val readBlog: ReadBlog = new ReadBlogImpl(databaseConnection)

  override def afterEach(): Unit = {
    cleanUpDatabase(connection)
  }

  override protected def beforeEach(): Unit = {
    cleanUpDatabase(connection)
    createTable(connection)
  }

  "ReadBlog" should {

    "return knolders who have written blogs" in {
      val insertKnolder1: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt1: PreparedStatement = connection.prepareStatement(insertKnolder1)
      preparedStmt1.setInt(1, 1)
      preparedStmt1.setString(2, "Mukesh Kumar")
      preparedStmt1.setString(3, "mukesh01")
      preparedStmt1.setString(4, "mukesh.kumar@knoldus.com")
      preparedStmt1.execute
      preparedStmt1.close()

      val insertKnolder2: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt2: PreparedStatement = connection.prepareStatement(insertKnolder2)
      preparedStmt2.setInt(1, 2)
      preparedStmt2.setString(2, "Abhishek Baranwal")
      preparedStmt2.setString(3, "abhishek02")
      preparedStmt2.setString(4, "abhishek.baranwal@knoldus.com")
      preparedStmt2.execute
      preparedStmt2.close()

      val insertKnolder3: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt3: PreparedStatement = connection.prepareStatement(insertKnolder3)
      preparedStmt3.setInt(1, 3)
      preparedStmt3.setString(2, "Komal Rajpal")
      preparedStmt3.setString(3, "komal03")
      preparedStmt3.setString(4, "komal.rajpal@knoldus.com")
      preparedStmt3.execute
      preparedStmt3.close()

      val insertBlog1: String =
        """
          |insert into blog(id, wordpress_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt4: PreparedStatement = connection.prepareStatement(insertBlog1)
      preparedStmt4.setInt(1, 1001)
      preparedStmt4.setString(2, "mukesh01")
      preparedStmt4.setTimestamp(3, Timestamp.from(Instant.parse("2020-04-13T14:56:40Z")))
      preparedStmt4.setString(4, "windows handling using selenium webdriver")
      preparedStmt4.execute
      preparedStmt4.close()

      val insertBlog2: String =
        """
          |insert into blog(id, wordpress_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt5: PreparedStatement = connection.prepareStatement(insertBlog2)
      preparedStmt5.setInt(1, 1002)
      preparedStmt5.setString(2, "abhishek02")
      preparedStmt5.setTimestamp(3, Timestamp.from(Instant.parse("2020-04-13T13:10:40Z")))
      preparedStmt5.setString(4, "ChatOps : Make your life easy")
      preparedStmt5.execute
      preparedStmt5.close()

      val insertBlog3: String =
        """
          |insert into blog(id, wordpress_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt6: PreparedStatement = connection.prepareStatement(insertBlog3)
      preparedStmt6.setInt(1, 1003)
      preparedStmt6.setString(2, "komal03")
      preparedStmt6.setTimestamp(3, Timestamp.from(Instant.parse("2020-04-13T12:57:27Z")))
      preparedStmt6.setString(4, "Automating Windows Controls in Selenium")
      preparedStmt6.execute
      preparedStmt6.close()

      val insertBlog4: String =
        """
          |insert into blog(id, wordpress_id, published_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt7: PreparedStatement = connection.prepareStatement(insertBlog4)
      preparedStmt7.setInt(1, 1004)
      preparedStmt7.setString(2, "mukesh01")
      preparedStmt7.setTimestamp(3, Timestamp.from(Instant.parse("2020-04-13T12:40:20Z")))
      preparedStmt7.setString(4, "Java 9: Enhance your Jav…ptional API enhancement")
      preparedStmt7.execute
      preparedStmt7.close()

      val knoldersWithBlogs = List(KnolderBlog(1001, "mukesh01"), KnolderBlog(1002, "abhishek02"),
        KnolderBlog(1003, "komal03"), KnolderBlog(1004, "mukesh01"))
      val result = readBlog.fetchKnoldersWithBlogs
      result shouldBe knoldersWithBlogs
    }
  }
}