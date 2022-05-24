package marsrover

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GridTest extends AnyFlatSpec with Matchers {
  it should "create a grid with specified dimensions" in {
    val grid = new Grid(3, 5)

    println(grid.grid)
    grid.grid(0).length shouldEqual 3
    grid.grid.length shouldEqual 5
  }

  it should "throw an exception if grid size is negative" in {
    assertThrows[Exception] {
      new Grid(-1, 5)
    }
  }

}
