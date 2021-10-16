package poker

import cards.PlayCard

object Combination extends Enumeration {
  type Combination = Value
  val
    Kicker,
    Pair,
    TwoPairs,
    ThreeOfAKind,
    Straight,
    Flush,
    FullHouse,
    FourOfAKind,
    StraightFlush,
    RoyalFlush = Value

  def calculate(cards: List[PlayCard]): TableCombination = {
    null
  }
}

class CombinationData(value: PlayCard) {}

case class TwoPairsData(pair1: PlayCard, pair2: PlayCard)
  extends CombinationData(value = null) {}

case class FullHouseData(high: PlayCard, low: PlayCard)
  extends CombinationData(value = null) {}
