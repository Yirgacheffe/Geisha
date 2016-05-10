//: dal: UserRepository.scala
package dal

import javax.inject.{Inject, Singleton}

import scala.concurrent._
import scala.concurrent.duration._
import slick.driver.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import tables.Users
import models.{Gender, User}


/**
 * Data access layer repository for 'USER'
 *
 * @version 1.0.0 $ 2016-04-27 00:30 $
 */
@Singleton
class UserRepository @Inject()( dbConfigProvider: DatabaseConfigProvider )( implicit ec: ExecutionContext ) {


  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._   // dbConfig.db.run
  import driver.api._ // dbConfig.driver.api._


  // Temprary put here...
  // def exec[T]( action: DBIO[T] ) : T = Await.result( db.run(action), 2 seconds )
  // def close() = db.close()


  private val users = TableQuery[Users]
  private def usersTableAutoInc = users returning users.map( _.id )

  private val rowNum = 5


  /**
    * Pagnation list users data
    */
  def listByPage( page: Int = 1 ) : Future[ Seq[User] ] = db.run {
    val pageNum = if ( page <=0 ) 1 else page
    users.drop( (pageNum - 1) * rowNum ).take( rowNum + 1 ).result
  }


  /**
   * Common filter query by id
   */
  def filterQuery( id: Int ) : Query[Users, User, Seq] = {
    users.filter( _.id === id )
  }


  /**
   * Only email and password will be provide for register
   */
  def register( email: String, password: String ) : Future[Int] = db.run {
    users.map(
      u => (u.email, u.password) ) += ( email, password )
  }


  /**
   * Show single user
   */
  def findById( id: Int ) : Future[Option[User]] = db.run {
    filterQuery( id ).result.headOption
  }


  /**
   * Remove user by id
   */
  def delete( id: Int ) : Future[Int] = db.run {
    filterQuery( id ).delete
  }


  def update( id: Int, phone: String, name: String, gender: Char, facebook: String, twitter: String,
              linkedIn: String,
              wechat:   String ) : Future[Int] = {

    val q = for (
      u <- users
      if u.id === id
    ) yield ( u.phone, u.name, u.gender, u.facebook, u.twitter, u.linkedIn, u.wechat )

    db.run {
      q.update( phone, name, Gender.fromChar(gender), facebook, twitter, linkedIn, wechat )
    }

  }


} //:~
