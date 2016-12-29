import language.LeluLanguage

object Main {

  val usage =
    """
      Usage:
        app --help
        app -h
        app --file code.lk
        app -f code.lk
    """

  val compiler = new LeluCompiler()

  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      println("Not enough parameters! Use --help")
      return
    }

    makeChoice(args)
  }

  def makeChoice(args: Array[String]) {
    args(0) match {
      case "-h" | "--help" => println(usage)
      case "-f" | "--file"  => compile(readFile(args(1)))
      case _ => println(usage)
    }
  }

  def compile(param: String) = {
    val rootnode = compiler.compile(LeluLanguage, param)
  }

  def readFile(path: String): String = {
    scala.io.Source.fromFile(path).mkString
  }

}