package practice


case class ComplicatedSalesTaxData( baseRate: Float, isTaxHoliday: Boolean, storedId: Int )


object SimplestStateSalesTax {
  implicit val rate: Float = 0.05F
}


object ComplicatedSalesTax {

  private def extraTaxRateForStore( id: Int ) : Float = {
    0.0F
  }

  implicit def rate( implicit cstd: ComplicatedSalesTaxData ) : Float = {

    if ( cstd.isTaxHoliday ) 0.0F
    else cstd.baseRate + extraTaxRateForStore( cstd.storedId )

  }

}


object ImplicitsArgs {


  def calcTax( amount: Float )( implicit rate: Float ) : Float = amount * rate


  def main( args: Array[String] ) = {

    import SimplestStateSalesTax.rate

    val amount = 100F
    println( s"Tax on $amount = ${calcTax(amount}" )

    import ComplicatedSalesTax.rate
    implicit val myStore = ComplicatedSalesTaxData( 0.06F, false, 1010 )

    val amount 100F
    println( s"Tax on $amount = ${calcTax(amount}" )

  }

}
