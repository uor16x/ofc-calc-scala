package poker

import cards.{DeckCards, PlayCard}

class CardLine(private var cards: List[PlayCard]) {
  //= Config
  require(cards.length >= 3 && cards.length <= 5, "Invalid card line length")
  cards = cards.sortWith((a, b) => b.value.id > a.value.id)

  //= API
  def isFlush: Boolean = isLongLine &&
    cards.forall(card => card.suit == cards.head.suit)

  def isStraight: Boolean = isLongLine && (isSequence || isWheel)

  def getPairs: Map[DeckCards.Value, Int] = cards
    .groupBy(card => card.value)
    .map{ case (deckCard, playCards) => (deckCard, playCards.length) }

  //= Internal
  private val isLongLine: Boolean = cards.length == 5

  private def isSequence: Boolean =
    (for (index <- cards.init.indices) yield
      cards(index + 1).value.id - cards(index).value.id == 1)
      .forall(item => item)

  private def isWheel: Boolean = cards
    .map(card => card.value.id)
    .equals(List(0, 1, 2, 3, 12))
}
