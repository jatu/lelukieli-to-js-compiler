import sun.misc.Regexp

/**
  * Created by nikoe on 13.11.2016.
  */
class ConcreteSymbol(regexp: Regexp) extends Symbol {

  def this(regexpPattern: String) {
    this(new Regexp(regexpPattern))
  }

}
