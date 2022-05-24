package marsrover

class MarsRover(grid: Seq[Seq[Int]], position: RoverPosition, instructions: String) {
  val initialState = placeRover(grid, position)


  private def placeRover(grid: Seq[Seq[Int]], position: RoverPosition): Seq[Seq[Int]] = {
    val rowsIndex = grid.length - 1
    val tempSubgrid = grid(rowsIndex - position.y).updated(position.x, position.Direction)
    val updatedGrid = grid.updated(rowsIndex - position.y, tempSubgrid)
    updatedGrid
  }

}