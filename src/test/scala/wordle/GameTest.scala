package wordle

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class GameTest extends AnyFlatSpec with Matchers {
  val player = new User
  val words = new WordsList
  val game = new Game(player, words)

  it should "is word valid" in {
    val words = Seq("above", "clone", "focus", "lions", "storm")
    //    game.isWordValid("focus", words) shouldEqual true
    //    game.isWordValid("CLONE", words) shouldEqual true
    //    game.isWordValid("hello", words) shouldEqual false
  }
}
