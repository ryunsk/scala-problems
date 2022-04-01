package cheatinghangman

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class WordsManagerTest extends AnyFlatSpec with Matchers {

  val wordsManager = new WordsManager("test_lowercase_words.txt")

  behavior of "word finder (checks for answer)"
  it should "check the win condition - positive" in {
    val guesses = Set("c", "x", "a", "z", "t")
    wordsManager.foundWord(guesses, "cat") shouldEqual true
  }

  it should "check the win condition - positive - 2 " in {
    val guesses = Set("h", "e", "l", "o")
    wordsManager.foundWord(guesses, "hello") shouldEqual true
  }

  it should "check the win condition - negative " in {
    val guesses = Set("d", "o", "e")
    wordsManager.foundWord(guesses, "dog") shouldEqual false
  }

}
