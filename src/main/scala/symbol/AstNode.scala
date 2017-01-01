package symbol

trait AstNode {
  def symbol: Symbol
  def toCodeString: CharSequence
}

object AstNode {
  def toCodeString(nodes: Seq[AstNode]) = {
    nodes.foldLeft(new StringBuilder()) { (strBuilder, node) =>
      strBuilder.append(node.toCodeString)
    }
  }
}

case class AstControlLeaf(symbol: ControlSymbol) extends AstNode {
  override def toCodeString: String = symbol.text
}

case class AstDataLeaf(symbol: DataSymbol, content: CharSequence) extends AstNode{
  override def toCodeString: CharSequence = content
}

case class AstBranch(symbol: ComposedSymbol, subNodes: Seq[AstNode]) extends AstNode{
  override def toCodeString: CharSequence = AstNode.toCodeString(subNodes)
}
