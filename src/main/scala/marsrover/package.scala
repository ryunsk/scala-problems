package object marsrover {
  case class RoverPosition(x: Int, y: Int, direction: Int) {
    override def toString: String = {
      val directionText = direction match {
        case 0 => "N"
        case 90 => "E"
        case 180 => "S"
        case 270 => "W"
      }
      s"x: $x, y: $y, Direction: $directionText"
    }
  }
}
