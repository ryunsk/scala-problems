package texasholdem

import scala.annotation.tailrec

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
    val straightFlush = hasStraightFlush(allCardsSorted)
    if (straightFlush._1) return (StraightFlush, straightFlush._2)

    val fourOfAKind = hasFourOfAKind(allCardsSorted)
    if (fourOfAKind._1) return (FourOfAKind, fourOfAKind._2)

    val fullHouse = hasFullHouse(allCardsSorted)
    if (fullHouse._1) return (FullHouse, fullHouse._2)

    val flush = hasFlush(allCardsSorted)
    if (flush._1) return (Flush, flush._2)

    val straight = hasStraight(allCardsSorted)
    if (straight._1) return (Straight, straight._2)

    val threeOfAKind = hasThreeOfAKind(allCardsSorted)
    if (threeOfAKind._1) return (ThreeOfAKind, threeOfAKind._2)

    val twoPairs = hasTwoPairs(allCardsSorted)
    if (twoPairs._1) return (TwoPair, twoPairs._2)

    val onePair = hasOnePair(allCardsSorted)
    if (onePair._1) return (OnePair, onePair._2)

    val highCard = hasHighCard(allCardsSorted)
    (HighCard, highCard._2)
  }

  // Return boolean and the matched cards. Input is sorted cards
  // Edge cases: High / low Ace
  // https://stackoverflow.com/questions/6985148/scala-detecting-a-straight-in-a-5-card-poker-hand-using-pattern-matching

  private def hasStraightFlush(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    val flush = hasFlush(cards)
    val straightFlush = hasStraight(flush._2)
    (straightFlush._1, straightFlush._2)
  }

  private def hasFourOfAKind(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    val cardsGroupedByNumber = cards.groupBy(_.number)
    val fours = cardsGroupedByNumber.filter { case (_, v) => v.length == 4 }
    if (fours.nonEmpty) {
      val highestPair = fours.getOrElse(1, fours(fours.keys.max))
      (true, highestPair)
    } else {
      (false, Seq())
    }
  }

  private def hasFullHouse(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    val threeOfAKind = hasThreeOfAKind(cards)
    val remainingCards = cards.filter(card => !threeOfAKind._2.contains(card))
    val pair = hasOnePair(remainingCards)
    if (threeOfAKind._1 && pair._1) {
      (true, threeOfAKind._2 ++ pair._2)
    } else {
      (false, Seq())
    }
  }

  private def hasFlush(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    // Find the suit with 5 or more cards
    val cardsGroupedBySuit = cards.groupBy(_.suit)
    val suitToFlushCards = cardsGroupedBySuit.filter {
      case (_, cards) => cards.length >= 5
    }
    if (suitToFlushCards.size == 1) {
      // Take Ace first if it exists
      val aceCard = suitToFlushCards.head._2.filter(_.number == 1)
      if (aceCard.size == 1) {
        (true, aceCard ++ suitToFlushCards.values.flatten.toSeq.sortBy(-_.number).take(4))
      } else {
        (true, suitToFlushCards.values.flatten.toSeq.sortBy(-_.number).take(5))
      }
    } else {
      (false, Seq())
    }
  }

  private def hasStraight(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    // Edge case for Straight flush as it may return an empty card
    if (cards.isEmpty) {
      return (false, Seq())
    }
    val possibleStraights = (Range.inclusive(1, 13).sliding(5, 1).map(_.toSet) ++ Seq(Set(10, 11, 12, 13, 1))).toSeq
    val cardsGroupedByNumber = cards.groupBy(_.number)
    val cardNumbers = cardsGroupedByNumber.keySet

    val straightNumbers = possibleStraights.filter(x => x.intersect(cardNumbers).size == 5)
    if (straightNumbers.nonEmpty) {
      // Get the highest straight here with A and K
      val highestStraight = straightNumbers.filter(x => x.intersect(Set(1, 13)).size == 2)
      if (highestStraight.size == 1) {
        val straightCards = highestStraight.head.map(x=>cardsGroupedByNumber(x).head).toSeq.sortBy(_.number)
        return (true, straightCards)
      } else {
        val highestNumber = straightNumbers.flatMap(x=>x.toSeq).max
        val highestStraight = straightNumbers.filter(x => x.intersect(Set(highestNumber)).size == 1)
        val straightCards = highestStraight.head.map(x=>cardsGroupedByNumber(x).head).toSeq.sortBy(_.number)
        return (true, straightCards)
      }
    }
    (false, Seq())
    // Unused
    // Case with Ace
    //    if (numbers.head == 1) {
    //      val numbersWithAce = numbers ++ Seq(14)
    //      val hasStraightBool = straightFinder(numbersWithAce, 1)
    //      if (hasStraightBool) {
    //
    //      } else {
    //        (false, Seq())
    //      }
    //
    //    } else {
    //      val hasStraightBool = straightFinder(numbers, 1)
    //      if (hasStraightBool) {
    //
    //      } else {
    //        (false, Seq())
    //      }
    //    }
  }

  // Unused
  @tailrec
  private def straightFinder(numbers: Seq[Int], count: Int): Boolean = {
    if (count == 5) {
      return true
    }
    if (numbers(1) == numbers.head + 1) {
      straightFinder(numbers.tail, count + 1)
    } else {
      false
    }
  }

  private def hasThreeOfAKind(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    val cardsGroupedByNumber = cards.groupBy(_.number)
    val threes = cardsGroupedByNumber.filter { case (_, v) => v.length == 3 }
    if (threes.nonEmpty) {
      val highestPair = threes.getOrElse(1, threes(threes.keys.max))
      (true, highestPair)
    } else {
      (false, Seq())
    }
  }

  private def hasTwoPairs(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    val cardsGroupedByNumber = cards.groupBy(_.number)
    val pairs = cardsGroupedByNumber.filter { case (_, v) => v.length == 2 }
    if (pairs.nonEmpty) {
      val highestPair = pairs.getOrElse(1, pairs(pairs.keys.max))
      val remainingPairs = pairs.filter { case (k, _) => k != highestPair.head.number }
      if (remainingPairs.nonEmpty) {
        val nextHighestPair = remainingPairs(remainingPairs.keys.max)
        (true, highestPair ++ nextHighestPair)
      } else {
        (false, Seq())
      }
    } else {
      (false, Seq())
    }
  }

  private def hasOnePair(cards: Seq[Card]): (Boolean, Seq[Card]) = {
    val cardsGroupedByNumber = cards.groupBy(_.number)
    val pairs = cardsGroupedByNumber.filter { case (_, v) => v.length == 2 }
    if (pairs.nonEmpty) {
      val highestPair = pairs.getOrElse(1, pairs(pairs.keys.max))
      (true, highestPair)
    } else {
      (false, Seq())
    }
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