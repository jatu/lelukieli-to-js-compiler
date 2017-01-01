package language

import symbol.{ComposedSymbol, ControlSymbol, DataSymbol, SymbolPool}

object JavascriptSubset extends Language {

  val value = new DataSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
  val stringValue = new DataSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
  val symbol = new DataSymbol("\\p{L}+")
  val assignment = new ComposedSymbol(symbol, new ControlSymbol("="), value)
  val condition = new ComposedSymbol(stringValue, symbol, stringValue)

  val mloc = new SymbolPool()

  val print = new ComposedSymbol(new ControlSymbol("console.log"), new ControlSymbol("("), stringValue, new ControlSymbol(")"), new ControlSymbol(";"))
  val wwhile = new ComposedSymbol(new ControlSymbol("while"), new ControlSymbol("("), condition, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}"))
  val iff = new ComposedSymbol(new ControlSymbol("if"), new ControlSymbol("("), condition, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}"))

  val loc = new SymbolPool(assignment, print, wwhile, iff)
  val locAndMloc = new ComposedSymbol(loc, mloc)
  mloc.addGroups(locAndMloc, loc)
  val block = new ComposedSymbol(mloc)

  override def startSymbol = block

}
