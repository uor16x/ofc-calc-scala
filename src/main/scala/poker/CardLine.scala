package poker

import cards.DeckCards.DeckCards
import cards.{DeckCards, PlayCard}

class CardLine(private var cards: List[PlayCard]) {
  //= Config
  require(cards.length >= 3 && cards.length <= 5, "Invalid card line length")
  cards = cards.sortWith((a, b) => b.value.id > a.value.id)

  //= API
  def head: PlayCard = cards.head
  def tail: List[PlayCard] = cards.tail

  val isLong: Boolean = cards.length == 5

  def isFlush: Boolean = isLong &&
    cards.forall(card => card.suit == cards.head.suit)

  def isWheel: Boolean = isLong && cards
    .map(card => card.value.id)
    .equals(List(0, 1, 2, 3, 12))

  def isSequence: Boolean = isLong &&
    (for (index <- cards.init.indices) yield
      cards(index + 1).value.id - cards(index).value.id == 1)
      .forall(item => item)

  def getPairs: Map[DeckCards, Int] = cards
    .groupBy(card => card.value)
    .map{ case (deckCard: DeckCards, playCards) => (deckCard, playCards.length) }
    .filter{ case (_, occurrences: Int) => occurrences > 1 }
}
