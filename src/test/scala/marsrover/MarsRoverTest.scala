package marsrover

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MarsRoverTest extends AnyFlatSpec with Matchers {

  ignore should "place rover in the grid" in {
    //    val grid = Seq(
    //      Seq(0, 0, 0),
    //      Seq(0, 0, 0),
    //      Seq(0, 0, 0),
    //      Seq(0, 0, 0))
    //    val position = RoverPosition(0, 1, 1)
    //    val instructions = ""
    //
    //    val marsRover = new MarsRover(grid, position, instructions)
    //    marsRover.initialState shouldEqual
    //      Seq(
    //        Seq(0, 0, 0),
    //        Seq(0, 0, 0),
    //        Seq(1, 0, 0),
    //        Seq(0, 0, 0))
  }
  it should "rotate rover right when instructed - right" in {
    val position = RoverPosition(0, 1, 0)
    val instructions = "R"
    val marsRover = new MarsRover(position, instructions)
    marsRover.finalState shouldEqual RoverPosition(0, 1, 90)
  }

  it should "rotate rover left when instructed - Edge case cycle L" in {
    val position = RoverPosition(0, 1, 0)
    val instructions = "L"
    val marsRover = new MarsRover(position, instructions)
    marsRover.finalState shouldEqual RoverPosition(0, 1, 270)
  }

  it should "rotate rover right when instructed - Edge case cycle R" in {
    val position = RoverPosition(0, 1, 270)
    val instructions = "R"
    val marsRover = new MarsRover(position, instructions)
    marsRover.finalState shouldEqual RoverPosition(0, 1, 0)
  }
}