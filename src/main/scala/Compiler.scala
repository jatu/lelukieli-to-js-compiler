import language.Language
import symbol.AstNode
import transform.Transform

class Compiler {

  def compile(transform: Transform, inputCode: String): Option[String] = {
    val sourceAstTree = parse(transform.sourceLanguage, inputCode)
    val targetAstTree = sourceAstTree.map(astTree => transform.transform(astTree))
    val outputCode = targetAstTree.map(astTree => AstNode.toCodeString(astTree).toString())

    outputCode
  }

  def parse(language: Language, code: String): Option[AstNode] = {
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