package cheatinghangman

class Display {
  def display(hangManState: HangmanState): Unit = {
    println(renderCurrentGuess(hangManState.guessedLetters, hangManState.answer))
  }

  private def renderCurrentGuess(guessedLetters: Set[String], answer: String): String = {
    answer.map(x => if (guessedLetters.contains(x.toString)) {
      x
    } else {
      '_'
    }).mkString(" ")
  }

  def renderGuessedLetters(guessedLetters: Set[String]): String = {
    "Guessed letters: " + "[ " + guessedLetters.mkString(", ") + " ]"
  }
}