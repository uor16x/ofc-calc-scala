package poker

import poker.Combination._

class TableCombination (rank: Combination, data: CombinationData) {
  override def toString: String = s"{ $rank } :: $data"
}