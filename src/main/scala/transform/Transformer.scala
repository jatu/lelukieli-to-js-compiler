package transform

import language.{JavascriptSubset, LeluLanguage}
import symbol.{AstBranch, AstControlLeaf, AstDataLeaf, AstNode, ComposedSymbol, ControlSymbol, DataSymbol, Symbol}

import scala.collection.mutable.HashMap

class Transformer {
  private val transforms = new HashMap[Symbol, AstNode=>AstNode]()

  def addTransition(symbol: Symbol, transformation: AstNode=>AstNode) = {
    transforms.put(symbol, transformation)
  }

  def transform(ast: AstNode) : AstNode = {
    val transform = transforms.get(ast.symbol)
    transform match {
      case (t: Some[AstNode=>AstNode]) => t.get (ast)
      case None => throw new Exception("Unknown symbol")
    }
  }

  def equalTransition(targetSymbol: Symbol) : (AstNode=>AstNode) = {
    (sourceNode: AstNode) => {
      sourceNode match {
        case (node: AstDataLeaf) => equalTransition(node, targetSymbol.asInstanceOf[DataSymbol])
        case (node: AstControlLeaf) => equalTransition(node, targetSymbol.asInstanceOf[ControlSymbol])
        case (node: AstBranch) => equalTransition(node, targetSymbol.asInstanceOf[ComposedSymbol])
      }
    }
  }

  private def equalTransition(ast: AstBranch, targetSymbol: ComposedSymbol) : AstNode = {
    AstBranch(targetSymbol, ast.subNodes.map(transform))
  }

  private def equalTransition(ast: AstControlLeaf, targetSymbol: ControlSymbol) : AstNode = {
    AstControlLeaf(targetSymbol)
  }

  private def equalTransition(ast: AstDataLeaf, targetSymbol: DataSymbol) : AstNode = {
    AstDataLeaf(targetSymbol, ast.content)
  }

  addTransition(LeluLanguage.block, equalTransition(JavascriptSubset.block))
}