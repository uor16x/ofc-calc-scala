package game

import cards.PlayCard

class Table(cards: List[String]) {
  require(cards.length == 13, "Cards length should be 13")

  val top: List[PlayCard] = List(null)
  val middle: List[PlayCard] = List(null)
  val bottom: List[PlayCard] = List(null)

  def parse(): Unit = {
    val parsedCards = cards.map(card => new PlayCard(card))
  }
}