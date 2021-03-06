package texasholdem

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PokerHandRankerTest extends AnyFlatSpec with Matchers {

  behavior of "ranking hands"
  it should "rank straight flush - high ace" in {
    val player = Player(Seq(Card(13, Hearts), Card(12, Hearts)))
    val table = Table(Seq(Card(11, Hearts), Card(10, Hearts), Card(1, Hearts), Card(3, Diamonds), Card(3, Clubs)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (StraightFlush, List(Card(1, Hearts), Card(10, Hearts), Card(11, Hearts), Card(12, Hearts), Card(13, Hearts)))
  }

  it should "rank straight flush - low ace" in {
    val player = Player(Seq(Card(1, Spades), Card(2, Spades)))
    val table = Table(Seq(Card(3, Spades), Card(4, Spades), Card(5, Spades), Card(7, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (StraightFlush, List(Card(1, Spades), Card(2, Spades), Card(3, Spades), Card(4, Spades), Card(5, Spades)))
  }

  it should "rank four of a kind" in {
    val player = Player(Seq(Card(1, Spades), Card(2, Spades)))
    val table = Table(Seq(Card(2, Diamonds), Card(4, Spades), Card(2, Hearts), Card(2, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (FourOfAKind, Seq(Card(2, Spades), Card(2, Diamonds), Card(2, Hearts), Card(2, Clubs)))
  }

  it should "rank full house" in {
    val player = Player(Seq(Card(13, Spades), Card(13, Hearts)))
    val table = Table(Seq(Card(3, Clubs), Card(3, Spades), Card(3, Diamonds), Card(4, Clubs), Card(7, Hearts)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (FullHouse, Seq(Card(3, Clubs), Card(3, Spades), Card(3, Diamonds), Card(13, Spades), Card(13, Hearts)))
  }

  it should "rank flush" in {
    val player = Player(Seq(Card(13, Spades), Card(13, Hearts)))
    val table = Table(Seq(Card(3, Spades), Card(10, Spades), Card(7, Spades), Card(5, Spades), Card(7, Hearts)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (Flush, Seq(Card(13, Spades), Card(10, Spades), Card(7, Spades), Card(5, Spades), Card(3, Spades)))
  }

  it should "rank flush - with Ace" in {
    val player = Player(Seq(Card(13, Spades), Card(13, Hearts)))
    val table = Table(Seq(Card(3, Spades), Card(10, Spades), Card(7, Spades), Card(5, Spades), Card(1, Spades)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (Flush, Seq(Card(1, Spades), Card(13, Spades), Card(10, Spades), Card(7, Spades), Card(5, Spades)))
  }

  it should "rank straight - high ace" in {
    val player = Player(Seq(Card(13, Hearts), Card(12, Spades)))
    val table = Table(Seq(Card(11, Hearts), Card(10, Hearts), Card(1, Clubs), Card(3, Diamonds), Card(3, Clubs)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (Straight, List(Card(1, Clubs), Card(10, Hearts), Card(11, Hearts), Card(12, Spades), Card(13, Hearts)))
  }

  it should "rank straight - low ace" in {
    val player = Player(Seq(Card(1, Spades), Card(2, Diamonds)))
    val table = Table(Seq(Card(3, Spades), Card(4, Spades), Card(5, Diamonds), Card(7, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (Straight, Seq(Card(1, Spades), Card(2, Diamonds), Card(3, Spades), Card(4, Spades), Card(5, Diamonds)))
  }

  it should "rank straight - multiple single cards" in {
    val player = Player(Seq(Card(1, Spades), Card(3, Diamonds)))
    val table = Table(Seq(Card(3, Spades), Card(2, Diamonds), Card(4, Diamonds), Card(5, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (Straight, Seq(Card(1, Spades), Card(2, Diamonds), Card(3, Diamonds), Card(4, Diamonds), Card(5, Clubs)))
  }

  it should "rank straight - multiple straights - high card - low ace loses" in {
    val player = Player(Seq(Card(1, Spades), Card(2, Diamonds)))
    val table = Table(Seq(Card(3, Spades), Card(4, Diamonds), Card(5, Diamonds), Card(6, Clubs), Card(7, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (Straight, List(Card(3, Spades), Card(4, Diamonds), Card(5, Diamonds), Card(6, Clubs), Card(7, Diamonds)))
  }

  it should "rank three of a kind" in {
    val player = Player(Seq(Card(1, Spades), Card(1, Diamonds)))
    val table = Table(Seq(Card(1, Clubs), Card(2, Spades), Card(5, Clubs), Card(7, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (ThreeOfAKind, Seq(Card(1, Spades), Card(1, Diamonds), Card(1, Clubs)))
  }

  it should "rank two pair" in {
    val player = Player(Seq(Card(1, Spades), Card(1, Diamonds)))
    val table = Table(Seq(Card(2, Clubs), Card(2, Spades), Card(5, Clubs), Card(7, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (TwoPair, Seq(Card(1, Spades), Card(1, Diamonds), Card(2, Clubs), Card(2, Spades)))
  }

  it should "rank one pair" in {
    val player = Player(Seq(Card(2, Spades), Card(13, Diamonds)))
    val table = Table(Seq(Card(12, Clubs), Card(3, Spades), Card(4, Clubs), Card(7, Clubs), Card(4, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (OnePair, Seq(Card(4, Clubs), Card(4, Diamonds)))
  }

  it should "rank one pair - High card Ace" in {
    val player = Player(Seq(Card(1, Spades), Card(1, Diamonds)))
    val table = Table(Seq(Card(12, Clubs), Card(2, Spades), Card(10, Clubs), Card(3, Hearts), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual
      (OnePair, Seq(Card(1, Spades), Card(1, Diamonds)))
  }

  it should "rank high card" in {
    val player = Player(Seq(Card(1, Spades), Card(2, Clubs)))
    val table = Table(Seq(Card(11, Spades), Card(13, Spades), Card(5, Diamonds), Card(7, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual(HighCard, Seq(Card(1, Spades)))

  }

  behavior of "ranking two or more different hands"
  ignore should "rank the higher hand" in {

  }

}
