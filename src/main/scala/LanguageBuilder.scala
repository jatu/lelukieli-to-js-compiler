object LanguageBuilder {

  def buildLeluLanguage() : Language = {
    val value = new TerminalSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val stringValue = new TerminalSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
    val symbol = new TerminalSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List(symbol, new TerminalSymbol("="), value))

    val print = new ComposedSymbol(List(new TerminalSymbol("tulosta"), new TerminalSymbol(" "), stringValue))

    //val loc = new ComposedSymbol(List(Self(), Self()), List(assignment))
    val loc = new ComposedSymbol(List(assignment), List(print))

    val block = new ComposedSymbol(List(loc))

    new Language(block)
  }

}