package cakepattern.car

object Application {
  def main(args: Array[String]): Unit = {
    val fastEngine = new FastEngine()

    val car1 = new Car(fastEngine)
    println("Car 1")
    println(car1.horsePower)
    println(car1.weight)

    val car2 = new CarNoDI
    println("Car 2")
    println(car2.horsePower)
    println(car2.weight)

    val car3 = new CarUsingTrait
    println("Car 3")
    println(car3.horsePower)
    println(car3.weight)
  }
}
