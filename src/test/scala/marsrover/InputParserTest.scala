package marsrover

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class InputParserTest extends AnyFlatSpec with Matchers {
  it should "get grid dimensions" in {
    InputParser.gridDimensions shouldEqual Seq("5", "5")
  }
  it should "rover 1 location" in {
    InputParser.rover1Position shouldEqual Seq("1", "2", "N")
  }
  it should "rover 1 instructions" in {
    InputParser.rover1Instructions shouldEqual "LMLMLMLMM"
  }
  it should "rover 2 location" in {
    InputParser.rover2Position shouldEqual Seq("3", "3", "E")
  }
  it should "rover 2 instructions" in {
    InputParser.rover2Instructions shouldEqual "MMRMMRMRRM"
  }
}
