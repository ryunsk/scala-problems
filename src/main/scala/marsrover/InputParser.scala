package marsrover

import scala.io.Source

object InputParser {
  private val lines = readInput("mars-rover/input.txt")
  val gridDimensions = lines

  private def readInput(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines()
    lines.map(_.toString).toSeq
  }
}
