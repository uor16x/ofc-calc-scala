package cards

import scala.util.{Failure, Success, Try}

case class FailedToParseException(message: String) extends Exception(message)

trait CardParsable {
  val notations: Map[Char, Int]

  def parse(symbol: Char): Try[Int] = notations.get(symbol) match {
    case Some(value: Int) => Success(value)
    case None => Failure(FailedToParseException(s"Failed to parse card part $symbol"))
  }
}
