package com.knoldus.leader_board

import akka.actor.ActorSystem
import com.knoldus.leader_board.business._
import com.knoldus.leader_board.infrastructure._
import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging._
import scala.concurrent.ExecutionContextExecutor

object AllPagesBlogsApp extends App with LazyLogging {
  implicit val system: ActorSystem = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val config: Config = ConfigFactory.load()
  val fetchData: FetchData = new FetchDataImpl(config)
  val storeData: StoreData = new StoreDataImpl(config)
  val blogs: Blogs = new BlogsImpl(fetchData, config)
  storeData.insertBlog(blogs.getAllPagesBlogsFromAPI)
  logger.info("Blogs stored successfully!")
}