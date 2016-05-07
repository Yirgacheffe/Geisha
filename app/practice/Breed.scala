//: practice: Breed.scala
package practice


object Breed extends Enumeration {

  type Breed = Value

  val doberman = Value( "Doberman Prinscher" )
  val yorkie   = Value( "Yorkshire Terrier" )
  val scottie  = Value( "Scottish Terrier"  )
  val dane     = Value( "Great Dane" )
  val portie   = Value( "Portuguese Water Dog" )

} //:~


object BreedTest {

  def main( arg: Array[String] ) : Unit = {

    println( "ID\tBreed" )
    for ( breed <- Breed.values ) println( s"${breed.id}\t$breed" )

    println( "\n" )
    println( "Just Terriers:" )

    Breed.values filter( _.toString.endsWith( "Terrier" ) ) foreach println

    import Breed._

    def isTerrier( b: Breed ) : Boolean = b.toString.endsWith( "Terrier" )

    println( "\n" )
    println( "Terriers Again???" )
    Breed.values filter isTerrier foreach println

  }

} //:~
