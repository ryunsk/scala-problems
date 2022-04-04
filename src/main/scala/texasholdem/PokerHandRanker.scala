package texasholdem

object PokerHandRanker {


  def rankHands(players: Seq[Player], table: Table): Unit = {
    1
  }

  def rankHand(player: Player, table: Table): PokerHand = {
    val allCards = player.hand ++ table.cards

    HighCard
  }


}


// Improvements
// Show current best combination between turns/rounds

// Edge cases:
// Same hand => Find higher hand with high card (Texas hold'em suit does not matter?)
// Ace High/Low for Straight / Straight flush
// Royal straight flush will naturally win when comparing high cards
// Count the best 5 cards
// You can have a tie

// Highest to lowest: A, K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3 and 2