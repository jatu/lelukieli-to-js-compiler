package symbol

class ControlSymbol(val text: String) extends Symbol {

  private def startsWithText(input: CharSequence): Boolean = {
    if (input.length < text.length) {
      return false
    }

    for (i <- 0 to text.length - 1) {
      if (text.charAt(i) != input.charAt(i)) {
        return false
      }
    }
    true
  }

  override def parseAstNode(input: CharSequence): Option[(AstNode, CharSequence)] = {
    if (startsWithText(input)) {
      Some((AstControlLeaf(this), input.subSequence(text.length, input.length())))
    }
    else {
      None
    }
  }
}
