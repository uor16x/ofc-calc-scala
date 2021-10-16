package test

import cards.PlayCard
import org.scalatest.funsuite.AnyFunSuite
import poker.{CardLine, Combination, TableCombination}


class CombinationParseTest extends AnyFunSuite {
  test("Royal flush") {
    val royalFlush = List("Ah", "Kh", "Qh", "Jh", "Th")
      .map(cardStr => new PlayCard(cardStr))

    val parsed: TableCombination = Combination.calculate(new CardLine(royalFlush))
    assert(parsed.isRank(Combination.RoyalFlush))
  }
}
