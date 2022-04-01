package wordle

trait Player {
  var tries: Int
  var guesses: Seq[String]
  var currentGuess: String
}

class User extends Player {
  override var tries: Int = 1
  override var guesses: Seq[String] = Seq("hello")
  override var currentGuess: String = guesses.head
}