package language

import symbol.{ComposedSymbol, ControlSymbol, DataSymbol, SymbolPool}

object LeluLanguage extends Language {

  val booleanValue = new DataSymbol("suattaa|suattaapiollaolematta")
  val numValue = new DataSymbol("[0-9]+\\\\.[0-9]*|[0-9]+")
  val nullValue = new DataSymbol("eilieneemittää")
  val stringValue = new DataSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
  val value = new SymbolPool(booleanValue, numValue, nullValue, stringValue)

  val expression = new SymbolPool()
  val mloc = new SymbolPool()

  val symbol = new DataSymbol("\\p{L}+")
  val functionCall = new ComposedSymbol(expression, new ControlSymbol("("), expression, new ControlSymbol(")"))
  val functionReturnStatement = new ComposedSymbol(new ControlSymbol("palautappa:"), expression)
  expression.addGroups(value, function, functionCall, symbol)
  val function = new ComposedSymbol(new ControlSymbol("funkkari"), new ControlSymbol("("), symbol, new ControlSymbol(")"), new ControlSymbol("{"), mloc, functionReturnStatement, new ControlSymbol("}"))
  val assignment = new ComposedSymbol(symbol, new ControlSymbol("="), expression)

  val print = new ComposedSymbol(new ControlSymbol("voekkoprintata"), expression)
  val wwhile = new ComposedSymbol(new ControlSymbol("kerranjostoisennii"), new ControlSymbol("("), expression, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}"))
  val iff = new ComposedSymbol(new ControlSymbol("oliskolie"), new ControlSymbol("("), expression, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}"))

  val loc = new SymbolPool(assignment, print, wwhile, iff)
  val locAndMloc = new ComposedSymbol(loc, mloc)
  mloc.addGroups(locAndMloc, loc)
  val block = new ComposedSymbol(mloc)

  override def startSymbol = block

}