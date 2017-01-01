package transform

import symbol.{AstBranch, AstControlLeaf, AstDataLeaf, AstNode, ComposedSymbol, ControlSymbol, DataSymbol, Symbol}

import scala.collection.mutable.HashMap

class Transformer {

  private val transforms = new HashMap[Symbol, AstNode=>Seq[AstNode]]()

  def addTransition(symbol: Symbol, transformation: AstNode=>Seq[AstNode]) = {
    transforms.put(symbol, transformation)
  }

  def transform(ast: AstNode) : Seq[AstNode] = {
    val transform = transforms.get(ast.symbol)
    transform match {
      case (t: Some[AstNode=>Seq[AstNode]]) => t.get (ast)
      case None => throw new Exception("Unknown symbol")
    }
  }

  def ignoreTransition() : (AstNode=>Seq[AstNode]) = {
    (sourceNode: AstNode) => {
      List()
    }
  }

  def equalTransition(targetSymbol: Symbol) : (AstNode=>Seq[AstNode]) = {
    (sourceNode: AstNode) => {
      sourceNode match {
        case (node: AstDataLeaf) => equalTransition(node, targetSymbol.asInstanceOf[DataSymbol])
        case (node: AstControlLeaf) => equalTransition(node, targetSymbol.asInstanceOf[ControlSymbol])
        case (node: AstBranch) => equalTransition(node, targetSymbol.asInstanceOf[ComposedSymbol])
      }
    }
  }

  private def equalTransition(ast: AstBranch, targetSymbol: ComposedSymbol) : Seq[AstNode] = {
    val nonControlNodes = ast.subNodes.filter(n => !n.isInstanceOf[AstControlLeaf]).iterator

    val transformedSubNodes = targetSymbol.symbols.flatMap({
      case (controlSymbol: ControlSymbol) => List(AstControlLeaf(controlSymbol))
      case _ => {
        if (nonControlNodes.hasNext) {
          transform(nonControlNodes.next())
        }
        else {
          throw new Exception("Ast tree has more data nodes than target symbol has data sub symbols.")
        }
      }
    })

    if (nonControlNodes.hasNext) {
      throw new Exception("Ast tree has non handled data nodes.")
    }

    List(AstBranch(targetSymbol, transformedSubNodes))
  }

  private def equalTransition(ast: AstControlLeaf, targetSymbol: ControlSymbol) : Seq[AstNode] = {
    List(AstControlLeaf(targetSymbol))
  }

  private def equalTransition(ast: AstDataLeaf, targetSymbol: DataSymbol) : Seq[AstNode] = {
    List(AstDataLeaf(targetSymbol, ast.content))
  }

}