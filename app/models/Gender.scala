//: tables: Gender.scala
package models

import slick.driver.MySQLDriver.api._

/**
  * Database column type related to data type 'Gender' in 'Users' like table
  *
  * @version 1.0 $ 2016-02-28 18:27:27 $
  */
sealed abstract class Gender( val abbr: Char )

object Gender {

  case object Female extends Gender( 'F' )
  case object Male   extends Gender( 'M' )
  case object Others extends Gender( 'O' )

  implicit val columnType: BaseColumnType[Gender] =
    MappedColumnType.base[Gender, Char ]( Gender.toChar, Gender.fromChar )

  def toChar( gender: Gender ) : Char = gender match {

    case Female => 'F'
    case Male   => 'M'
    case Others => 'O'

  }

  def fromChar( value: Char ) : Gender = value match {

    case 'F' => Female
    case 'M' => Male
    case 'O' => Others
    case _   => sys.error( "System gender only support 'F', 'M' and 'O'" )

  }

} //:~
