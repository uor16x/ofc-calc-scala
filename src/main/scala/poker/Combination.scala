package poker

import cards.DeckCards.DeckCards
import cards.{DeckCards, PlayCard}

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

  def calculate(cardLine: CardLine): TableCombination = {
    val pairs = cardLine.getPairs
    pairs.size match {
      case 0 => CombinationGroupResolver.sequence(cardLine)
      case _ => CombinationGroupResolver.pairs(pairs)
    }
  }
}

object CombinationGroupResolver {
  val sequenceCombinator = Map(
    "straight" -> (false, true, false),
    "wheel" -> (false, false, true),
    "flush" -> (true, false, false),
    "straightFlush" -> (true, true, false),
    "straightFlushWheel" -> (true, false, true),
    "kicker" -> (false, false, false)
  )

  def sequence(cardLine: CardLine): TableCombination = {
    (cardLine.isFlush, cardLine.isSequence, cardLine.isWheel) match {
      case (false, true, _) =>
        new TableCombination(Combination.Straight, new CombinationData(cardLine.head))
      case (false, false, true) =>
        new TableCombination(Combination.Straight, new CombinationData(cardLine.tail.head))
      case (true, false, false) =>
        new TableCombination(Combination.Flush, new CombinationData(cardLine.head))
      case (true, true, false) => cardLine.head.value match {
        case DeckCards.Ace =>
          new TableCombination(Combination.RoyalFlush, new CombinationData(cardLine.head))
        case _ =>
          new TableCombination(Combination.StraightFlush, new CombinationData(cardLine.head))
      }
      case (true, false, true) =>
        new TableCombination(Combination.StraightFlush, new CombinationData(cardLine.tail.head))
      case (false, false, false) =>
        new TableCombination(Combination.Kicker, new CombinationData(cardLine.head))
    }
  }

  def straight(cardLine: CardLine): TableCombination = {
    null
  }

  def pairs(pairs: Map[DeckCards, Int]): TableCombination = {
    null
  }
}

class CombinationData(value: PlayCard) {
  override def toString: String = s"[$value]"
}

case class TwoPairsData(pair1: PlayCard, pair2: PlayCard)
  extends CombinationData(value = null) {
  override def toString: String = s"[${pair1.value}] & [${pair2.value}]"
}

case class FullHouseData(high: PlayCard, low: PlayCard)
  extends CombinationData(value = null) {
  override def toString: String = s"[${high.value}] & [${low.value}]"
}
