package game

import cards.PlayCard

class Table(cards: List[String]) {
  require(cards.length == 13, "Cards length should be 13")

  private val parsedCards: List[PlayCard] = cards.map(card => new PlayCard(card))
  private val top :: middle :: bottom:: Nil = List(
    cards.slice(0, 3),
    cards.slice(3, 8),
    cards.slice(8, 13)
  )

  def print(): Unit = println(s"--\n $top \n $middle \n $bottom")
}