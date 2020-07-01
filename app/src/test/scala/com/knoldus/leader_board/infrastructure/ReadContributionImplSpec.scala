package com.knoldus.leader_board.infrastructure

import java.sql.{Connection, PreparedStatement, Timestamp}
import java.time.Instant

import com.knoldus.leader_board.{Constant, DatabaseConnection, GetCount}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterEach, DoNotDiscover}

@DoNotDiscover
class ReadContributionImplSpec extends DBSpec with BeforeAndAfterEach {
  implicit val connection: Connection = DatabaseConnection.connection(ConfigFactory.load())
  val readContribution: ReadContribution = new ReadContributionImpl(ConfigFactory.load())

  override def afterEach(): Unit = {
    cleanUpDatabase(connection)
  }

  override def beforeEach(): Unit = {
    createTable(connection)
  }

  "read contribution" should {
    val currentMonth = Timestamp.valueOf(Constant.CURRENT_TIME
      .withDayOfMonth(1).toLocalDate.atStartOfDay())
    val firstMonth = Timestamp.valueOf(Constant.CURRENT_TIME
      .withDayOfMonth(1).toLocalDate.minusMonths(3).atStartOfDay())
    val secondMonth = Timestamp.valueOf(Constant.CURRENT_TIME
      .withDayOfMonth(1).toLocalDate.minusMonths(2).atStartOfDay())
    val thirdMonth = Timestamp.valueOf(Constant.CURRENT_TIME
      .withDayOfMonth(1).toLocalDate.minusMonths(1).atStartOfDay())

    "return number of contributions of each knolder" in {
      val insertKnolder1: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt1: PreparedStatement = connection.prepareStatement(insertKnolder1)
      preparedStmt1.setInt(1, 1)
      preparedStmt1.setString(2, "Mukesh Kumar")
      preparedStmt1.setString(3, "mukesh01")
      preparedStmt1.setString(4, "mukesh.kumar@knoldus.com")
      preparedStmt1.setBoolean(5, true)
      preparedStmt1.execute
      preparedStmt1.close()

      val insertKnolder2: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt2: PreparedStatement = connection.prepareStatement(insertKnolder2)
      preparedStmt2.setInt(1, 2)
      preparedStmt2.setString(2, "Abhishek Baranwal")
      preparedStmt2.setString(3, "abhishek02")
      preparedStmt2.setString(4, "abhishek.baranwal@knoldus.com")
      preparedStmt2.setBoolean(5, true)
      preparedStmt2.execute
      preparedStmt2.close()

      val insertKnolder3: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt3: PreparedStatement = connection.prepareStatement(insertKnolder3)
      preparedStmt3.setInt(1, 3)
      preparedStmt3.setString(2, "Komal Rajpal")
      preparedStmt3.setString(3, "komal03")
      preparedStmt3.setString(4, "komal.rajpal@knoldus.com")
      preparedStmt3.setBoolean(5, true)
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

      val insertKnolx1: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt8: PreparedStatement = connection.prepareStatement(insertKnolx1)
      preparedStmt8.setInt(1, 1)
      preparedStmt8.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt8.setTimestamp(3, Timestamp.from(Instant.parse("2020-04-13T12:40:20Z")))
      preparedStmt8.setString(4, "Reactive Microservices")
      preparedStmt8.execute
      preparedStmt8.close()

      val insertKnolx2: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt9: PreparedStatement = connection.prepareStatement(insertKnolx2)
      preparedStmt9.setInt(1, 4)
      preparedStmt9.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt9.setTimestamp(3, Timestamp.from(Instant.parse("2020-04-13T12:40:20Z")))
      preparedStmt9.setString(4, "Delta Lake")
      preparedStmt9.execute
      preparedStmt9.close()

      val knoldersWithBlogs = List(GetCount(1, "Mukesh Kumar", 2, 2),
        GetCount(3, "Komal Rajpal", 1, 0),
        GetCount(2, "Abhishek Baranwal", 1, 0))

      val result = readContribution.fetchKnoldersWithContributions
      result shouldBe knoldersWithBlogs
    }

    "return number of contributions of each knolder in current month" in {
      val insertKnolder1: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt1: PreparedStatement = connection.prepareStatement(insertKnolder1)
      preparedStmt1.setInt(1, 1)
      preparedStmt1.setString(2, "Mukesh Kumar")
      preparedStmt1.setString(3, "mukesh01")
      preparedStmt1.setString(4, "mukesh.kumar@knoldus.com")
      preparedStmt1.setBoolean(5, true)
      preparedStmt1.execute
      preparedStmt1.close()

      val insertKnolder2: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt2: PreparedStatement = connection.prepareStatement(insertKnolder2)
      preparedStmt2.setInt(1, 2)
      preparedStmt2.setString(2, "Abhishek Baranwal")
      preparedStmt2.setString(3, "abhishek02")
      preparedStmt2.setString(4, "abhishek.baranwal@knoldus.com")
      preparedStmt2.setBoolean(5, true)
      preparedStmt2.execute
      preparedStmt2.close()

      val insertKnolder3: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt3: PreparedStatement = connection.prepareStatement(insertKnolder3)
      preparedStmt3.setInt(1, 3)
      preparedStmt3.setString(2, "Komal Rajpal")
      preparedStmt3.setString(3, "komal03")
      preparedStmt3.setString(4, "komal.rajpal@knoldus.com")
      preparedStmt3.setBoolean(5, true)
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
      preparedStmt4.setTimestamp(3, currentMonth)
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
      preparedStmt5.setTimestamp(3, currentMonth)
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
      preparedStmt6.setTimestamp(3, currentMonth)
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
      preparedStmt7.setTimestamp(3, currentMonth)
      preparedStmt7.setString(4, "Java 9: Enhance your Jav…ptional API enhancement")
      preparedStmt7.execute
      preparedStmt7.close()

      val insertKnolx1: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt8: PreparedStatement = connection.prepareStatement(insertKnolx1)
      preparedStmt8.setInt(1, 1)
      preparedStmt8.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt8.setTimestamp(3, currentMonth)
      preparedStmt8.setString(4, "Reactive Microservices")
      preparedStmt8.execute
      preparedStmt8.close()

      val insertKnolx2: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt9: PreparedStatement = connection.prepareStatement(insertKnolx2)
      preparedStmt9.setInt(1, 4)
      preparedStmt9.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt9.setTimestamp(3, currentMonth)
      preparedStmt9.setString(4, "Delta Lake")
      preparedStmt9.execute
      preparedStmt9.close()

      val knoldersWithMonthlyBlogs = List(GetCount(1, "Mukesh Kumar", 2, 2),
        GetCount(3, "Komal Rajpal", 1, 0),
        GetCount(2, "Abhishek Baranwal", 1, 0))

      val result = readContribution.fetchKnoldersWithMonthlyContributions
      result shouldBe knoldersWithMonthlyBlogs
    }

    "return number of contributions of each knolder in first month of quarter" in {
      val insertKnolder1: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt1: PreparedStatement = connection.prepareStatement(insertKnolder1)
      preparedStmt1.setInt(1, 1)
      preparedStmt1.setString(2, "Mukesh Kumar")
      preparedStmt1.setString(3, "mukesh01")
      preparedStmt1.setString(4, "mukesh.kumar@knoldus.com")
      preparedStmt1.setBoolean(5, true)
      preparedStmt1.execute
      preparedStmt1.close()

      val insertKnolder2: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt2: PreparedStatement = connection.prepareStatement(insertKnolder2)
      preparedStmt2.setInt(1, 2)
      preparedStmt2.setString(2, "Abhishek Baranwal")
      preparedStmt2.setString(3, "abhishek02")
      preparedStmt2.setString(4, "abhishek.baranwal@knoldus.com")
      preparedStmt2.setBoolean(5, true)
      preparedStmt2.execute
      preparedStmt2.close()

      val insertKnolder3: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt3: PreparedStatement = connection.prepareStatement(insertKnolder3)
      preparedStmt3.setInt(1, 3)
      preparedStmt3.setString(2, "Komal Rajpal")
      preparedStmt3.setString(3, "komal03")
      preparedStmt3.setString(4, "komal.rajpal@knoldus.com")
      preparedStmt3.setBoolean(5, true)
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
      preparedStmt4.setTimestamp(3, firstMonth)
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
      preparedStmt5.setTimestamp(3, firstMonth)
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
      preparedStmt6.setTimestamp(3, firstMonth)
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
      preparedStmt7.setTimestamp(3, firstMonth)
      preparedStmt7.setString(4, "Java 9: Enhance your Jav…ptional API enhancement")
      preparedStmt7.execute
      preparedStmt7.close()

      val insertKnolx1: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt8: PreparedStatement = connection.prepareStatement(insertKnolx1)
      preparedStmt8.setInt(1, 1)
      preparedStmt8.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt8.setTimestamp(3, firstMonth)
      preparedStmt8.setString(4, "Reactive Microservices")
      preparedStmt8.execute
      preparedStmt8.close()

      val insertKnolx2: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt9: PreparedStatement = connection.prepareStatement(insertKnolx2)
      preparedStmt9.setInt(1, 4)
      preparedStmt9.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt9.setTimestamp(3, firstMonth)
      preparedStmt9.setString(4, "Delta Lake")
      preparedStmt9.execute
      preparedStmt9.close()

      val knoldersWithQuarterlyBlogs = List(GetCount(1, "Mukesh Kumar", 2, 2),
        GetCount(3, "Komal Rajpal", 1, 0),
        GetCount(2, "Abhishek Baranwal", 1, 0))

      val result = readContribution.fetchKnoldersWithQuarterFirstMonthContributions
      result shouldBe knoldersWithQuarterlyBlogs
    }

    "return number of contributiobns of each knolder in second month of quarter" in {
      val insertKnolder1: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt1: PreparedStatement = connection.prepareStatement(insertKnolder1)
      preparedStmt1.setInt(1, 1)
      preparedStmt1.setString(2, "Mukesh Kumar")
      preparedStmt1.setString(3, "mukesh01")
      preparedStmt1.setString(4, "mukesh.kumar@knoldus.com")
      preparedStmt1.setBoolean(5, true)
      preparedStmt1.execute
      preparedStmt1.close()

      val insertKnolder2: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt2: PreparedStatement = connection.prepareStatement(insertKnolder2)
      preparedStmt2.setInt(1, 2)
      preparedStmt2.setString(2, "Abhishek Baranwal")
      preparedStmt2.setString(3, "abhishek02")
      preparedStmt2.setString(4, "abhishek.baranwal@knoldus.com")
      preparedStmt2.setBoolean(5, true)
      preparedStmt2.execute
      preparedStmt2.close()

      val insertKnolder3: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt3: PreparedStatement = connection.prepareStatement(insertKnolder3)
      preparedStmt3.setInt(1, 3)
      preparedStmt3.setString(2, "Komal Rajpal")
      preparedStmt3.setString(3, "komal03")
      preparedStmt3.setString(4, "komal.rajpal@knoldus.com")
      preparedStmt3.setBoolean(5, true)
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
      preparedStmt4.setTimestamp(3, secondMonth)
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
      preparedStmt5.setTimestamp(3, secondMonth)
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
      preparedStmt6.setTimestamp(3, secondMonth)
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
      preparedStmt7.setTimestamp(3, secondMonth)
      preparedStmt7.setString(4, "Java 9: Enhance your Jav…ptional API enhancement")
      preparedStmt7.execute
      preparedStmt7.close()

      val insertKnolx1: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt8: PreparedStatement = connection.prepareStatement(insertKnolx1)
      preparedStmt8.setInt(1, 1)
      preparedStmt8.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt8.setTimestamp(3, secondMonth)
      preparedStmt8.setString(4, "Reactive Microservices")
      preparedStmt8.execute
      preparedStmt8.close()

      val insertKnolx2: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt9: PreparedStatement = connection.prepareStatement(insertKnolx2)
      preparedStmt9.setInt(1, 4)
      preparedStmt9.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt9.setTimestamp(3, secondMonth)
      preparedStmt9.setString(4, "Delta Lake")
      preparedStmt9.execute
      preparedStmt9.close()

      val knoldersWithQuarterlyBlogs = List(GetCount(1, "Mukesh Kumar", 2, 2),
        GetCount(3, "Komal Rajpal", 1, 0),
        GetCount(2, "Abhishek Baranwal", 1, 0))

      val result = readContribution.fetchKnoldersWithQuarterSecondMonthContributions
      result shouldBe knoldersWithQuarterlyBlogs
    }

    "return number of contributions of each knolder in third month of quarter" in {
      val insertKnolder1: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt1: PreparedStatement = connection.prepareStatement(insertKnolder1)
      preparedStmt1.setInt(1, 1)
      preparedStmt1.setString(2, "Mukesh Kumar")
      preparedStmt1.setString(3, "mukesh01")
      preparedStmt1.setString(4, "mukesh.kumar@knoldus.com")
      preparedStmt1.setBoolean(5, true)
      preparedStmt1.execute
      preparedStmt1.close()

      val insertKnolder2: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt2: PreparedStatement = connection.prepareStatement(insertKnolder2)
      preparedStmt2.setInt(1, 2)
      preparedStmt2.setString(2, "Abhishek Baranwal")
      preparedStmt2.setString(3, "abhishek02")
      preparedStmt2.setString(4, "abhishek.baranwal@knoldus.com")
      preparedStmt2.setBoolean(5, true)
      preparedStmt2.execute
      preparedStmt2.close()

      val insertKnolder3: String =
        """
          |insert into knolder(id, full_name, wordpress_id, email_id, active_status)
          |values (?,?,?,?,?)
""".stripMargin

      val preparedStmt3: PreparedStatement = connection.prepareStatement(insertKnolder3)
      preparedStmt3.setInt(1, 3)
      preparedStmt3.setString(2, "Komal Rajpal")
      preparedStmt3.setString(3, "komal03")
      preparedStmt3.setString(4, "komal.rajpal@knoldus.com")
      preparedStmt3.setBoolean(5, true)
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
      preparedStmt4.setTimestamp(3, thirdMonth)
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
      preparedStmt5.setTimestamp(3, thirdMonth)
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
      preparedStmt6.setTimestamp(3, thirdMonth)
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
      preparedStmt7.setTimestamp(3, thirdMonth)
      preparedStmt7.setString(4, "Java 9: Enhance your Jav…ptional API enhancement")
      preparedStmt7.execute
      preparedStmt7.close()

      val insertKnolx1: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt8: PreparedStatement = connection.prepareStatement(insertKnolx1)
      preparedStmt8.setInt(1, 1)
      preparedStmt8.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt8.setTimestamp(3, thirdMonth)
      preparedStmt8.setString(4, "Reactive Microservices")
      preparedStmt8.execute
      preparedStmt8.close()

      val insertKnolx2: String =
        """
          |insert into knolx(id, email_id, delivered_on, title)
          |values (?,?,?,?)
""".stripMargin

      val preparedStmt9: PreparedStatement = connection.prepareStatement(insertKnolx2)
      preparedStmt9.setInt(1, 4)
      preparedStmt9.setString(2, "mukesh.kumar@knoldus.com")
      preparedStmt9.setTimestamp(3, thirdMonth)
      preparedStmt9.setString(4, "Delta Lake")
      preparedStmt9.execute
      preparedStmt9.close()

      val knoldersWithQuarterlyBlogs = List(GetCount(1, "Mukesh Kumar", 2, 2),
        GetCount(3, "Komal Rajpal", 1, 0),
        GetCount(2, "Abhishek Baranwal", 1, 0))

      val result = readContribution.fetchKnoldersWithQuarterThirdMonthContributions
      result shouldBe knoldersWithQuarterlyBlogs
    }
  }
}