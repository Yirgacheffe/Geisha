package tables

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import slick.driver.MySQLDriver.api._

/**
 * Database entity related to table 'ORGANIZERS'
 *
 * @version 1.0 $ 2016-02-03 17:38 $
 *
 */

case class Organizer( id: Int = 0,
  name  : String,
  gender: String, phone: String, email: String, website: Option[String] )


class Organizers( tag: Tag ) extends Table[Organizer]( tag, "ORGANIZERS" ) {

  def id      = column[Int]( "ID", O.PrimaryKey, O.AutoInc )
  def name    = column[String]( "NAME"    )
  def gender  = column[String]( "GENDER"  )
  def phone   = column[String]( "PHONE"   )
  def email   = column[String]( "EMAIL"   )
  def website = column[Option[String]]( "WEBSITE" )

  def * = ( id , name, gender, phone, email, website ) <> ( Organizer.tupled, Organizer.unapply )

} //:~
