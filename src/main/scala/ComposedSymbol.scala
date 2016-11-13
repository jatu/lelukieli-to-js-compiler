
/**
  * Created by nikoe on 13.11.2016.
  */
class ComposedSymbol (val symbolGroups: Seq[Symbol]*) extends Symbol{
  def this(symbols: Symbol*) {
    this(List[Seq[Symbol]](symbols))
  }
}
