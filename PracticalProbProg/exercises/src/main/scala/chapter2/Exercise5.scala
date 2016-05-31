package chapter2

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.atomic.discrete.FromRange

/**
  * Created by csenol on 5/30/16.
  */
object Exercise5 extends App {

  val firstRoll = FromRange(1, 7)
  val secondRoll = FromRange(1, 7)
  val total = for {
    f1 <- firstRoll
    f2 <- secondRoll
  } yield f1 + f2

  total.setCondition( _ > 8)

  println(VariableElimination.probability(firstRoll, 6))
  // 0.4

}
