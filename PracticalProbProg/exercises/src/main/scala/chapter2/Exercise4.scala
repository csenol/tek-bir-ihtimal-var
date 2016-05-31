package chapter2

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.atomic.discrete.FromRange

/**
  * Created by csenol on 5/30/16.
  */
object Exercise4 extends App {


  val total11 = for {
    firstRoll <- FromRange(1, 7)
    secondRoll <- FromRange(1, 7)
  } yield firstRoll + secondRoll

  println(VariableElimination.probability(total11, 11))
  //0.05555555555555555

}
