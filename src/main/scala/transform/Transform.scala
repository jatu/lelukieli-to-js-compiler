package transform

import language.Language
import symbol.AstNode

trait Transform {
  def sourceLanguage : Language
  def transform(astNode: AstNode) : Seq[AstNode]
}
