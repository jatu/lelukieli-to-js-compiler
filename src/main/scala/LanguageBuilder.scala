

/**
  * Created by nikoe on 13.11.2016.
  */
object LanguageBuilder {
  val epsilonGroup = List()

  val value = new ConcreteSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
  val symbol = new ConcreteSymbol("\\p{L}+")
  val assignment = new ComposedSymbol(symbol, new ConcreteSymbol("="), value)

  val loc = new ComposedSymbol(epsilonGroup, List(loc, loc), List(assignment))

  val block = new ComposedSymbol(loc)

  val lelu = new Language(block)
}
