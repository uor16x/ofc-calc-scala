package poker

import cards.PlayCard

class CardLine(cards: List[PlayCard]) {
  def isLongLine: Boolean = cards.length == 5

  def isFlush: Boolean = isLongLine &&
    cards.forall(card => card.suit == cards.head.suit)
}
