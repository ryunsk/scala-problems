package marsrover

class Grid(x: Int, y: Int) {
  val grid = createGrid(x, y) // (rows, cols) <=> (y, x) not (x, y)
  //  val grid: Array[Array[String]] = Array.ofDim[String](y, x) // Alternate method

  def createGrid(x: Int, y: Int): Seq[Seq[Int]] = {
    if (x < 0 || y < 0) {
      throw new Exception("Coordinates must be positive")
    }
    Seq.fill(y)(Seq.fill(x)(0))
  }
}
