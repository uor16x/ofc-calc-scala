package cards

object DeckCards extends Enumeration with CardParsable {
  type DeckCards = Value
  val Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King,
    Ace = Value

  val notations: Map[Char, Int] = DeckCards.values
    .zipWithIndex
    .map {
      case (card: DeckCards, index) =>
        if (index >= 8) card.toString.toUpperCase()(0) -> index
      else (index + 2).toString()(0) -> index
    }.toMap
}