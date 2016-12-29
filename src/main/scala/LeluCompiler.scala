import language.Language
import symbol.AstNode

class LeluCompiler {

  def compile(language: Language, code: String): Option[AstNode] = {
    val parseResult = language.startSymbol.parseAstNode(code.toCharArray)

    parseResult match {
      case Some((root: AstNode, rest: CharSequence)) => {
        if (rest.length() > 0) {
          None
        }
        else {
          Some(root)
        }
      }
      case None => None
    }
  }

}