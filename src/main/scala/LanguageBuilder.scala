

/**
  * Created by nikoe on 13.11.2016.
  */
object LanguageBuilder {
  def getLeluLanguage() = {
    val epsilonGroup = List()

    val value = new ConcreteSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val symbol = new ConcreteSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List[SymbolOrSelf](symbol, new ConcreteSymbol("="), value))

    val loc : ComposedSymbol = new ComposedSymbol(epsilonGroup, List(new Self(), new Self()), List(assignment))

    val block = new ComposedSymbol(List(loc))

    new Language(block)
  }
}
