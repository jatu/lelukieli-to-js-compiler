

/**
  * Created by nikoe on 13.11.2016.
  */
object LanguageBuilder {
  def getLeluLanguage() = {
    val epsilonGroup = List()

    val value = new ConcreteSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val symbol = new ConcreteSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List(symbol, new ConcreteSymbol("="), value))

    lazy val loc : ComposedSymbol = new ComposedSymbol(epsilonGroup, List(loc, loc), List(assignment))

    val block = new ComposedSymbol(List(loc))

    new Language(block)
  }
}
