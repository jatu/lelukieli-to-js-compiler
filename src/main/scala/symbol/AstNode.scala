package symbol

abstract class AstNode(val symbol: Symbol)
case class AstLeaf(sym: Symbol, content: CharSequence) extends AstNode(sym)
case class AstBranch(sym: Symbol, subNodes: Seq[AstNode]) extends AstNode(sym)