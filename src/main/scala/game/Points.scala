package game

import cards.PlayCard
import poker.Combination.{Combination, Pair, ThreeOfAKind}

case class InvalidCombination(combination: Combination, line: String)
  extends Exception(s"Invalid combination: $combination found at line $line")

object Points {
  private var middlePoints = List(0, 0, 0, 2, 4, 8, 12, 20, 30, 50)
  def top(combination: Combination, value: PlayCard): Int =
    combination match {
      case Pair => if (value.value.id < 4) 0 else value.value.id - 3
      case ThreeOfAKind => value.value.id + 10
      case _ => throw InvalidCombination(combination, "top")
    }
  def middle(combination: Combination): Int = middlePoints(combination.id)
  def bottom(combination: Combination): Int = middlePoints
    .map((point: Int) => if (point > 2) point / 2 else 0 )(combination.id)
}
