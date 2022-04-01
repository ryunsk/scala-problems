package texasholdem

import scala.Console.in

class DeckTest extends AnyFlatSpec with Matchers {

  val deckBuilder = new DeckBuilder
  it should "A full deck of cards should have 52 cards" in {
    deckBuilder.deck.cards.length shouldEqual 52
  }
}
