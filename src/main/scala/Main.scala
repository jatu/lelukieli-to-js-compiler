object Main {

  val usage =
    """
      Usage:
        app --help
        app code.lk
    """

  val compiler = new LeluCompiler()

  val testCode =
    """
      asd=null
    """

  def main(args: Array[String]): Unit = {
    val leluLang = LanguageBuilder.buildLeluLanguage()
    compiler.compile(leluLang, testCode)
    return

    /*if(args.length == 0) {
      println("Not enough parameters! Use --help")

      compiler.compile(leluLang, testCode)

      return
    }

    args(0) match {
      case "--help" => println(usage)
      case _  => compiler.compile(leluLang, readFile(args(0)))
    }*/
  }

  /*def readFile(path: String): Unit = {
    try {
      scala.io.Source.fromFile(path).getLines.toList
    } catch {
      case ex: Exception => println("Cannot read file " + path)
    }
  }*/

}