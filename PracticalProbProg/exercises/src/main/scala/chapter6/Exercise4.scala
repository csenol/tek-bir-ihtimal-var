package chapter6

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Select
import com.cra.figaro.library.collection.Container

/**
  * Created by csenol on 8/14/16.
  */
object Exercise4 {

  val numRows = 10
  val numColumns = 10
  val mine = Array.fill(numRows, numColumns)(Select(0.4 -> 1, 0.6 -> 0))
  val count = Array.tabulate(numRows, numColumns)((i: Int, j: Int) => {
    val neighbors =
      for {
        k <- i - 1 to i + 1
        l <- j - 1 to j + 1
        if k >= 0 && k < numRows
        if l >= 0 && l < numColumns
        if k != i || l != j
      } yield mine(k)(l)
    Container(neighbors:_*).reduce(_ + _)
  })
  val data =
    Array("?2M???????", "1?????????", "?1????????", "??????????",
      "??????????", "??????????", "??????????", "??????????",
      "??????????", "??????????")
  for {
    i <- 0 until numRows
    j <- 0 until numColumns
  } {
    data(i)(j) match {
      case 'M' => mine(i)(j).observe(1)
      case d if d.isDigit =>
        mine(i)(j).observe(0)
        count(i)(j).observe(d - '0')
      case '?' => ()
    }
  }
  println(VariableElimination.probability(mine(1)(1), 1))



}
