package cakepattern.car

object Application {
  def main(args: Array[String]): Unit = {
    val fastEngine = new FastEngineClass()

    val car1 = new Car(fastEngine)
    println("Car 1")
    println(car1.horsePower) // 10
    println(car1.weight) // 10

    val car2 = new CarNoDI
    println("Car 2")
    println(car2.horsePower) // 10
    println(car2.weight) // 11

    val car3 = new CarUsingTrait with Engine // Mixin trait
    println("Car 3")
    println(car3.horsePower) // 15 - Default 15 if using Engine
    println(car3.weight) // 12

    val car4 = new CarUsingTrait with FastEngine
    println("Car 4")
    println(car4.horsePower) // 100 - Override in FastEngine
    println(car4.weight) // 12
  }
}
