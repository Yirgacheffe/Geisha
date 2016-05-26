//: practice: RemoveBlanks.scala
package practice


object RemoveBlanks {


  def apply( path: String, compressWhiteSpace: Boolean = false ) : Seq[String] = {

    for {
      line <- scala.io.Source.fromFile( path ).getLines().toSeq
      if line.matches( """^\s*$""" ) == false
      line2 = if ( compressWhiteSpace ) line replaceAll ( "\\s+", " " ) else line
    } yield line2

  }

  
  def main( args: Array[String] ) : Unit = {

    for {
      path2 <- args
      ( compress, path ) = if ( path2 startsWith "_" ) ( true, path2.substring(1) ) else ( false, path2 )
      line <- apply( path, compress )
    } println( line )

  }


} //:~
