import scala.collection.immutable.List

class ComposedSymbol (symbolOptionGroups: Seq[SymbolOrSelf]*) extends Symbol {

  symbolOptionGroups.foreach(
    group =>
      if (group.nonEmpty) {
        print (group.head.toString)
        group.head match {
          case Self() => throw new java.lang.UnsupportedOperationException("ComposedSymbol won't support self reference being first symbol.")
          case _ =>
        }
      }
  )

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
        var code = input //TODO: fold function should carry result array AND code, currently code is just mutated!
        val result = g.foldLeft[Option[List[AstNode]]](Some(List[AstNode]())) { (results, symbol) =>
          if (results.isEmpty) {
            None
          }
          else {
            val symbolParseResult = symbol.parseAstNode(code)
            if (symbolParseResult.isEmpty) {
              None
            }
            else {
              code = symbolParseResult.get._2
              Some((symbolParseResult.get._1) :: results.get)
            }
          }
        }

        if (result.isEmpty) {
          None
        }
        else {
          Some( (AstBranch(result.get), code) )
        }

      }
    ).find(r=>r.isDefined).getOrElse(None)
  }
}