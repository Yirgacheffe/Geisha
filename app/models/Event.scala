//: models: Event.scala
package models

import java.sql.Timestamp


/**
 * Model object related to table 'EVENTS'
 *
 * @version 1.0.0 $ 2016-04-27 13:03 $
 */
case class Event( id: Int = 0,  title: String, startTime: Timestamp, closeTime: Timestamp,
  location : String,
  summary  : String,
  cost     : Int,
  category : Int,
  organizer: Int, creator: Int, createdTime: Timestamp, updatedTime: Timestamp )
