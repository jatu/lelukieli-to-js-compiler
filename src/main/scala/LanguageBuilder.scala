

/**
  * Created by nikoe on 13.11.2016.
  */
object LanguageBuilder {

  val value = new ConcreteSymbol()
  val symbol = new ConcreteSymbol()
  val assignment = new ComposedSymbol(symbol, new ConcreteSymbol(), value)

  //val ifSym = new ComposedSymbol

  //val lelu = new Language()
}
