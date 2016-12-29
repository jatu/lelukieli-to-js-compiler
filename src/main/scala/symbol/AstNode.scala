package symbol

trait AstNode {
  def symbol: Symbol
}
case class AstControlLeaf(symbol: ControlSymbol) extends AstNode
case class AstDataLeaf(symbol: DataSymbol, content: CharSequence) extends AstNode
case class AstBranch(symbol: ComposedSymbol, subNodes: Seq[AstNode]) extends AstNode