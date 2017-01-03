package transform

import language.{JavascriptSubset, LeluLanguage}
import symbol.{AstDataLeaf, AstNode}

object LeluToJsTransform extends Transform {

  def sourceLanguage = LeluLanguage
  def targetLanguage = LeluLanguage

  private val compiler = new Transformer()

  compiler.addTransition(LeluLanguage.block, compiler.equalTransition(JavascriptSubset.block))
  compiler.addTransition(LeluLanguage.locAndMloc, compiler.equalTransition(JavascriptSubset.locAndMloc))
  compiler.addTransition(LeluLanguage.assignment, compiler.equalTransition(JavascriptSubset.assignment))
  compiler.addTransition(LeluLanguage.expression, compiler.equalTransition(JavascriptSubset.expression))
  compiler.addTransition(LeluLanguage.iff, compiler.equalTransition(JavascriptSubset.iff))
  compiler.addTransition(LeluLanguage.wwhile, compiler.equalTransition(JavascriptSubset.wwhile))
  compiler.addTransition(LeluLanguage.loc, compiler.equalTransition(JavascriptSubset.loc))
  compiler.addTransition(LeluLanguage.mloc, compiler.equalTransition(JavascriptSubset.mloc))
  compiler.addTransition(LeluLanguage.print, compiler.equalTransition(JavascriptSubset.print))
  compiler.addTransition(LeluLanguage.stringValue, compiler.equalTransition(JavascriptSubset.stringValue))
  compiler.addTransition(LeluLanguage.numValue, compiler.equalTransition(JavascriptSubset.numValue))
  compiler.addTransition(LeluLanguage.value, compiler.equalTransition(JavascriptSubset.value))
  compiler.addTransition(LeluLanguage.symbol, compiler.equalTransition(JavascriptSubset.symbol))
  compiler.addTransition(LeluLanguage.functionB, compiler.equalTransition(JavascriptSubset.functionB))
  compiler.addTransition(LeluLanguage.functionCall, compiler.equalTransition(JavascriptSubset.functionCall))
  compiler.addTransition(LeluLanguage.functionReturnStatement, compiler.equalTransition(JavascriptSubset.functionReturnStatement))
  compiler.addTransition(LeluLanguage.nonCatchedExpression, compiler.equalTransition(JavascriptSubset.nonCatchedExpression))
  compiler.addTransition(LeluLanguage.nullValue, (astNode: AstNode) => List(AstDataLeaf(JavascriptSubset.nullValue, "null")))
  compiler.addTransition(LeluLanguage.booleanValue, (astNode: AstNode) => {
    if(astNode.asInstanceOf[AstDataLeaf].content == "suattaa") {
      List(AstDataLeaf(JavascriptSubset.booleanValue, "true"))
    } else {
      List(AstDataLeaf(JavascriptSubset.booleanValue, "false"))
    }
  })

  def transform(astNode: AstNode) : Seq[AstNode] = {
    compiler.transform(astNode)
  }

}
