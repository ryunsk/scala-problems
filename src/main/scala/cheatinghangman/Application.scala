package cheatinghangman


object Application {
  def main(args: Array[String]): Unit = {
    // Configs for the game
    val wordLength = 5
    val wordsListFile = "lowercase_words.txt"

    val wordsManager = new WordsManager(wordsListFile)
    val player = new HumanPlayer()
    val display = new Display()
    // - Choose a word length

    val startState = HangmanState(
      guessedLetters = Set(),
      possibleWords = wordsManager.getWords(wordLength),
      currentGuess = player.guessLetter(),
      answer = wordsManager.pickRandomAnswer(wordsManager.getWords(wordLength)),
      gameState = InProgress)

    val hangman = new Hangman(wordLength, wordsManager, player, display)

    hangman.play(startState)


  }
}

//      - If a letter is in the word, try to change the word
//      - Bonus: Reveal the letter if it has better odds of "cheating"
//    - The word change must be consistent with the previous guesses.
//      - e.g. if 'a' is not in the word then the new word must not contain 'a'