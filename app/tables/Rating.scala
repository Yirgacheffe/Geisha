//: tables: Rating.scala
package tables

import slick.driver.MySQLDriver.api._

/**
 * Database entity related to data type 'Rating'
 *
 * @version 1.0 $ 2016-02-26 23:51:10 $
 */
sealed abstract class Rating( val stars: Int )

object Rating {

  final case object Awesome extends Rating( 5 )
  final case object Good    extends Rating( 4 )
  final case object NotBad  extends Rating( 3 )
  final case object Meh     extends Rating( 2 )
  final case object Aaargh  extends Rating( 1 )

  implicit val columnType: BaseColumnType[Rating] =
    MappedColumnType.base[Rating, Int]( Rating.toInt, Rating.fromInt )

  private def toInt( rating: Rating ) : Int = rating match {

    case Awesome => 5
    case Good    => 4
    case NotBad  => 3
    case Meh     => 2
    case Aaargh  => 1

  }

  private def fromInt( stars: Int ) : Rating = stars match {

    case 5 => Awesome
    case 4 => Good
    case 3 => NotBad
    case 2 => Meh
    case 1 => Aaargh
    case _ => sys.error( "System rating only apply from 1 to 5" )

  }

} //:~
