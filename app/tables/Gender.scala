//: tables: Gender.scala
package tables

import slick.driver.MySQLDriver.api._

/**
 * Database column type related to data type 'Gender' in 'Users' like table
 *
 * @version 1.0 $ 2016-02-28 18:27:27 $
 */
sealed abstract class Gender( val abbr: Char )

object Gender {

  final case object Female extends Gender( 'F' )
  final case object Male   extends Gender( 'M' )
  final case object Others extends Gender( 'O' )

  implicit val columnType: BaseColumnType[Gender] =
    MappedColumnType.base[Gender, Char ]( Gender.toChar, Gender.fromChar )

  private def toChar( gender: Gender ) : Char = gender match {

    case Female => 'F'
    case Male   => 'M'
    case Others => 'O'

  }

  private def fromChar( abbr: Char ) : Gender = abbr match {

    case 'F' => Female
    case 'M' => Male
    case 'O' => Others
    case _   => sys.error( "System gender only support 'F', 'M' and 'O'" )

  }

} //:~
