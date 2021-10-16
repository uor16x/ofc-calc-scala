package cards

import cards.DeckCards.DeckCards
import cards.Suits.Suits

class PlayCard(card: String) {
  require(card.length == 2, "Card length should be 2")

  private val value::suit::Nil = List(
    DeckCards(DeckCards.parse(card.head).get),
    Suits(Suits.parse(card.tail.head).get),
  )
}