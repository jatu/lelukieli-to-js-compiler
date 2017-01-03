package language

import symbol.{ComposedSymbol, ControlSymbol, DataSymbol, SymbolPool}

object JavascriptSubset extends Language {

  val booleanValue = new DataSymbol("true|false")
  val numValue = new DataSymbol("[0-9]+\\\\.[0-9]*|[0-9]+")
  val nullValue = new DataSymbol("null")
  val stringValue = new DataSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
  val value = new SymbolPool(booleanValue, numValue, nullValue, stringValue)

  val expression = new SymbolPool()
  val mloc = new SymbolPool()

  val symbol = new DataSymbol("\\p{L}+")
  val functionCall = new ComposedSymbol(expression, new ControlSymbol("("), expression, new ControlSymbol(")"))
  val functionReturnStatement = new ComposedSymbol(new ControlSymbol("return"), new ControlSymbol(" "), expression, new ControlSymbol(";"))
  val functionB = new ComposedSymbol(new ControlSymbol("function"), new ControlSymbol("("), symbol, new ControlSymbol(")"), new ControlSymbol("{"), mloc, functionReturnStatement, new ControlSymbol("}"))
  expression.addGroups(value, functionB, functionCall, symbol)
  val assignment = new ComposedSymbol(symbol, new ControlSymbol("="), expression, new ControlSymbol(";"))

  val print = new ComposedSymbol(new ControlSymbol("console.log"), new ControlSymbol("("), expression, new ControlSymbol(")"), new ControlSymbol(";"))
  val wwhile = new ComposedSymbol(new ControlSymbol("while"), new ControlSymbol("("), expression, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}"))
  val iff = new ComposedSymbol(new ControlSymbol("if"), new ControlSymbol("("), expression, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}"))

  val nonCatchedExpression = new ComposedSymbol(expression, new ControlSymbol(";"))

  val loc = new ComposedSymbol(new SymbolPool(assignment, print, wwhile, iff, nonCatchedExpression), new ControlSymbol(";"))
  val locAndMloc = new ComposedSymbol(loc, mloc)
  mloc.addGroups(locAndMloc, loc)
  val block = new ComposedSymbol(mloc)

  override def startSymbol = block


}
