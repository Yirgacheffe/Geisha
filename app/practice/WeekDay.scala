package practice


object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}

object WeekDayTest {

  import WeekDay._

  def isWorkingDay( d: WeekDay ) = ! ( d == Sat || d == Sun )

} //:~
