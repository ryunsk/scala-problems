package cheatinghangman

import scala.io.Source

// Logic for words / letters
class WordsManager(filePath: String) {
  // List of words
  // List of remaining words based on guess?

  def getWords(wordLength: Int): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines()
    lines.toSeq.filter(x => x.length == wordLength)
  }

  def filterWordsWithLetter(letter: String, words: Seq[String]): Seq[String] = {
    words.filter(_.contains(letter))
  }

  def filterWordsWithoutLetter(letter: String, words: Seq[String]): Seq[String] = {
    // TODO: Better way of filtering letters. E.g. a - e - i - o - u - then try to filter y -> will filter every word.
    if (words.count(!_.contains(letter)) < 1) {
      words
    } else {
      words.filter(!_.contains(letter))
    }
  }

  def pickRandomAnswer(words: Seq[String]): String = {
    // TODO: Uncomment
    val r = scala.util.Random
    val randomIndex = r.nextInt(words.length)
    words(randomIndex)
    //    words.head
  }

  def foundWord(guesses: Set[String], answer: String): Boolean = {
    answer.forall(x => guesses.contains(x.toString))
  }

}