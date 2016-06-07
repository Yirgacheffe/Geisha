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

import dal.UserRepository
import models.{ User, Gender }


/**
 * User controller
 *
 * @version 1.0.0 $ 2016-04-26 22:46 $
 */
case class UserEditEvent( phone: String, name: String, gender: Char,
  facebook: String,
  twitter:  String,
  linkedIn: String, wechat: String )

case class RegisterEvent( email: String, password: String )


class UserController @Inject() ( val repo: UserRepository, val messagesApi: MessagesApi )
                               ( implicit ec: ExecutionContext )
  extends Controller with I18nSupport {


  val userEditForm: Form[ UserEditEvent ] = Form {
    mapping(
      "phone"    -> nonEmptyText,
      "name"     -> nonEmptyText,
      "gender"   -> char,
      "facebook" -> text,
      "twitter"  -> text,
      "linkedIn" -> text,
      "wechat"   -> text
    ) ( UserEditEvent.apply )( UserEditEvent.unapply )
  }

  val registerForm: Form[ RegisterEvent ] = Form {
    mapping (
      "email"    -> email,
      "password" -> nonEmptyText
    ) ( RegisterEvent.apply )( RegisterEvent.unapply )
  }


  /**
   * Show single user by id
   */
  def show( id: Int ) = Action.async {
    repo.findById( id ).map {
      case Some(user) => Ok( views.html.user( user ) )
      case None => Ok( views.html.error( "User with id can not found" ) )
    }
  }


  /**
   * Get user list by page
   */
  def list( page: Int = 1 ) = Action.async {
    repo.listByPage(
      page
    ).map {
      case users: Seq[User] => Ok( views.html.users(users) )
    }
  }


  /**
   * Render an user register form
   */
  def showCreate() = Action {
    Ok( views.html.register( registerForm ) )
  }


  /**
   * Register user
   */
  def create() = Action.async { implicit request =>
    registerForm.bindFromRequest.fold (
      errorForm => {
        Future.successful( Ok(views.html.register(errorForm)) )
      },
      u => {
        repo.register(
          u.email,
          u.password
        ).map( _ => Redirect(  routes.UserController.list())  )
      }
    )
  }


  /**
   * Remove a user
   */
  def delete( id: Int ) = Action.async {
    repo.delete( id ).map { _ => Redirect( routes.UserController.list() ) }
  }


  /**
   * Show edit form
   */
  def showEdit( id: Int ) = Action.async {

    repo.findById( id ).map {
      case Some(u) => {

        val userEditData = UserEditEvent(
          u.phone.getOrElse(""),
          u.name.getOrElse(""),
          Gender.toChar(u.gender),
          u.facebook.getOrElse(""),
          u.twitter.getOrElse(""),
          u.linkedIn.getOrElse(""),
          u.wechat.getOrElse("")
        )

        userEditForm.fill( userEditData )
        Ok(views.html.userEdit(u.id.get)(userEditForm))
      }
      case None => Ok( views.html.error( "No user found" ) )
    }

  }


  /**
   * Run edit action
   */
  def edit( id: Int ) = Action.async { implicit request =>

    userEditForm.bindFromRequest.fold (

      errorForm => {
        Future.successful( Ok( views.html.userEdit(id)(errorForm)) )
      },
      u => { // u is UserData
        repo.update( id, u.name, u.phone,
          u.gender,
          u.facebook,
          u.twitter,
          u.linkedIn,
          u.wechat
        ).map { _ => Redirect( routes.UserController.list() ) }
      }

    )
  }


} //:~
