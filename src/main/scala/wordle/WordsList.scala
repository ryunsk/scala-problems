package wordle

import scala.io.Source

class WordsList {
  val words: Seq[String] = readWordsFromFile("lowercase_five_letter_words.txt")
  val randomWord: String = generateRandomWord(words)

  private def generateRandomWord(words: Seq[String]): String = {
    val r = scala.util.Random
    val randomIndex = r.nextInt(words.length)

    words(randomIndex)
  }

  private def readWordsFromFile(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines()
    lines.map(_.toString).toSeq
  }
}
