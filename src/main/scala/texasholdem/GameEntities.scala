package texasholdem

case class GameState(currentDeck: Deck,
                     players: Seq[Player],
                     table: Table)


case class Player(hand: Seq[Card])

case class Table(cards: Seq[Card])

class DeckBuilder() {
  private val numbers = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
  private val suits = Seq(Clubs, Diamonds, Hearts, Spades)

  private val cards: Seq[Card] = for {
    suit <- suits
    number <- numbers
  } yield Card(number, suit)

  val deck: Deck = Deck(cards)
}

case class Deck(cards: Seq[Card])

sealed trait Suit

case object Clubs extends Suit

case object Diamonds extends Suit

case object Hearts extends Suit

case object Spades extends Suit

case class Card(number: Int, suit: Suit) {
  // Check for number 1 <= number <= 13
  // Assume number is correct for now.
}