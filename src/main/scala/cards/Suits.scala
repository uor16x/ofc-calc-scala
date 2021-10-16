package cards

import scala.util.{Failure, Success, Try}

object Suits extends Enumeration with CardParsable {
  type Suits = Value
  val Hearts, Diamonds, Clubs, Spades = Value

  val notations: Map[Char, Int] = Suits.values
    .zipWithIndex
    .map { case (suit, index) => suit.toString.toLowerCase()(0) -> index }
    .toMap
}