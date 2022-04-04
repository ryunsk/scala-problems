package texasholdem

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PokerHandRankerTest extends AnyFlatSpec with Matchers {

  behavior of "ranking hands"
  it should "rank straight flush - high ace" in {
    val player = Player(Seq(Card(13, Hearts), Card(12, Hearts)))
    val table = Table(Seq(Card(11, Hearts), Card(10, Hearts), Card(1, Hearts), Card(3, Diamonds), Card(3, Clubs)))

    PokerHandRanker.rankHand(player, table) shouldEqual StraightFlush
  }

  it should "rank straight flush - low ace" in {
    val player = Player(Seq(Card(1, Spades), Card(2, Spades)))
    val table = Table(Seq(Card(3, Spades), Card(4, Spades), Card(5, Spades), Card(7, Clubs), Card(9, Diamonds)))

    PokerHandRanker.rankHand(player, table) shouldEqual StraightFlush
  }

  it should "rank four of a kind" in {

  }

  it should "rank full house" in {

  }

  it should "rank flush" in {

  }

  it should "rank straight" in {

  }

  it should "rank three of a kind" in {

  }

  it should "rank two pair" in {

  }

  it should "rank one pair" in {

  }

  it should "rank high card" in {

  }

  behavior of "ranking two or more different hands"
  ignore should "rank the higher hand" in {

  }

}
