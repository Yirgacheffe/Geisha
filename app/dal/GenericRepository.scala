//: dal: GenericRepository.scala
package dal

import javax.inject.{ Inject, Singleton }

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile


/**
 *
 *
 */
@Singleton
class GenericRepository[T] @Inject()( dbConfigProvider: DatabaseConfigProvider )( implicit ec: ExecutionContext ) {


  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  // val tables = TableQuery[T]







} //:~

