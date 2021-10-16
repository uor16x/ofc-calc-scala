package game

import cards.{DeckCards, PlayCard}
import poker.{CardLine, Combination}

class Table(cards: List[String]) {
  require(cards.length == 13, "Cards length should be 13")

  private val parsedCards: List[PlayCard] = cards.map(card => new PlayCard(card))
  val top :: middle :: bottom:: Nil = List(
    Combination.calculate(new CardLine(parsedCards.slice(0, 3))),
    Combination.calculate(new CardLine(parsedCards.slice(3, 8))),
    Combination.calculate(new CardLine(parsedCards.slice(8, 13))),
  )

  def print(): Unit = println(s"--\n $top \n $middle \n $bottom")
}