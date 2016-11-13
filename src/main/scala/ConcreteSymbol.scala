import sun.misc.Regexp

class ConcreteSymbol(regexp: Regexp) extends Symbol {

  def this(regexpPattern: String) {
    this(new Regexp(regexpPattern))
  }

}