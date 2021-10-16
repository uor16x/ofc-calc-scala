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

  test("Straight flush") {
    val straightFlush = List("9h", "Kh", "Qh", "Jh", "Th")
      .map(cardStr => new PlayCard(cardStr))
    val parsedStraightFlush: TableCombination = Combination.calculate(new CardLine(straightFlush))
    assert(parsedStraightFlush.isRank(Combination.StraightFlush), "Straight flush")

    var straightFlushWheel = List("2h", "3h", "4h", "5h", "Ah")
      .map(cardStr => new PlayCard(cardStr))
    val parsedStraightFlushWheel: TableCombination = Combination.calculate(new CardLine(straightFlushWheel))
    assert(parsedStraightFlush.isRank(Combination.StraightFlush), "Straight flush wheel")
  }

  test("Four of a kind") {
    val fourOfAKind = List("2h", "2d", "2c", "2s", "Ah")
      .map(cardStr => new PlayCard(cardStr))
    val parsed: TableCombination = Combination.calculate(new CardLine(fourOfAKind))
    assert(parsed.isRank(Combination.FourOfAKind))
  }

  test("Full house") {
    val fullHouse = List("2h", "2d", "2c", "As", "Ah")
      .map(cardStr => new PlayCard(cardStr))
    val parsed: TableCombination = Combination.calculate(new CardLine(fullHouse))
    assert(parsed.isRank(Combination.FullHouse))
  }

  test("Flush") {
    val flush = List("2h", "4h", "6h", "8h", "Ah")
      .map(cardStr => new PlayCard(cardStr))
    val parsed: TableCombination = Combination.calculate(new CardLine(flush))
    assert(parsed.isRank(Combination.Flush))
  }

  test("Straight") {
    val straight = List("6h", "7c", "8d", "9d", "Th")
      .map(cardStr => new PlayCard(cardStr))
    val parsed: TableCombination = Combination.calculate(new CardLine(straight))
    assert(parsed.isRank(Combination.Straight), "Straight")

    val straightWheel = List("2h", "3c", "4d", "5h", "Ah")
      .map(cardStr => new PlayCard(cardStr))
    val parsedStraightWheel: TableCombination = Combination.calculate(new CardLine(straightWheel))
    assert(parsedStraightWheel.isRank(Combination.Straight), "Straight")
  }

  test("ThreeOfAKind") {
    val threeOfAKind3 = List("7h", "7c", "7d")
      .map(cardStr => new PlayCard(cardStr))
    val parsed3: TableCombination = Combination.calculate(new CardLine(threeOfAKind3))
    assert(parsed3.isRank(Combination.ThreeOfAKind), "Three of a kind (3 cards)")

    val threeOfAKind5 = List("7h", "7c", "7d", "Kc", "Ts")
      .map(cardStr => new PlayCard(cardStr))
    val parsed5: TableCombination = Combination.calculate(new CardLine(threeOfAKind5))
    assert(parsed5.isRank(Combination.ThreeOfAKind), "Three of a kind (5 cards)")
  }

  test("TwoPairs") {
    val twoPairs = List("7h", "7c", "8d", "8d", "Th")
      .map(cardStr => new PlayCard(cardStr))
    val parsed: TableCombination = Combination.calculate(new CardLine(twoPairs))
    assert(parsed.isRank(Combination.TwoPairs))
  }

  test("Pair") {
    val pair3 = List("7h", "7c", "Ad")
      .map(cardStr => new PlayCard(cardStr))
    val parsed3: TableCombination = Combination.calculate(new CardLine(pair3))
    assert(parsed3.isRank(Combination.Pair), "Pair (3 cards)")

    val pair5 = List("7h", "7c", "Jd", "Kc", "Ts")
      .map(cardStr => new PlayCard(cardStr))
    val parsed5: TableCombination = Combination.calculate(new CardLine(pair5))
    assert(parsed5.isRank(Combination.Pair), "Pair (5 cards)")
  }

  test("Kicker") {
    val kicker3 = List("7h", "8c", "Ad")
      .map(cardStr => new PlayCard(cardStr))
    val parsed3: TableCombination = Combination.calculate(new CardLine(kicker3))
    assert(parsed3.isRank(Combination.Kicker), "Kicker (3 cards)")

    val pair5 = List("7h", "8c", "Jd", "Kc", "Ts")
      .map(cardStr => new PlayCard(cardStr))
    val parsed5: TableCombination = Combination.calculate(new CardLine(pair5))
    assert(parsed5.isRank(Combination.Kicker), "Kicker (5 cards)")
  }
}
