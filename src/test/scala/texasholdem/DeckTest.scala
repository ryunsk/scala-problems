package texasholdem

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class DeckTest extends AnyFlatSpec with Matchers {

  val deckBuilder = new DeckBuilder
  it should "A full deck of cards should have 52 cards" in {
    deckBuilder.deck.cards.length shouldEqual 52
  }
}
