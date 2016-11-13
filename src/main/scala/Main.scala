object Main {

  val usage =
    """
      Usage:
        app --help
        app code.lk
    """

  val compiler = new LeluCompiler()

  def main(args: Array[String]): Unit = {
    LanguageBuilder.getLeluLanguage()
    if(args.length == 0) {
      println("Not enough parameters! Use --help")
      return
    }

    args(0) match {
      case "--help" => println(usage)
      case _  => compiler.compile(readFile(args(0)))
    }
  }

  def readFile(path: String): Unit = {
    try {
      scala.io.Source.fromFile(path).getLines.toList
    } catch {
      case ex: Exception => println("Cannot read file " + path)
    }
  }

}
