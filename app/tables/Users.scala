//: tables: Users.scala
package tables

import java.sql.Timestamp
import slick.driver.MySQLDriver.api._

import models.User
import models.Gender


/**
 * Database entity related to table 'USERS'
 *
 * @version 1.0.0 $ 2016-04-27 00:02 $
 */
class Users( tag: Tag ) extends Table[User]( tag, "USERS" ) {


  def id         = column[Int](    "ID", O.PrimaryKey, O.AutoInc )

  def email      = column[String]( "EMAIL"    )
  def password   = column[String]( "PASSWORD" )
  def phone      = column[String]( "PHONE"    )
  def name       = column[String]( "NAME"     )
  def gender     = column[Gender]( "GENDER"   )

  def facebook   = column[Option[String]]( "FACEBOOK" )
  def twitter    = column[Option[String]]( "TWITTER"  )
  def linkedIn   = column[Option[String]]( "LINKEDIN" )
  def wechat     = column[Option[String]]( "WECHAT"   )

  def registered = column[Timestamp]( "TS_REGISTERED" )
  def updated    = column[Timestamp]( "TS_UPDATED"    )


  def * = ( id, email, password, phone, name, gender,
    facebook,
    twitter,
    linkedIn, wechat, registered, updated ) <> ( User.tupled, User.unapply )


} //:~
