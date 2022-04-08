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
    if (straight._1) return (Straight, straight._2)

    val flush = hasFlush(allCardsSorted)
    if (flush._1) return (Flush, flush._2)

    val fullHouse = hasFullHouse(allCardsSorted)
    if (fullHouse._1) return (FullHouse, fullHouse._2)

    val threeOfAKind = hasThreeOfAKind(allCardsSorted)
    if (threeOfAKind._1) return (ThreeOfAKind, threeOfAKind._2)

    val twoPairs = hasTwoPairs(allCardsSorted)
    if (twoPairs._1) return (TwoPair, twoPairs._2)

    val onePair = hasOnePair(allCardsSorted)
    if (onePair._1) return (OnePair, onePair._2) else {
      val highCard = hasHighCard(allCardsSorted)
      (HighCard, highCard._2)
    }
  }

  // Return boolean and the matched cards. Input is sorted cards
  // Edge cases: High / low Ace
  // https://stackoverflow.com/questions/6985148/scala-detecting-a-straight-in-a-5-card-poker-hand-using-pattern-matching
  private def hasStraight(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    // case for Ace
    //    if (cards.head.number == 1) {
    //      val cardsWithHighAce = cards ++ Seq(Card(14, cards.head.suit))
    //    }
    cards.sliding(5, 1)

    var count = 0
    (false, Seq())
  }

  private def hasFlush(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    // Find the suit with 5 or more cards
    val cardsGroupedBySuit = cards.groupBy(_.suit)
    val suitWithFlush = cardsGroupedBySuit.filter {
      case (_, cards) => cards.length >= 5
    }
    if(suitWithFlush.size==1){
      // TODO: Case for ace
      (true, suitWithFlush.values.flatten.toSeq.sortBy(-_.number).take(5))
    } else{
      (false, Seq())
    }

  }

  private def hasFullHouse(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    (false, Seq())
  }

  private def hasThreeOfAKind(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    (false, Seq())
  }

  private def hasTwoPairs(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    (false, Seq())
  }

  private def hasOnePair(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    (false, Seq())
  }

  private def hasHighCard(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    // Return Ace (1) if it exists or the highest number
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