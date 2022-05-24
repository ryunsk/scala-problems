package marsrover


object Application {
  def main(args: Array[String]): Unit = {
    val gridDimensions = InputParser.gridDimensions
    val rover1Position = InputParser.rover1Position
    val rover1Instructions = InputParser.rover1Instructions
    val grid = new Grid(gridDimensions(0), gridDimensions(1))

    val marsRover = new MarsRover(grid.grid, rover1Position, rover1Instructions)
  }
}
