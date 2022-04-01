package dawkinsweasel

import org.scalatest.funsuite.AnyFunSuite

class DawkinsWeaselTest extends AnyFunSuite {
  test("Test template") {
    val input = Array(1, 2, 3)
    val output = Array(1, 2, 3)
    assert(input sameElements output)
  }

  test("createRandomIndividual string should have same length of given length of string") {
    val possibleLetters = "ABCDE"
    assert(DawkinsWeasel.createRandomIndividual(28, possibleLetters).length == 28)
  }

  test("create copies of individual to generate a population") {
    val expected = List("abc", "abc", "abc")
    assert(DawkinsWeasel.createPopulation("abc", 3) sameElements expected)
  }

  test("Find fittest individual") {
    // Find string most similar to "METHINKS IT IS LIKE A WEASEL"
    val inputList = List("METHINKS AA AA LIKE A ABCDEF", "METHINKS IT IS LIKE A WEASEA", "METHINKS IT IS LIKE A WEASBB")
    assert(DawkinsWeasel.findFittestIndividual(inputList).equals("METHINKS IT IS LIKE A WEASEA"))
  }
}
