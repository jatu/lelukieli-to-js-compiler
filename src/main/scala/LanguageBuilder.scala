object LanguageBuilder {

  def buildLeluLanguage() : Language = {
    val value = new TerminalSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val stringValue = new TerminalSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
    val symbol = new TerminalSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List(symbol, new TerminalSymbol("="), value))
    val condition = new ComposedSymbol(List(stringValue, new TerminalSymbol(" "), symbol, new TerminalSymbol(" "), stringValue))

    val print = new ComposedSymbol(List(new TerminalSymbol("tulosta"), new TerminalSymbol(" "), stringValue))
    val wwhile = new ComposedSymbol(List(new TerminalSymbol("toista"), new TerminalSymbol(" "), condition, new TerminalSymbol(":")))

    //val loc = new ComposedSymbol(List(Self(), Self()), List(assignment))
    val loc = new ComposedSymbol(List(assignment), List(print), List(wwhile))

    val block = new ComposedSymbol(List(loc))

    new Language(block)
  }

}