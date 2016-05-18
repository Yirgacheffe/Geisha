//: dal: EventRepository.scala
package dal

import javax.inject.{ Inject, Singleton }

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

import slick.driver.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider

import models.Event
import models.User

import tables.{ Events, Users }


/**
 * Data access layer repository for 'EVENTS'
 *
 * @version 1.0.0 $ 2016-04-27 17:26 $
 */
@Singleton
class EventRepository @Inject()( dbConfigProvider: DatabaseConfigProvider )( implicit ec: ExecutionContext ) {


  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private val events = TableQuery[ Events ]
  private val users  = TableQuery[ Users  ]


  private def eventsTableAutoInc = events returning events.map( _.id )

  def findById( id: Int ) : Future[Option[Event]] = db.run {
    events.filter( _.id === id ).result.headOption
  }


  def findByIdWithUser( id: Int ) : Future[Option[(Event, User)]] = {
    val q = for {
      e <- events
      u <- users
      if e.creator === u.id
    } yield ( e, u )

    db.run {
      q.result.headOption  // Get event join with user, yield event and user name
    }
  }



} //:~
