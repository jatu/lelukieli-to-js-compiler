import scala.collection.mutable.ArrayBuffer

class ComposedSymbol (symbolOptionGroups: Seq[SymbolOrSelf]*) extends Symbol {

  val symbolGroups = symbolOptionGroups.map(
    (group: Seq[SymbolOrSelf]) => group.map(
      (symbol: SymbolOrSelf) => symbol match {
        case SymbolReference(x: Symbol) => x
        case Self() => this
      }
    )
  )

  override def parseAstNode(input: CharSequence): Option[(AstNode, CharSequence)] = {
    symbolGroups.map( g => {
        var code = input
        val result = new ArrayBuffer[AstNode]
        for (s <- g) {
          val symbolParseResult = s.parseAstNode(code)
          if (symbolParseResult.isEmpty) {
            None
          }
          else {
            result.append(symbolParseResult.get._1)
            code = symbolParseResult.get._2
          }
        }

        Some( (AstBranch(result), code) )
      }
    ).find(r=>r.isDefined).getOrElse(None)
  }
}