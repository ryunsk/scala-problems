package marsrover


object Application {
  def main(args: Array[String]): Unit = {
    //    val gridDimensions = InputParser.gridDimensions
    //    val grid = new Grid(gridDimensions(0), gridDimensions(1))
    val rover1Position = InputParser.rover1Position
    val rover1Instructions = InputParser.rover1Instructions

    val rover2Position = InputParser.rover2Position
    val rover2Instructions = InputParser.rover2Instructions

    val marsRover1 = new MarsRover(rover1Position, rover1Instructions)
    val marsRover2 = new MarsRover(rover2Position, rover2Instructions)

    val finalState1 = marsRover1.finalState
    val finalState2 = marsRover2.finalState

    println(rover1Position)
    println(rover2Position)
    println("---")
    println(finalState1)
    println(finalState2)
  }
}
