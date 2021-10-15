package poker

object Combination extends Enumeration {
  type Combination = Value
  val
    Kicker,
    Pair,
    TwoPairs,
    ThreeOfAKind,
    Straight,
    Flush,
    FullHours,
    FourOfAKind,
    StraightFlush,
    RoyalFlush = Value
}