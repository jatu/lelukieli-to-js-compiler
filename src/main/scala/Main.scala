import java.io.{File, PrintWriter}

import transform.LeluToJsTransform

object Main {

  val usage =
    """
      Usage:
        app --help
        app -h
        app --file code.lk app.js
        app -f code.lk app.js
    """

  val compiler = new Compiler()

  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      printNotEnough()
      return
    }

    makeChoice(args)
  }

  private def makeChoice(args: Array[String]) {
    if(args.length < 2) printNotEnough()

    args(0) match {
      case "-h" | "--help" => println(usage)
      case "-f" | "--file" =>
        if (args.length > 2) {
          compile(readFile(args(1)), args(2))
        } else {
          compile(readFile(args(1)), "output.js")
        }
      case _ => println(usage)
    }
  }

  private def printNotEnough(): Unit = {
    println("Not enough parameters! Use --help")
  }

  private def compile(param: String, file: String) = {
    val resultCode = compiler.compile(LeluToJsTransform, param)
    writeFile(resultCode, file)
  }

  private def readFile(path: String): String = {
    scala.io.Source.fromFile(path).mkString
  }

  private def writeFile(resultCode: Option[String], file: String) = {
    if(resultCode.nonEmpty) {
      val pw = new PrintWriter(new File(file))
      val split = resultCode.get.split("\n")
      split.foreach(pw.write)
      pw.close()
    }
  }

}