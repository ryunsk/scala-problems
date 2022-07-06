package cakepattern.car

trait Vehicle {
  val numberOfWheels: Int
  val weight: Int
  val maxPassengers: Int

  def move(): Unit
}
