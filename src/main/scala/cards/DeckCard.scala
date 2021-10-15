package cards

object DeckCard extends Enumeration {
  type DeckCard = Value
  val Two, Three, Four, Five,
    Six,  Seven, Eight, Nine,
    Ten, Jack, Queen, King, Ace = Value
}