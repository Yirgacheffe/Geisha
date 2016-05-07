//: models: User.scala
package models

import java.sql.Timestamp


/**
 * Model object related to table 'USERS'
 *
 * @version 1.0.0 $ 2016-04-27 00:02 $
 */
case class User( id: Int = 0, email: String, password: String, phone: String, name: String,
  gender:   Gender,
  facebook: Option[String],
  twitter:  Option[String],
  linkedIn: Option[String],
  wechat:   Option[String], registered: Timestamp, updated: Timestamp )
