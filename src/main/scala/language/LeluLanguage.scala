package language

import symbol.{ComposedSymbol, ControlSymbol, DataSymbol}

object LeluLanguage extends Language {

  val value = new DataSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
  val stringValue = new DataSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
  val symbol = new DataSymbol("\\p{L}+")
  val assignment = new ComposedSymbol(List(symbol, new ControlSymbol("="), value))
  val condition = new ComposedSymbol(List(stringValue, new ControlSymbol(" "), symbol, new ControlSymbol(" "), stringValue), List(stringValue))

  val mloc = new ComposedSymbol()

  val print = new ComposedSymbol(List(new ControlSymbol("tulosta"), new ControlSymbol(" "), stringValue))
  val wwhile = new ComposedSymbol(List(new ControlSymbol("toista"), new ControlSymbol("("), condition, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}")))
  val iff = new ComposedSymbol(List(new ControlSymbol("jos"), new ControlSymbol("("), condition, new ControlSymbol(")"), new ControlSymbol("{"), mloc, new ControlSymbol("}")))

  val loc = new ComposedSymbol(List(assignment), List(print), List(wwhile), List(iff))
  mloc.addGroups(List(loc, mloc), List(loc))

  val block = new ComposedSymbol(List(mloc))

  override def startSymbol = block

}