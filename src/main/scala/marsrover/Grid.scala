package marsrover

class Grid(x: Int, y: Int) {
  val grid: Seq[Seq[Int]] = Seq.fill(y)(Seq.fill(x)(0)) // (rows, cols) <=> (y, x) not (x, y)
  //  val grid: Array[Array[String]] = Array.ofDim[String](y, x) // Alternate method
}


object Test {

}