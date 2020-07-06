package com.knoldus.leader_board.application

import java.time.Month

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{Directives, ExceptionHandler, Route}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import com.knoldus.leader_board.business.TwelveMonthsContribution
import com.knoldus.leader_board.infrastructure.{FetchKnolderDetails, FetchReputation}
import com.typesafe.config.Config
import com.typesafe.scalalogging._
import net.liftweb.json.Extraction.decompose
import net.liftweb.json.{DefaultFormats, compactRender}

import scala.concurrent.{ExecutionContextExecutor, Future}

class ReputationOnAPIImpl(readContribution: TwelveMonthsContribution, fetchKnolderDetails: FetchKnolderDetails,
                          fetchReputation: FetchReputation, config: Config)
                         (implicit system: ActorSystem, executionContext: ExecutionContextExecutor)
  extends ReputationOnAPI with Directives with CorsDirectives with LazyLogging {
  implicit val formats: DefaultFormats.type = net.liftweb.json.DefaultFormats
  val myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case _: IllegalArgumentException =>
        extractUri { _ =>
          complete(HttpResponse(StatusCodes.InternalServerError))
        }
    }

  /**
   * Displays reputation and details of each knolder on API.
   *
   * @return Http request binded with server port.
   */
  override def displayReputationOnAPI: Future[Http.ServerBinding] = {
    val mainRoute = reputationRoute ~ monthlyDetailsRoute ~ allTimeDetailsRoute ~ twelveMonthsRoute
    Http().bindAndHandle(mainRoute, config.getString("interface"), config.getInt("port"))
      .recoverWith {
        case ex: Exception => Future.failed(new Exception("Service failed", ex))
      }
  }

  /**
   * Displays reputation of each knolder on API.
   *
   * @return Route for displaying reputation of each knolder on API.
   */
  override def reputationRoute: Route = {
    logger.info("Displaying reputation of each knolder on API.")
    cors(settings = CorsSettings.defaultSettings) {
      path("reputation") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`,
            compactRender(decompose(fetchReputation.fetchReputation))))
        }
      }
    }
  }

  /**
   * Displays monthly details of particular knolder on API.
   *
   * @return Route for displaying monthly details of particular knolder on API.
   */
  override def monthlyDetailsRoute: Route = {
    logger.info("Displaying monthly details of particular knolder on API.")
    handleExceptions(myExceptionHandler) {
      cors(settings = CorsSettings.defaultSettings) {
        path("reputation" / IntNumber) { id =>
          parameters("month", "year") { (month, year) =>
            get {
              fetchKnolderDetails.fetchKnolderMonthlyDetails(id, Month.valueOf(month.toUpperCase).getValue, year.toInt) match {
                case Some(value) => complete(HttpEntity(ContentTypes.`application/json`,
                  compactRender(decompose(value))))
                case None => complete(HttpResponse(StatusCodes.NotFound, entity = HttpEntity("invalid resource")))
              }
            }
          }
        }
      }
    }
  }

  /**
   * Displays all time details of particular knolder on API.
   *
   * @return Route for displaying all time details of particular knolder on API.
   */
  override def allTimeDetailsRoute: Route = {
    logger.info("Displaying all time details of particular knolder on API.")
    cors(settings = CorsSettings.defaultSettings) {
      path("reputation" / IntNumber) { id =>
        get {
          fetchKnolderDetails.fetchKnolderAllTimeDetails(id) match {
            case Some(value) => complete(HttpEntity(ContentTypes.`application/json`,
              compactRender(decompose(value))))
            case None => complete(HttpResponse(StatusCodes.NotFound, entity = HttpEntity("invalid resource")))
          }
        }
      }
    }
  }

  /**
   * Displays twelve months details of particular knolder on API.
   *
   * @return Route for displaying twelve months details of particular knolder on API.
   */
  override def twelveMonthsRoute: Route = {
    logger.info("Displaying twelve months details of particular knolder on API.")
    cors(settings = CorsSettings.defaultSettings) {
      path("twelvemonthsreputation" / IntNumber) { id =>
        get {
          readContribution.lastTwelveMonthsScore(id, 1) match {
            case Some(value) => complete(HttpEntity(ContentTypes.`application/json`,
              compactRender(decompose(value))))
            case None => complete(HttpResponse(StatusCodes.NotFound, entity = HttpEntity("invalid resource")))
          }
        }
      }
    }
  }
}
