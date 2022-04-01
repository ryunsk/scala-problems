package texasholdem

import scala.util.Random

object Dealer {
  // Handles the game state

  def shuffleDeck(deck: Deck): Deck = {
    Deck(Random.shuffle(deck.cards))
  }

  // Filters cards
  def dealCardsToAllPlayers(gameState: GameState): GameState = {
    val numberOfPlayers = gameState.players.length
    val numberOfCardsToTake = 2 * numberOfPlayers

    val cardsToDealAndDeck = drawCards(numberOfCardsToTake, gameState.currentDeck)

    val playersWithCards = cardsToDealAndDeck.drawnCards.sliding(2, 2).map(Player).toSeq
    gameState.copy(currentDeck = cardsToDealAndDeck.remainingCards, players = playersWithCards)
  }

  def dealCardsToTable(gameState: GameState, numberOfCards: Int): GameState = {
    val topCardDiscarded = removeTopCardFromDeck(gameState.currentDeck)

    val cardsToDealAndDeck = drawCards(numberOfCards, topCardDiscarded)

    val tableWithCards = Table(gameState.table.cards ++ cardsToDealAndDeck.drawnCards)

    gameState.copy(currentDeck = cardsToDealAndDeck.remainingCards, table = tableWithCards)
  }

  private def drawCards(numberOfCardsToTake: Int, deck: Deck): CardsDrawnAndRemaining = {
    val cardsDrawn = deck.cards.take(numberOfCardsToTake)
    val remainingCards = removeCardsFromDeck(cardsDrawn, deck)
    CardsDrawnAndRemaining(cardsDrawn, remainingCards)
  }

  private def removeCardsFromDeck(cards: Seq[Card], deck: Deck): Deck = {
    Deck(deck.cards.filter(x => !cards.contains(x)))
  }

  private def removeTopCardFromDeck(deck: Deck): Deck = {
    Deck(deck.cards.tail)
  }

  private case class CardsDrawnAndRemaining(drawnCards: Seq[Card], remainingCards: Deck)

}
