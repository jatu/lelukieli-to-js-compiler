package symbol

import scala.collection.immutable.List

class ComposedSymbol (symbolGroups: Symbol*) extends Symbol {

  var symbols = symbolGroups

  def addGroups(symbolGroups: Symbol*) = {
    symbols = symbols ++ symbolGroups
  }

  override def parseAstNode(input: CharSequence): Option[(AstNode, CharSequence)] = {
    var code = input //TODO: fold function should carry result array AND code, currently code is just mutated!
    val result = symbols.foldLeft[Option[List[AstNode]]](Some(List[AstNode]())) { (results, symbol) =>
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
      Some( (AstBranch(this, result.get.reverse), code) )
    }
  }
}