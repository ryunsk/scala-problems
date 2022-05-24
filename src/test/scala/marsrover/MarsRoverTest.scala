package marsrover

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MarsRoverTest extends AnyFlatSpec with Matchers {

  it should "place rover in the grid" in {
    val grid = Seq(
      Seq(0, 0, 0),
      Seq(0, 0, 0),
      Seq(0, 0, 0),
      Seq(0, 0, 0))
    val position = RoverPosition(0, 1, 90)
    val instructions = ""

    val marsRover = new MarsRover(grid, position, instructions)
    marsRover.initialState shouldEqual
      Seq(
        Seq(0, 0, 0),
        Seq(0, 0, 0),
        Seq(90, 0, 0),
        Seq(0, 0, 0))
  }
}