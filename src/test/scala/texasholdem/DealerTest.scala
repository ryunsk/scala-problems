package texasholdem

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DealerTest extends AnyFlatSpec with Matchers {

  it should "deal two cards to two players" in {
    val initialDeck = Deck(Seq(Card(1, Hearts), Card(2, Hearts), Card(3, Hearts), Card(4, Hearts)))
    val player1 = Player(Seq())
    val player2 = Player(Seq())
    val table = Table(Seq())
    val initialState = GameState(initialDeck, Seq(player1, player2), table)

    val deckAfter = Deck(Seq())
    val player1After = Player(Seq(Card(1, Hearts), Card(2, Hearts)))
    val player2After = Player(Seq(Card(3, Hearts), Card(4, Hearts)))
    Dealer.dealCardsToAllPlayers(initialState) shouldEqual GameState(deckAfter, Seq(player1After, player2After), table)
  }

  it should "discard top card and deal 3 cards to an empty table" in {
    val initialDeck = Deck(Seq(Card(1, Hearts), Card(2, Hearts), Card(3, Hearts), Card(4, Hearts)))
    val player1 = Player(Seq())
    val player2 = Player(Seq())
    val table = Table(Seq())
    val initialState = GameState(initialDeck, Seq(player1, player2), table)

    val deckAfter = Deck(Seq())
    val tableAfter = Table(Seq(Card(2, Hearts), Card(3, Hearts), Card(4, Hearts)))
    Dealer.dealCardsToTable(initialState, numberOfCards = 3) shouldEqual GameState(deckAfter, Seq(player1, player2), tableAfter)
  }
}
