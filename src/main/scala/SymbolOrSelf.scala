/**
  * Created by nikoe on 14.11.2016.
  */
object SymbolOrSelf {
  implicit def Symbol2SymbolOrSelf(x: Symbol): SymbolOrSelf = {
    new SymbolReference(x)
  }
}

abstract class SymbolOrSelf {
  def get : Symbol
  def isSelf : Boolean
}

final case class SymbolReference(x: Symbol) extends SymbolOrSelf {
  def isSelf = false
  def get = x
}
case class Self() extends SymbolOrSelf {
  def isSelf = true
  def get = throw new NoSuchElementException("Self.get")
}

