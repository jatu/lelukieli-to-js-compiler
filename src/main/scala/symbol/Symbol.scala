package symbol

trait Symbol {
  def parseAstNode(input: CharSequence) : Option[(AstNode, CharSequence)]
}