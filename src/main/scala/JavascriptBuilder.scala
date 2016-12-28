class JavascriptBuilder {

  def buildJavascriptLanguage(): Language = {
    val value = new TerminalSymbol("true|false|null|[0-9]+\\.[0-9]*|[0-9]+")
    val stringValue = new TerminalSymbol("([\"'])(?:(?=(\\\\?))\\2.)*?\\1")
    val symbol = new TerminalSymbol("\\p{L}+")
    val assignment = new ComposedSymbol(List(symbol, new TerminalSymbol("="), value))
    val condition = new ComposedSymbol(List(stringValue, new TerminalSymbol(" "), symbol, new TerminalSymbol(" "), stringValue), List(stringValue))

    val mloc = new ComposedSymbol()

    val print = new ComposedSymbol(List(new TerminalSymbol("console.log"), new TerminalSymbol("("), stringValue, new TerminalSymbol(")"), new TerminalSymbol(";")))
    val wwhile = new ComposedSymbol(List(new TerminalSymbol("while"), new TerminalSymbol("("), condition, new TerminalSymbol(")"), new TerminalSymbol("{"), mloc, new TerminalSymbol("}")))
    val iff = new ComposedSymbol(List(new TerminalSymbol("if"), new TerminalSymbol("("), condition, new TerminalSymbol(")"), new TerminalSymbol("{"), mloc, new TerminalSymbol("}")))

    val loc = new ComposedSymbol()
    mloc.addGroups(List(loc, mloc), List(loc))
    val block = new ComposedSymbol(List(mloc))

    new Language(block)

  }

}
