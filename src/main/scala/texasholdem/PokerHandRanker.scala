package texasholdem

object PokerHandRanker {


  def rankHands(players: Seq[Player], table: Table): Unit = {
    1
  }

  // Return the type of hand and its cards
  def rankHand(player: Player, table: Table): (PokerHand, Seq[Card]) = {
    val allCardsSorted: Seq[Card] = (player.hand ++ table.cards).sortBy(_.number)
    if (allCardsSorted.length != 7) {
      throw new RuntimeException("There should be only 7 cards total in hand and table")
    }
    val straight = hasStraight(allCardsSorted)

    println(allCardsSorted)
    println(straight)

    (HighCard, allCardsSorted.take(1))
  }

  // Return boolean and the matched cards. Input is sorted cards
  // Edge cases: High / low Ace
  // https://stackoverflow.com/questions/6985148/scala-detecting-a-straight-in-a-5-card-poker-hand-using-pattern-matching
  private def hasStraight(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    var count = 0
    (false, Seq())
  }

  private def hasFlush(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    // Find the suit with 5 or more cards
    //    cards.count(card => card.suit ==)
    // Sort card by high cards (Ace, mod 13?)
    ???
  }

  private def hasFullHouse(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    ???
  }

  private def hasThreeOfAKind(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    ???
  }

  private def hasTwoPairs(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    ???
  }

  private def hasOnePair(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    ???
  }

  private def hasHighCard(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    if (cards.head.number == 1) {
      (true, Seq(cards.head))
    } else {
      (true, Seq(cards.last))
    }
  }
}

// Check Straight Flush by checking Straight + Flush
// Check Four of a kind
// Check Full house by checking Three of a kind + (Different) Pair
// Check Straight from previous result
// Check Flush from previous result
// Check Check Three of a kind from previous result
// Check Two pairs from previous result
// Check One pair
// Check High card
// return immediately if you find a high card -> Do not have to go down all the conditional path

// Improvements
// Show current best combination between turns/rounds

// Edge cases:
// Same hand => Find higher hand with high card (Texas hold'em suit does not matter?)
// Ace High/Low for Straight / Straight flush
// Royal straight flush will naturally win when comparing high cards
// Count the best 5 cards
// You can have a tie

// Highest to lowest: A, K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3 and 2