package symbol

class SymbolPool(symbolOptions: Symbol*) extends Symbol{
  var symbols = symbolOptions

  def addGroups(symbolOptions: Symbol*) = {
    symbols = symbols ++ symbolOptions
  }

  override def parseAstNode(input: CharSequence): Option[(AstNode, CharSequence)] = {
    val results = symbols.map( g => {
      g.parseAstNode(input)
    })
    val validResults = results.find(r=>r.isDefined)
    val finalSingleResult = validResults.getOrElse(None)
    finalSingleResult
  }

}
