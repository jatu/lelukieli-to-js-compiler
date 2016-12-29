package symbol

import scala.util.matching.Regex

class TerminalSymbol(val regexp: Regex) extends Symbol {

  def this(regexpPattern: String) {
    this(new Regex(regexpPattern))
  }

  override def parseAstNode(input: CharSequence): Option[(AstNode, CharSequence)] = {
    val parseResult = regexp.findPrefixMatchOf(input)

    parseResult match {
      case Some(m) => Some((AstLeaf(this, m.matched), m.after))
      case None => None
    }
  }
}