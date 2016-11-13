

/**
  * Created by nikoe on 13.11.2016.
  */
object LanguageBuilder {

  val value = new ConcreteSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
  val symbol = new ConcreteSymbol("\\p{L}+")
  val assignment = new ComposedSymbol(symbol, new ConcreteSymbol("="), value)

  val block = new ComposedSymbol()

  //val ifSym = new ComposedSymbol
  val lelu = new Language(block)
}
