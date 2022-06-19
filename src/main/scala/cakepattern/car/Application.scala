package cakepattern.car

object Application {
  def main(args: Array[String]): Unit = {
    val fastEngine = new FastEngine()
    val car = new Car(fastEngine)
  }
}
