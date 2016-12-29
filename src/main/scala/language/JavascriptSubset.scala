package language

import symbol.{ComposedSymbol, ControlSymbol, DataSymbol}

object JavascriptSubset extends Language {

  val value = new DataSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
  val stringValue = new DataSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
  val symbol = new DataSymbol("\\p{L}+")
  val assignment = new ComposedSymbol(List(symbol, new ControlSymbol("="), value))
  val condition = new ComposedSymbol(List(stringValue, new ControlSymbol(" "), symbol, new ControlSymbol(" "), stringValue), List(stringValue))

  val mloc = new ComposedSymbol()

  val print = new ComposedSymbol(List(new ControlSymbol("console.log"), new ControlSymbol("("), stringValue, new ControlSymbol(")"), new ControlSymbol(";")))
  val wwhile = new ComposedSymbol(List(new ControlSymbol("while"), new ControlSymbol("("), condition, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}")))
  val iff = new ComposedSymbol(List(new ControlSymbol("if"), new ControlSymbol("("), condition, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}")))

  val loc = new ComposedSymbol()
  mloc.addGroups(List(loc, mloc), List(loc))
  val block = new ComposedSymbol(List(mloc))

  override def startSymbol = block

}
