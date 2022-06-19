package cakepattern.car

// Dependency injection through the constructor
class Car(engine: Engine) extends Vehicle {
  override val numberOfWheels: Int = 4
  override val weight: Int = 10
  override val maxPassengers: Int = 2
  val horsePower: Int = engine.horsePower // No override here

  override def move(): Unit = println("Move")
}

// Example without dependency injection - Engine instantiated inside the class
class CarNoDI extends Vehicle {
  val engine: Engine = new FastEngine() // Car and Engine classes tightly coupled

  override val numberOfWheels: Int = 4
  override val weight: Int = 11
  override val maxPassengers: Int = 3
  val horsePower = engine.horsePower // No override here

  override def move(): Unit = println("Move")
}

trait CarTrait extends Vehicle {
  engine: Engine =>
  override val horsePower: Int = engine.horsePower // override
}

class CarUsingTrait extends CarTrait with Engine {
  override val numberOfWheels: Int = 5
  override val weight: Int = 12
  override val maxPassengers: Int = 4

  override def move(): Unit = println("Move")
}