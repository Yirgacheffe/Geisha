//: tables: Events.scala
package tables

import java.sql.Timestamp
import slick.driver.MySQLDriver.api._

import models.Event


/**
 * Database entity related to table 'EVENTS'
 *
 * @version 1.0.0 $ 2016-04-27 10:59 $
 */
class Events( tag: Tag ) extends Table[Event]( tag, "EVENTS" ) {


  def id = column[Int]( "ID", O.PrimaryKey, O.AutoInc )

  def title       = column[String]( "TITLE"    )
  def location    = column[String]( "LOCATION" )
  def summary     = column[String]( "SUMMARY"  )

  def cost        = column[Int]( "COST"      )
  def category    = column[Int]( "CATEGORY"  )
  def organizer   = column[Int]( "ORGANIZER" )
  def creator     = column[Int]( "CREATOR"   )

  def startTime   = column[Timestamp]( "START_TIME" )
  def closeTime   = column[Timestamp]( "CLOSE_TIME" )
  def createdTime = column[Timestamp]( "TS_CREATED" )
  def updatedTime = column[Timestamp]( "TS_UPDATED" )


  def * = ( id, title, startTime, closeTime,
    location,
    summary,
    cost,
    category,
    organizer,
    creator, createdTime, updatedTime ) <> ( Event.tupled, Event.unapply )


} //:~
