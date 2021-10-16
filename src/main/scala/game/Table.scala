package game

import cards.{DeckCards, PlayCard}
import poker.CardLine

class Table(cards: List[String]) {
  require(cards.length == 13, "Cards length should be 13")

  private val parsedCards: List[PlayCard] = cards.map(card => new PlayCard(card))
  private val top :: middle :: bottom:: Nil = List(
    parsedCards.slice(0, 3),
    parsedCards.slice(3, 8),
    parsedCards.slice(8, 13)
  )

  def print(): Unit = println(s"--\n $top \n $middle \n $bottom")

  def cl1 = new CardLine(bottom)
  val x: Map[DeckCards.Value, Int] = cl1.getPairs
  println(x)
}