package marsrover

import scala.io.Source

object InputParser {
  private val lines = readInput("mars-rover/input.txt")
  val gridDimensions: Seq[Int] = lines(0).split(" ").map(_.toInt)
  val rover1Position: Seq[String] = lines(1).split(" ")
  val rover1Instructions: String = lines(2)
  val rover2Position: Seq[String] = lines(3).split(" ")
  val rover2Instructions: String = lines(4)

  private def readInput(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines()
    lines.map(_.toString).toSeq
  }
}
