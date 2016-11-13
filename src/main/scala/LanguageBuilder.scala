object LanguageBuilder {

  def buildLeluLanguage() = {
    val epsilonGroup = List()

    val value = new ConcreteSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val symbol = new ConcreteSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List[SymbolOrSelf](symbol, new ConcreteSymbol("="), value))

    val loc = new ComposedSymbol(epsilonGroup, List(Self(), Self()), List(assignment))

    val block = new ComposedSymbol(List(loc))

    new Language(block)
  }

}