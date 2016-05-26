//: controllers: EventController.scala
package controllers

import javax.inject._

import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api._
import play.api.mvc._
import scala.concurrent.ExecutionContext

import models.Event
import dal.EventRepository


/**
 * Event controller
 *
 * @version 1.0.0 $ 2016-04-27 22:55 $
 */
class EventController @Inject() ( val eventDal: EventRepository, val messagesApi: MessagesApi )
                                ( implicit ec: ExecutionContext ) extends Controller with I18nSupport {

/*
  def list( page: Int = 1 ) = Action.async {
    repo.list().map {
      case events: Seq[Event] => Ok( views.html.events( "event list" )( events ) )
    }
  }
*/


  def show( id: Int ) = Action.async {
    eventDal.findByIdWithUser( id ).map {
      case Some( (e, u) ) => Ok( views.html.event(e)(u) )
      case None           => Ok( views.html.error( "Can not found event" ) )
    }
  }


  def list( page: Int = 1 ) = Action.async {
    eventDal.listByPage(
      page
    ).map {
      case events: Seq[Event] => Ok( views.html.events(events) )
    }
  }


} //:~
