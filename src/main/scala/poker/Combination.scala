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
      case _ => CombinationGroupResolver.pairs(pairs, cardLine.isLong)
    }
  }
}

private object CombinationGroupResolver {
  def sequence(cardLine: CardLine): TableCombination = {
    (cardLine.isFlush, cardLine.isSequence, cardLine.isWheel) match {
      case (false, true, _) => // straight
        new TableCombination(Combination.Straight, new CombinationData(cardLine.head))
      case (false, false, true) => // wheel
        new TableCombination(Combination.Straight, new CombinationData(cardLine.tail.head))
      case (true, false, false) => // flush
        new TableCombination(Combination.Flush, new CombinationData(cardLine.head))
      case (true, true, false) => cardLine.head.value match { // straight/royal flush
        case DeckCards.Ace => // royal flush
          new TableCombination(Combination.RoyalFlush, new CombinationData(cardLine.head))
        case _ => // straight flush
          new TableCombination(Combination.StraightFlush, new CombinationData(cardLine.head))
      }
      case (true, false, true) => // straight flush wheel
        new TableCombination(Combination.StraightFlush, new CombinationData(cardLine.tail.head))
      case (false, false, false) => // kicker
        new TableCombination(Combination.Kicker, new CombinationData(cardLine.head))
    }
  }

  def pairs(pairsCounter: Map[DeckCards, List[PlayCard]], isLong: Boolean): TableCombination = {
    pairsCounter.size match {
      case 1 => singlePairHand(pairsCounter, isLong)
      case 2 => multiplePairsHand(pairsCounter, isLong)
      case _ => throw InvalidNumberOfPairs(s"Found $pairsCounter.size pairs")
    }
  }

  def singlePairHand(pairsCounter: Map[DeckCards, List[PlayCard]], isLong: Boolean): TableCombination = {
    val cards: List[PlayCard] = pairsCounter(pairsCounter.keys.head)
    cards.length match {
      case 4 => // four of a kind
        if (isLong) new TableCombination(Combination.FourOfAKind, new CombinationData(cards.head))
        else throw InvalidLengthOfLine()
      case 3 => // three of a kind
        new TableCombination(Combination.ThreeOfAKind, new CombinationData(cards.head))
      case 2 => // pair
        new TableCombination(Combination.Pair, new CombinationData(cards.head))
      case _ => throw InvalidNumberOfPairs(s"Found ${cards.length} pairs")
    }
  }

  def multiplePairsHand(pairsCounter: Map[DeckCards, List[PlayCard]], isLong: Boolean): TableCombination = {
    if (!isLong) throw InvalidLengthOfLine()
    val pairsSum = pairsCounter.values
      .map((cardList: List[PlayCard]) => cardList.length)
      .sum
    pairsSum match {
      case 4 =>  // two pairs
        val pair2 :: pair1 :: Nil = pairsCounter.values
          .map((currentPairCards: List[PlayCard]) => currentPairCards.head)
          .toList
          .sortBy(card => card.value.id)
        new TableCombination(Combination.TwoPairs, new CombinationData(pair1, pair2))
      case 5 => // full house
        val low :: high :: Nil = pairsCounter.values
          .toList
          .sortBy(cards => cards.size)
          .map(list => list.head)
        new TableCombination(Combination.FullHouse, new CombinationData(high, low))
      case _ => throw InvalidNumberOfPairs(s"Invalid multiple pairs sum: $pairsSum")
    }
  }
}

class CombinationData(val value: PlayCard, val extraValue: PlayCard = null) {
  override def toString: String = s"[$value]"

  def eq(that: CombinationData): Boolean =
    this.value.value == that.value.value &&
      (if (extraValue != null)
        this.extraValue.value == that.extraValue.value
      else true)

  def wins(that: CombinationData): Boolean =
    this.value.value.id > that.value.value.id &&
      (if (extraValue != null)
        this.extraValue.value.id > that.extraValue.value.id
      else true)
}