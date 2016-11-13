import main.Parser
import org.scalatest.{BeforeAndAfterEach, FunSuite}


class ParserTest extends FunSuite with BeforeAndAfterEach {

  val parser = new Parser()

  override def beforeEach() {
  }

  override def afterEach() {

  }

  test("testParse") {
    assert(parser.parse() == "testi")
  }

}
