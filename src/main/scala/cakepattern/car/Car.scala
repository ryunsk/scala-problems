package cakepattern.car

class Car(engine: Engine) extends Vehicle {
  override val numberOfWheels: Int = ???
  override val weight: Int = ???
  override val maxPassengers: Int = ???

  override def move(): Unit = ???
}

// Example without dependency injection - Engine instantiated inside the class
class CarNoDI extends Vehicle {
  val engine: Engine = new FastEngine() // Car and Engine classes tightly coupled

  override val numberOfWheels: Int = ???
  override val weight: Int = ???
  override val maxPassengers: Int = ???

  override def move(): Unit = ???
}