import game.{Player, Table}

object Main{
  def main(args: Array[String]): Unit = {
    println("Hello")
    val cards1 = List(
      "2h", "3h", "4h",
      "Th", "Th", "Ah", "8h", "8h",
      "4c", "4d", "Ah", "5h", "5d"
    )
    val cards2 = List(
      "2h", "3h", "5h",
      "Th", "Th", "Ah", "8h", "8h",
      "4c", "4d", "Ah", "5h", "5d"
    )
    val t1 = new Table(cards1)
    val t2 = new Table(cards2)

    val player1 = new Player(t1)
    val player2 = new Player(t2)
  }
}