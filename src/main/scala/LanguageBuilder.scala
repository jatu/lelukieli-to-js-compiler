object LanguageBuilder {

  def buildLeluLanguage : Language = {
    val value = new ConcreteSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val symbol = new ConcreteSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List(symbol, new ConcreteSymbol("="), value))

    val loc = new ComposedSymbol(List(Self(), Self()), List(assignment))

    val block = new ComposedSymbol(List(loc))

    new Language(block)
  }

}