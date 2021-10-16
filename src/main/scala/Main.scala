import game.Table

object Main{
  def main(args: Array[String]): Unit = {
    println("Hello")
    val cards = List(
      "2h", "3h", "4h",
      "Th", "Th", "Ah", "8h", "8h",
      "4c", "4d", "Ah", "5h", "5d"
    )
    val t = new Table(cards)
    t.print()
  }
}