package wordle

object Runner {
  def main(args: Array[String]): Unit = {
    val player = new User
    val words = new WordsList
    val game = new Game(player, words)
    println(words.words)
    //    game.play()
  }
}
