package poker

import poker.Combination._

class TableCombination (val rank: Combination, val data: CombinationData) {
  override def toString: String = s"{ $rank } :: $data"

  def isRank(rank: Combination): Boolean = this.rank == rank

  def isSame(that: TableCombination): Boolean = this.isRank(that.rank) &&
    this.data.eq(that.data)

  def wins(that: TableCombination): Option[Boolean] = {
    if (this isSame that)
      None
    else if (this.rank != that.rank)
      Some(this.rank > that.rank)
    else Some(this.data.wins(that.data))
  }
}