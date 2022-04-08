package texasholdem

object Display {

  def displayGameState(gameState: GameState): Unit = {
    println("====================")
    println("Players: ")
    gameState.players.foreach(player => {
      print("Hand: ")
      displayCards(player.hand)
      println
    })

    println
    print("Table cards: ")
    displayCards(gameState.table.cards)
    println
  }

  def displayCards(cards: Seq[Card]): Unit = {
    cards.foreach(displayCard)
  }

  def displayCard(card: Card): Unit = {
    val suit = card.suit match {
      case Clubs => "♣"
      case Diamonds => "♦"
      case Hearts => "♥"
      case Spades => "♠"
    }
    val numberToString = Map(
      1 -> "A", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5",
      6 -> "6", 7 -> "7", 8 -> "8", 9 -> "9", 10 -> "10",
      11 -> "J", 12 -> "Q", 13 -> "K", 14 -> "A")

    print("[" + numberToString(card.number) + " " + suit + "]")
  }
}
