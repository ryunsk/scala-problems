package wordle

import scala.io.StdIn

class Game(player: Player, wordsList: WordsList) {


  def play(): Unit = {
    while (player.tries < 7) {
      println("Current try number: " + player.tries)
      val guessedWord = StdIn.readLine("Guess the word: ")
      if (isWordValid(guessedWord, wordsList.words)) {
        if (compareGuessWithAnswer(guessedWord, wordsList.randomWord)) {
          player.tries = 7 // TODO: Better way of handling this
          println("Correct!")
        } else {
          player.tries += 1
        }
        // Continue with the game
      } else {
        println("Word must be valid. Please try again")
      }
    }
    println(player.guesses)
  }


  // Check word is valid
  private def isWordValid(word: String, wordsList: Seq[String]): Boolean = {
    val lowerCaseWord = word.toLowerCase()
    if (lowerCaseWord.length == 5 && wordsList.contains(lowerCaseWord)) {
      true
    } else {
      false
    }
  }

  // Compare words
  def compareGuessWithAnswer(guess: String, answer: String): Boolean = {
    true
  }

  def isGuessCorrect(guess: String, answer: String): Boolean = {
    guess.equals(answer)
  }
}
