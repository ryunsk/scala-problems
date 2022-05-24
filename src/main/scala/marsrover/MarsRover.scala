package marsrover

class MarsRover(position: RoverPosition, instructions: String) {
  // grid: Seq[Seq[Int]]
  //  val initialState: Seq[Seq[Int]] = placeRover(grid, position)
  val finalState = moveRover(position, instructions)

  private def moveRover(position: RoverPosition, instructions: String): RoverPosition = {
    updatePositionSingleInput(position, instructions(0))
  }

  private def updatePositionSingleInput(position: RoverPosition, instructions: Char): RoverPosition = {
    instructions match {
      case 'L' => position.copy(direction = (position.direction + 270) % 360)
      case 'R' => position.copy(direction = (position.direction + 90) % 360)
      case 'M' => position.direction match {
        case 0 => position.copy(y = position.y + 1)
        case 90 => position.copy(x = position.x + 1)
        case 180 => position.copy(y = position.y - 1)
        case 270 => position.copy(x = position.x - 1)
      }
    }

  }

  private def placeRover(grid: Seq[Seq[Int]], position: RoverPosition): Seq[Seq[Int]] = {
    updateGrid(grid, position.x, position.y, position.direction)
  }

  private def updateGrid(grid: Seq[Seq[Int]], x: Int, y: Int, updatedValue: Int): Seq[Seq[Int]] = {
    val rowsIndex = grid.length - 1
    val tempSubgrid = grid(rowsIndex - y).updated(x, updatedValue)
    val updatedGrid = grid.updated(rowsIndex - y, tempSubgrid)
    updatedGrid
  }
}