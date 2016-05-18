package practice


case class InvalidColumnName( name: String ) extends RuntimeException( s"Invalid column name &name" )

trait Row {
  def getText  ( colName: String ) : String
  def getInt   ( colName: String ) : Int
  def getDouble( colName: String ) : Double
}

case class JRow( representation: Map[String, Any] ) extends Row {

  private def get( colName: String ) : Any = representation.getOrElse( colName, throw InvalidColumnName(colName) )

  def getText  ( colName: String ) : String = get( colName ).asInstanceOf[String]
  def getInt   ( colName: String ) : Int    = get( colName ).asInstanceOf[Int]
  def getDouble( colName: String ) : Double = get( colName ).asInstanceOf[Double]

}

object JRow {
  def apply( pairs: (String, Any)* ) = new JRow( Map( pairs: _*) )
}

object implicits {

  import practice.JRow

  implicit class SRow( jrow: JRow ) {
    def get[T]( colName: String )( implicit toT: (JRow, String) => T ) : T = toT( jrow, colName )
  }

  implicit val jrowToText:   ( JRow, String ) => String = ( jrow: JRow, colName: String ) => jrow.getText(   colName )
  implicit val jrowToInt:    ( JRow, String ) => Int    = ( jrow: JRow, colName: String ) => jrow.getInt(    colName )
  implicit val jrowToDouble: ( JRow, String ) => Double = ( jrow: JRow, colName: String ) => jrow.getDouble( colName )

}


object DB {

  import implicits._

  def main( args: Array[String] ) = {

    val row = JRow( "one" -> 1, "two" -> 2.2, "three" -> "Three" )

    val oneValue1: Int = row.get( "one" )
    val oneValue2: Int = row.get[Int]( "one" )

    println( s"one1 -> $oneValue1" )
    println( s"one2 -> $oneValue2" )

  }

}

