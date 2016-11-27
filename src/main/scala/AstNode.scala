abstract class AstNode
case class AstLeaf(content: CharSequence) extends AstNode
case class AstBranch(subNodes: Seq[AstNode]) extends AstNode