package cakepattern

/** A Foo that depends on a Bar */
trait FooComponent {
  this: BarComponent =>

  class Foo {

    def doSomeFooBarOperation() = {
      println("FooComponent did something")
      bar.doSomething()
    }
  }
}

trait BarComponent {

  val bar: Bar
  println("bar initialised " + bar)

  class Bar {
    def doSomething() = {
      println("BarComponent did something")
    }
  }
}

/** A registry that contains the various class instances to be used */
trait FooBarModule extends
  FooComponent with
  BarComponent {

  lazy val bar = new Bar()

  lazy val foo = new Foo()
}

object Main extends FooBarModule {

  def main(args: Array[String]): Unit = {
    foo.doSomeFooBarOperation()
  }
}

// TODO: Try testing with overriding e.g. Bar - Mock
// TODO: Try and move class outside the trait