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