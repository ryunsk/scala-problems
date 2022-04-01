package dawkinsweasel

trait Evolvable[T] {
  def createIndividual(): T

  def mutateIndividual(individual: T, chanceToMutate: Int): T
}

class EvolutionSimulation[T](evolvable: Evolvable[T], populationSize: Int, fitnessFunction: T => Int, chanceToMutate: Int, isComplete: T => Boolean) {

  case class State(population: Seq[T])

  private def createInitialPopulation(): Seq[T] = {
    List.fill(populationSize)(evolvable.createIndividual())
  }

  private def findFittestIndividual(population: Seq[T]): T = {
    population.maxBy(individual => fitnessFunction(individual))
  }

  private def produceNextGeneration(fittestIndividual: T): Seq[T] = {
    List.fill(populationSize)(fittestIndividual)
      .map(individual => evolvable.mutateIndividual(individual, chanceToMutate))
  }

  def nextState(state: State): State = {
    val newPopulation = produceNextGeneration(findFittestIndividual(state.population))
    // copy = create new instance of case class with same case class values, can override certain values
    state.copy(population = newPopulation)
  }

  def run(): T = {
    //    Iterator.iterate(0)(x => x + 1).take(10)
    def isFinalState(state: State): Boolean = {
      isComplete(findFittestIndividual(state.population))
    }

    val initialState = State(createInitialPopulation())
    val finalState = Iterator.iterate(initialState)(nextState).dropWhile(state => !isFinalState(state)).next()
    findFittestIndividual(finalState.population)
  }
}

//class EvolvableString extends Evolvable[String] {
//  private final val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "
//
//  override def createIndividual(): String = List.fill(lengthOfString)(letters(Random.nextInt(letters.length))).mkString
//}

object Foo {
  def main(args: Array[String]): Unit = {
    //    val simulation = new EvolutionSimulation(new EvolvableString, 10)
    //    println(simulation.createInitialPopulation())
    // Print next state
  }
}


