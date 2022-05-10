package markshaney

import scala.io.Source
import scala.util.Random

object MarkShaney {

  def main(args: Array[String]): Unit = {
    val text = readInput("mark-shaney/input-string.txt")
    //    val text = readInput("mark-shaney/input-string-test.txt")
    val cleaned = cleanInput(text)
    val triplets = wordsToTriplets(cleaned)
    println(triplets)
  }

  def readInput(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines().filter(_.nonEmpty)
    lines.toList.flatMap(_.split(" ").toList)
  }

  def cleanInput(input: Seq[String]): Seq[String] = {
    input.map(word => {
      if (word.startsWith("“")) {
        word.substring(1, word.length)
      }
      else if (word.endsWith("”")) {
        word.substring(0, word.length - 1)
      }
      else if (word.startsWith("_") && word.endsWith("_")) {
        word.substring(1, word.length - 1)
      }
      else {
        word
      }
    })
  }

  def wordsToTriplets(words: Seq[String]): Seq[Seq[String]] = {
    words.sliding(3, 3).toSeq
  }

  def generateNextWord(input: Seq[Seq[String]], word: String): String = {
    val tripletsWithWord = input.filter(_.contains(word))
    tripletsWithWord(Random.nextInt(tripletsWithWord.length))(2)
  }

}
