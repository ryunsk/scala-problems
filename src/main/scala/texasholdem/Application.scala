package texasholdem

object Application {
  def main(args: Array[String]): Unit = {
    // Initialise
    val player1 = Player(Seq())
    val player2 = Player(Seq())
    val table = Table(Seq())
    val deckBuilder = new DeckBuilder
    val shuffledDeck = Dealer.shuffleDeck(deckBuilder.deck)

    val start = GameState(shuffledDeck, Seq(player1, player2), table)
    val dealCardsToPlayer = Dealer.dealCardsToAllPlayers(start)
    val flop = Dealer.dealCardsToTable(dealCardsToPlayer, numberOfCards = 3)
    val turn = Dealer.dealCardsToTable(flop, numberOfCards = 1)
    val river = Dealer.dealCardsToTable(turn, numberOfCards = 1)

    val turns = Seq(dealCardsToPlayer, flop, turn, river)

    println("Game State: (No formatting)")
    println(start)
    turns.foreach(Display.displayGameState)
  }
}
