package markshaney

import scala.io.Source
import scala.util.Random

object MarkShaney {

  def readInput(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines().filter(_.nonEmpty)
    lines.toList.flatMap(_.split(" ").toList)
  }

  def wordsToTriplets(words: Seq[String]): Seq[Seq[String]] = {
    words.sliding(3, 3).toSeq
  }

  def generateNextWord(input: Seq[Seq[String]]): String = {
    val randomIndex = Random.nextInt(input.length)
    val words = " " + input(randomIndex)(0) + " " + input(randomIndex)(1) + " " + input(randomIndex)(2)
    words
  }

  def main(args: Array[String]): Unit = {
    val text = readInput("mark-shaney/input-string.txt")
    //    val text = readInput("mark-shaney/input-string-test.txt")
    val triplets = wordsToTriplets(text)

    var i = 0
    while (i < 10) {
      val r = generateNextWord(triplets)
      print(r)
      i += 1
    }
  }

}
