package cheatinghangman

import scala.io.StdIn

trait Player {
  def guessLetter(): String
}

class HumanPlayer() extends Player {
  override def guessLetter(): String = {
    // TODO: Checks when input is not a single letter
    print("Guess a letter: ")
    StdIn.readLine().trim.toLowerCase
  }
}