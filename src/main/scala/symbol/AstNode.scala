package symbol

trait AstNode {
  def symbol: Symbol
}
case class AstLeaf(symbol: TerminalSymbol, content: CharSequence) extends AstNode
case class AstBranch(symbol: ComposedSymbol, subNodes: Seq[AstNode]) extends AstNode