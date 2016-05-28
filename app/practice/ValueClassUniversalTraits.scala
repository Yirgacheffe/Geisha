package practice


trait Digitizer extends Any {
  def digits( s: String ) : String = s.replaceAll( """\D""", "" )
}


trait Formatter extends Any {
  def format( areaCode: String, exchange: String, subNumber: String ) : String = {
    s"($areaCode) $exchange-$subNumber"
  }
}


class USPhoneNumber( val s: String ) extends AnyVal with Digitizer with Formatter {

  override def toString = {

    val digs = digits( s )

    val areaCode  = digs.substring( 0, 3  )
    val exchange  = digs.substring( 3, 6  )
    val subNumber = digs.substring( 6, 10 )

    format( areaCode, exchange, subNumber )

  }

} //:~
