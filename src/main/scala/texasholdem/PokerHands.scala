package texasholdem

sealed trait PokerHands

case object StraightFlush extends PokerHands
case object FourOfAKind extends PokerHands
case object FullHouse extends PokerHands
case object Flush extends PokerHands
case object Straight extends PokerHands
case object ThreeOfAKind extends PokerHands
case object TwoPair extends PokerHands
case object OnePair extends PokerHands
case object HighCard extends PokerHands