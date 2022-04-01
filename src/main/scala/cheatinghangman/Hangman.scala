package cheatinghangman



sealed trait GameState

case object InProgress extends GameState

case object Won extends GameState

case object Lost extends GameState

case class HangmanState(guessedLetters: Set[String],
                        possibleWords: Seq[String],
                        currentGuess: String,
                        answer: String,
                        gameState: GameState) {
  // number of previous tries can be found by doing length of the guessed letters
  // possibleWords will be changed depending on the guess
}


// Logic for state of the game
class Hangman(wordLength: Int, wordsManager: WordsManager, player: Player, display: Display) {
  // Display logic

  // Recursively play until you win
  def play(currentState: HangmanState): HangmanState = {
    println("=============================")
    println("Current letter guess: " + currentState.currentGuess)
    val newGuessedLetters: Set[String] = currentState.guessedLetters ++ Set(currentState.currentGuess)
    println("Guessed letters: " + "[ " + newGuessedLetters.mkString(", ") + " ]")
    println("Current answer: " + currentState.answer)
    println("Possible words (Dictionary): " + currentState.possibleWords)
    // Base case: Check if Game is won
    if (wordsManager.foundWord(newGuessedLetters, currentState.answer)) {
      println("You guessed the word!")
      println(currentState)
      currentState.copy(gameState = Won)
    } else {
      // If guess letter is in the answer, try to switch answer
      if (currentState.answer.contains(currentState.currentGuess)) {
        println("=============================")
        println(currentState.currentGuess + " is in the answer! (" + currentState.answer + ")")
        println("Changing answer...")
        val filteredWords = wordsManager.filterWordsWithoutLetter(currentState.currentGuess, currentState.possibleWords)
        println("Filtered words: " + filteredWords)

        val newAnswer = wordsManager.pickRandomAnswer(filteredWords)
        println("New Answer: " + newAnswer)

        val next = HangmanState(
          guessedLetters = newGuessedLetters,
          possibleWords = filteredWords,
          currentGuess = player.guessLetter(),
          answer = newAnswer,
          gameState = InProgress
        )
        play(next)
      } else {
        println("=============================")
        println("The letter is not in the word.")
        // If not, filter dictionary and keep playing
        val filteredWords = wordsManager.filterWordsWithoutLetter(currentState.currentGuess, currentState.possibleWords)
        println("Filtered words: " + filteredWords)

        val next = HangmanState(
          guessedLetters = newGuessedLetters,
          possibleWords = filteredWords,
          currentGuess = player.guessLetter(),
          answer = currentState.answer,
          gameState = InProgress
        )
        play(next)
      }

      currentState.copy(gameState = Lost)
    }

  }
}
