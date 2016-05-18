package practice

object MatchRegex {

  def main( args: Array[String] ) = {

    val bookExtractorRE = """Book: title=([^,]+),\s+author=(.+)""".r
    val magazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r

    val catalog = Seq (
      "Book: title=Programming Scala, author=Dean Wampler",
      "Magazine: title=The New Yorker, issue=January 2014",
      "Unknown: text=Who put this here??"
    )

    for (item <- catalog) {

      item match {
        case bookExtractorRE(title, author) => println( s"""Book $title written by $author""")
        case magazineExtractorRE(title, issue) => println( s"""Magazine $title, issue $issue""")
        case entry => println(s"Unrecognized entry: $entry")
      }

    }
  }
}