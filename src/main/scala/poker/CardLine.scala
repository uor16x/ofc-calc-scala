package poker

import cards.DeckCards.DeckCards
import cards.{DeckCards, PlayCard}

import scala.math.abs

case class InvalidNumberOfPairs(message: String)
  extends Exception(message)
case class InvalidLengthOfLine(message: String = "Line should be 5 for this combination")
  extends Exception(message)

class CardLine(private var cards: List[PlayCard]) {
  //= Config
  require(cards.length >= 3 && cards.length <= 5, "Invalid card line length")
  cards = cards.sortWith((a, b) => a.value.id > b.value.id)

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
      abs(cards(index + 1).value.id - cards(index).value.id) == 1)
      .forall(item => item)

  def getPairs: Map[DeckCards, List[PlayCard]] = cards
    .groupBy((card: PlayCard) => card.value)
    .map { case ( deckCard: DeckCards, pairsList: List[PlayCard]) => (deckCard, pairsList) }
    .filter{ case (_: DeckCards.Value, list) => list.length > 1 }
}
