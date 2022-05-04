package markshaney

import scala.io.Source

object MarkShaney {

  def readInput(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines()
    lines.toList
  }

  def generateNextWord(input: Seq[String]): String = {
    ???
  }

  def main(args: Array[String]): Unit = {
    val text = readInput("mark-shaney/input-string.txt")
    println(text)

  }

}
