package texasholdem

sealed trait PokerHand

case object StraightFlush extends PokerHand

case object FourOfAKind extends PokerHand

case object FullHouse extends PokerHand

case object Flush extends PokerHand

case object Straight extends PokerHand

case object ThreeOfAKind extends PokerHand

case object TwoPair extends PokerHand

case object OnePair extends PokerHand

case object HighCard extends PokerHand