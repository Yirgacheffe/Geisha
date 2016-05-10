//: controllers: UserController.scala
package controllers

import javax.inject._
import scala.concurrent.Future

import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.ExecutionContext

import models.User
import dal.UserRepository


/**
 * User controller
 *
 * @version 1.0.0 $ 2016-04-26 22:46 $
 */
case class UserData( email: String, password: String, phone: String, name: String, gender: Char,
                     facebook: String,
                     twitter:  String,
                     linkedIn: String, wechat: String )

class UserController @Inject() ( val repo: UserRepository, val messagesApi: MessagesApi )
                               ( implicit ec: ExecutionContext ) extends Controller with I18nSupport {


  val userForm: Form[UserData] = Form {
    mapping(
      "email"    -> nonEmptyText,
      "password" -> nonEmptyText,
      "phone"    -> nonEmptyText,
      "name"     -> nonEmptyText,
      "gender"   -> char,
      "facebook" -> text,
      "twitter"  -> text,
      "linkedIn" -> text,
      "wechat"   -> text
    )( UserData.apply )( UserData.unapply )
  }


  /**
   * Show single user by id
   */
  def show( id: Int ) = Action.async {
    repo.findById( id ).map {
      case user: User => Ok( views.html.user( "It works" )( user ) )
      case None       => Ok( views.html.error( "User with id can not found" ) )
    }
  }


  def list( page: Int = 1 ) = Action.async {
    repo.listByPage( page ).map {
      case users: Seq[User] => Ok( views.html.users( "It works" )( users ) )
    }
  }


  def showCreate() = Action {
    Ok( views.html.userCreate( "It works" )( userForm ) )
  }


  def create() = Action.async { implicit request =>

    userForm.bindFromRequest.fold (

      errorForm => {
        Future.successful( Ok(views.html.userCreate("With errors")(errorForm)) )
      },
      u => { // u is UserData
        repo.create( u.email, u.password,
          u.phone,
          u.name,
          u.gender,
          Some( u.facebook ),
          Some( u.twitter  ),
          Some( u.linkedIn ),
          Some( u.wechat)  ).map { _ => Redirect( routes.UserController.list() )
        }
      }
    )

  }


  def showEdit( id: Int ) = Action.async {

    repo.findById( id ).map {
      case user: User => {

        val userData = UserData(
          user.email,
          user.password,
          user.phone,
          user.name,
          user.gender.abbr,
          user.facebook.getOrElse(""),
          user.twitter.getOrElse(""),
          user.linkedIn.getOrElse(""),
          user.wechat.getOrElse("")
        )

        val editForm = userForm.fill( userData )
        Ok( views.html.userEdit( "It works" )( user.id )( editForm ) )

      }
      case None => Ok( views.html.error( "User with id can not edit" ) )
    }

  }


  def edit( id: Int ) = Action.async { implicit request =>

    userForm.bindFromRequest.fold (
      errorForm => {
        Future.successful( Ok( views.html.userEdit( "With errors" )( id )( errorForm )) )
      },
      u => { // u is UserData
        repo.edit( id, u.name, u.phone, u.name, u.gender,
          Some( u.facebook ),
          Some( u.twitter  ),
          Some( u.linkedIn ),
          Some( u.wechat ) ).map { _ => Redirect( routes.UserController.list() )
        }
      }
    )

  }


  def delete( id: Int ) = Action {
    Redirect( routes.UserController.list() )
  }

} //:~
