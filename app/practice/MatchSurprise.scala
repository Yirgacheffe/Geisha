package practice


object MatchSurprise {


  def checkY( y: Int ) = {
    for {
      x <- Seq( 99, 100, 101 )
    } {
      val str = x match {
        // case y => "found y!"       // Wrong pattern
        case `y` => "found y!"        // See `y` ??? back ticks not ''
        case i: Int => "int: " + i
      }

      println( str )
    }
  }

  def checkAgain() = {
    for {
      x <- Seq( 1, 2, 2,7, "one", "two", 'four )
    } {
      val str = x match {

        case _: Int | _: Double => "a number: " + x
        case "one"              => "string one"
        case _: String          => "other string: " + x
        case _                  => "unexpected value: " + x
      }

      println( str )
    }

  }



  def main( args: Array[String] ) = {
    checkY( 100 )
  }

} //:~
