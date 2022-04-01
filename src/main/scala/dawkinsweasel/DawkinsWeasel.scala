package dawkinsweasel

import scala.util.Random

/*
Dawkins' Weasel
https://programmingpraxis.com/2014/11/14/dawkins-weasel/
Start with a random string of 28 characters.
Make 100 copies of the string (reproduce).
For each character in each of the 100 copies, with a probability of 5%, replace (mutate) the character with a new random character.
Compare each new string with the target string “METHINKS IT IS LIKE A WEASEL”, and give each a score (the number of letters in the string that are correct and in the correct position).
If any of the new strings has a perfect score (28), halt. Otherwise, take the highest scoring string, and go to step 2.
 */
object DawkinsWeasel {
  final val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "
  final val expectedResult = "METHINKS IT IS LIKE A WEASEL"
  final val chanceToMutate = 5 // TODO: Change this to 5 later!
  // Chance to replace a character in the random string. Set to 5%.
  final val lengthOfListToCopy = 100 // TODO: Change this to 100 later!
  final val lengthOfString = expectedResult.length

  /** Create random string with given length of characters
   *
   */
  def createRandomIndividual(lengthOfString: Int, possibleLetters: String): String = {
    List.fill(lengthOfString)(possibleLetters(Random.nextInt(possibleLetters.length))).mkString
  }

  /** Make 100 copies of random 28 character string
   */
  def createPopulation(myString: String, populationSize: Int): List[String] = {
    List.fill(populationSize)(myString)
  }

//    def createPopulation2[A, B](myString: A, populationSize: B): List[A] = {
//      List.fill(populationSize)(myString)
//    }

  /** Every character has a 5% chance to be replaced with a new character
   *
   * replacing character can be a space too (" ")
   */
  def mutateIndividual(myString: String, possibleLetters: String, chanceToMutate: Int): String = {
    // Note: Random.between is a Scala 2.13 feature
    myString.map(x => if (Random.between(1, 100) <= chanceToMutate) {
      possibleLetters(Random.nextInt(possibleLetters.length))
    } else {
      x
    }).mkString
  }

  /** Mutate every string in List where every character in a string can be replaced with 5% chance
   */
  def mutatePopulation(listOfStrings: List[String], possibleLetters: String, chanceToMutate: Int): List[String] = {
    // Mutate every string in List where every character in a string can be replaced with 5% chance
    listOfStrings.map(x => mutateIndividual(x, possibleLetters, chanceToMutate))
  }

  /** Return similarity score between two strings.
   *
   * E.g. "abc" and "aac" would return 2.
   *
   * E.g. "aaa" and "bbb" would return 0.
   */
  def fitnessScore(a: String, b: String): Integer = {
    a.zip(b).count(x => x._1 == x._2)
  }

  /** Return the highest scoring string compared with the variable `expectedResult`
   */
  def findFittestIndividual(listOfStrings: List[String]): String =
  //    a.sortBy(x => TwoStringsSimilarityScore(x, expectedResult)).reverse.head
    listOfStrings.maxBy(x => fitnessScore(x, expectedResult))

  def run(): Unit = {
    // Can make this into one line, but will put in separate lines for readability...
    val initialString = createRandomIndividual(lengthOfString, letters)
    val copy100IntoList = createPopulation(initialString, lengthOfListToCopy)
    val mutatedList = mutatePopulation(copy100IntoList, letters, chanceToMutate)
    val bestMatchingString = findFittestIndividual(mutatedList)

    @annotation.tailrec
    def loop(a: String, n: Int): String = {
      if (a.equals(expectedResult)) {
        println("Found! " + a + " Iteration number: " + n)
        a
      } else {
        loop(findFittestIndividual(mutatePopulation(createPopulation(a, lengthOfListToCopy), letters, chanceToMutate)), n + 1)
      }
    }

    loop(bestMatchingString, 0)
    // TODO: How to see intermediate steps?
  }

  def main(args: Array[String]): Unit = {
    run()
  }
}
