import game.Table

object Main{
  def main(args: Array[String]): Unit = {
    println("Hello")
    val cards = List(
      "2h", "3h", "4h",
      "5h", "6h", "7h", "8h", "Th",
      "3h", "2h", "4h", "5h", "Ah"
    )
    val t = new Table(cards)
    t.print()
  }
}