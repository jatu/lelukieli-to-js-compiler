package transform

import language.{Language, LeluLanguage}
import symbol.{AstNode, ComposedSymbol, DataSymbol, Symbol, SymbolPool}

object WhitespaceRemoveTransform extends Transform {

  object UglifiedLanguage extends Language {
    val str = LeluLanguage.stringValue
    val whiteSpace = new DataSymbol("\\s")
    val character = new DataSymbol("[^\\s]")

    val loc = new SymbolPool(str, whiteSpace, character)
    val mloc = new SymbolPool()
    val locAndMloc = new ComposedSymbol(loc, mloc)
    mloc.addGroups(locAndMloc, loc)

    override def startSymbol: Symbol = mloc
  }

  object MinifiedLanguage extends Language {
    val str = UglifiedLanguage.str
    val character = UglifiedLanguage.character

    val loc = new SymbolPool(str, character)
    val mloc = new SymbolPool()
    val locAndMloc = new ComposedSymbol(loc, mloc)
    mloc.addGroups(locAndMloc, loc)

    override def startSymbol: Symbol = mloc
  }

  def sourceLanguage = UglifiedLanguage
  def targetLanguage = MinifiedLanguage

  private val compiler = new Transformer()

  compiler.addTransition(UglifiedLanguage.character, compiler.equalTransition(MinifiedLanguage.character))
  compiler.addTransition(UglifiedLanguage.str, compiler.equalTransition(MinifiedLanguage.str))
  compiler.addTransition(UglifiedLanguage.whiteSpace, compiler.ignoreTransition())

  compiler.addTransition(UglifiedLanguage.locAndMloc, compiler.equalTransition(MinifiedLanguage.locAndMloc))
  compiler.addTransition(UglifiedLanguage.mloc, compiler.equalTransition(MinifiedLanguage.mloc))
  compiler.addTransition(UglifiedLanguage.loc, compiler.equalTransition(MinifiedLanguage.loc))

  def transform(astNode: AstNode) : Seq[AstNode] = {
    compiler.transform(astNode)
  }
}