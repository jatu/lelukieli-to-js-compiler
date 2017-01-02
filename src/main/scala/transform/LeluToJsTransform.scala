package transform

import language.{JavascriptSubset, LeluLanguage}
import symbol.AstNode

object LeluToJsTransform extends Transform {

  def sourceLanguage = LeluLanguage
  def targetLanguage = LeluLanguage

  private val compiler = new Transformer()

  compiler.addTransition(LeluLanguage.block, compiler.equalTransition(JavascriptSubset.block))
  compiler.addTransition(LeluLanguage.locAndMloc, compiler.equalTransition(JavascriptSubset.locAndMloc))
  compiler.addTransition(LeluLanguage.assignment, compiler.equalTransition(JavascriptSubset.assignment))
  compiler.addTransition(LeluLanguage.expression, compiler.equalTransition(JavascriptSubset.condition))
  compiler.addTransition(LeluLanguage.iff, compiler.equalTransition(JavascriptSubset.iff))
  compiler.addTransition(LeluLanguage.wwhile, compiler.equalTransition(JavascriptSubset.wwhile))
  compiler.addTransition(LeluLanguage.loc, compiler.equalTransition(JavascriptSubset.loc))
  compiler.addTransition(LeluLanguage.mloc, compiler.equalTransition(JavascriptSubset.mloc))
  compiler.addTransition(LeluLanguage.print, compiler.equalTransition(JavascriptSubset.print))
  compiler.addTransition(LeluLanguage.stringValue, compiler.equalTransition(JavascriptSubset.stringValue))
  compiler.addTransition(LeluLanguage.value, compiler.equalTransition(JavascriptSubset.value))
  compiler.addTransition(LeluLanguage.symbol, compiler.equalTransition(JavascriptSubset.symbol))

  def transform(astNode: AstNode) : Seq[AstNode] = {
    compiler.transform(astNode)
  }

}
