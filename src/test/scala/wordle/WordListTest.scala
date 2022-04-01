package wordle

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class WordListTest extends AnyFlatSpec with Matchers {
  val words: Seq[String] = Seq("above", "clone", "focus", "lions", "storm")
}
