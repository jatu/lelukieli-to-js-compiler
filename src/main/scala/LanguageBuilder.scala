object LanguageBuilder {

  def buildLeluLanguage() : Language = {
    val value = new TerminalSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val symbol = new TerminalSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List(symbol, new TerminalSymbol("="), value))

    //val loc = new ComposedSymbol(List(Self(), Self()), List(assignment))
    val loc = new ComposedSymbol(List(assignment))

    val block = new ComposedSymbol(List(loc))

    new Language(block)
  }

}