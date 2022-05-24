package marsrover

import marsrover.InputParser.lines

import scala.io.Source

object InputParser {
  private val lines = readInput("mars-rover/input.txt")
  val gridDimensions: Seq[Int] = lines(0).split(" ").map(_.toInt)
  val rover1Position: RoverPosition = parsePosition(lines(1).split(" "))
  val rover1Instructions: String = lines(2)
  val rover2Position: RoverPosition = parsePosition(lines(3).split(" "))
  val rover2Instructions: String = lines(4)


  private def parsePosition(input: Seq[String]): RoverPosition = {
    val direction = input(2) match {
      case "N" => 0
      case "E" => 90
      case "S" => 180
      case "W" => 270
    }
    RoverPosition(x = input(0).toInt, y = input(1).toInt, Direction = direction)
  }

  private def readInput(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines()
    lines.map(_.toString).toSeq
  }
}
