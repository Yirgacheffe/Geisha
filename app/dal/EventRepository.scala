//: dal: EventRepository.scala
package dal

import javax.inject.{ Inject, Singleton }
import slick.driver.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.{ Future, ExecutionContext }

import models.Event
import tables.Events


/**
 * Data access layer repository for 'EVENTS'
 *
 * @version 1.0.0 $ 2016-04-27 17:26 $
 */
@Singleton
class EventRepository @Inject() ( val dbConfigProvider: DatabaseConfigProvider ) (implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._


  private val events = TableQuery[Events]

  def list() : Future[Seq[Event]] = db.run {
    events.result
  }

  def show( id: Int ) : Future[Event] = db.run {
    events.filter( _.id === id ).result.head
  }


} //:~
