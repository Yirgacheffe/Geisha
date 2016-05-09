//: dal: UserRepository.scala
package dal

import javax.inject.{ Inject, Singleton }

import scala.concurrent._
import scala.concurrent.duration._

import slick.driver.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider

import tables.Users
import models.User
import models.Gender


/**
 * Data access layer repository for 'USER'
 *
 * @version 1.0.0 $ 2016-04-27 00:30 $
 */
@Singleton
class UserRepository @Inject() ( dbConfigProvider: DatabaseConfigProvider ) ( implicit ec: ExecutionContext ) {


  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._   // dbConfig.db.run
  import driver.api._ // dbConfig.driver.api._

  private val users = TableQuery[Users]

  // Temprary put here...
  def exec[T]( action: DBIO[T] ) : T = Await.result( db.run(action), 2 seconds )
  def close() = db.close()


  def list() : Future[Seq[User]] = db.run {
    users.result
  }


  def show( id: Int ) : Future[User] = db.run {
    users.filter( _.id === id ).result.head
  }

  def edit( id: Int, email: String, phone: String, name: String, gender: Char,
            facebook: Option[String],
            twitter:  Option[String],
            linkedIn: Option[String],
            wechat:   Option[String] ) : Future[Int] = {

    val query = for {
      u <- users
      if u.id === id
    } yield ( u.email, u.phone, u.name, u.gender, u.facebook, u.twitter, u.linkedIn, u.wechat )

    db.run {
      query.update( email, phone, name, Gender.fromChar(gender), facebook, twitter, linkedIn, wechat )
    }

  }


  def create( email: String, password: String, phone: String, name: String, gender: Char,
              facebook: Option[String],
              twitter:  Option[String],
              linkedIn: Option[String],
              wechat:   Option[String] ) : Future[Int] =  db.run {

    users.map( u => (
      u.email,
      u.password,
      u.phone,
      u.name,
      u.gender,
      u.facebook,
      u.twitter,
      u.linkedIn,
      u.wechat )
    ) += ( email, password, phone, name, Gender.fromChar( gender ), facebook, twitter, linkedIn, wechat )

  }


} //:~
