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

  def makeChoice(args: Array[String]) {
    args(0) match {
      case "-h" | "--help" => println(usage)
      case "-f" | "--file" => {
        if(args.length < 2) {
          printNotEnough()
        } else if (args.length > 2) {
          compile(readFile(args(1)), args(2))
        } else {
          compile(readFile(args(1)), "output.js")
        }
      }
      case _ => println(usage)
    }
  }

  def printNotEnough(): Unit = {
    println("Not enough parameters! Use --help")
  }

  def compile(param: String, file: String) = {
    val resultCode = compiler.compile(LeluToJsTransform, param)
    writeFile(resultCode, file)
  }

  def readFile(path: String): String = {
    scala.io.Source.fromFile(path).mkString
  }

  def writeFile(resultCode: Option[String], file: String) = {
    if(resultCode.nonEmpty) {
      val pw = new PrintWriter(new File(file))
      val split = resultCode.get.split("\n")
      split.foreach(pw.write)
      pw.close()
    }
  }

}