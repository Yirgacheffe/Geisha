//: dao: EventsDAO.scala
package dao

import scala.concurrent._
import scala.concurrent.duration._

import slick.driver.MySQLDriver.api._

import tables.Events
import tables.Event


/**
 * Database access object related to table 'EVENTS'
 *
 * @version 1.0 $ 2016-03-29 23:53 $
 */

class EventsDAO {


  val db = Database.forConfig( "101_CityEventsDB" )
  lazy val events = TableQuery[Events]

  def exec[T]( action: DBIO[T] ) : T = Await.result( db.run(action), 2 seconds )
  def close() = db.close()


  /**
   * Query event record by page number
   */
  def queryByPageNumber( page: Int = 1 ) : Unit = {

    val pageNumber = if ( page <= 0) { 1 } else { page }

    val recordsIgnored = ( pageNumber - 1 ) * 10
    val pageRecsNumber = 10

    events.drop( recordsIgnored ).take( pageRecsNumber ).result

    close()

  }


} //:~
