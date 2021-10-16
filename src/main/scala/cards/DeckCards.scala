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
      // Indicies [0-7] - numbers - only index (+2 because card nominations start with 2)
      // Indicies [8-13] - broadway - take first char of name and apply upper case
      case (card: DeckCards, index) =>
        if (index >= 8) card.toString.toUpperCase()(0) -> index
      else (index + 2).toString()(0) -> index
    }.toMap
}