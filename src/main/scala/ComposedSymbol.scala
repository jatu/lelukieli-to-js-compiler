import scala.collection.mutable

/**
  * Created by nikoe on 13.11.2016.
  */
class ComposedSymbol (symbolOptionGroups: Seq[SymbolOrSelf]*) extends Symbol{
  val symbolGroups = symbolOptionGroups.map(
    (group: Seq[SymbolOrSelf]) => group.map(
      (symbol: SymbolOrSelf) => symbol match {
        case SymbolReference(x: Symbol) => x
        case Self() => this
      }
    )
  )
}
