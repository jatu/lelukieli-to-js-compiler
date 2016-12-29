package language

import symbol.{ComposedSymbol, TerminalSymbol}

object LeluLanguage extends Language {

  val value = new TerminalSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
  val stringValue = new TerminalSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
  val symbol = new TerminalSymbol("\\p{L}+")
  val assignment = new ComposedSymbol(List(symbol, new TerminalSymbol("="), value))
  val condition = new ComposedSymbol(List(stringValue, new TerminalSymbol(" "), symbol, new TerminalSymbol(" "), stringValue), List(stringValue))

  val mloc = new ComposedSymbol()

  val print = new ComposedSymbol(List(new TerminalSymbol("tulosta"), new TerminalSymbol(" "), stringValue))
  val wwhile = new ComposedSymbol(List(new TerminalSymbol("toista"), new TerminalSymbol("("), condition, new TerminalSymbol(")"), new TerminalSymbol("{"), mloc, new TerminalSymbol("}")))
  val iff = new ComposedSymbol(List(new TerminalSymbol("jos"), new TerminalSymbol("("), condition, new TerminalSymbol(")"), new TerminalSymbol("{"), mloc, new TerminalSymbol("}")))

  val loc = new ComposedSymbol(List(assignment), List(print), List(wwhile), List(iff))
  mloc.addGroups(List(loc, mloc), List(loc))

  val block = new ComposedSymbol(List(mloc))

  override def startSymbol = block

}