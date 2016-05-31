package chapter2

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Element
import com.cra.figaro.library.atomic.discrete.FromRange

/**
  * Created by csenol on 5/30/16.
  */
object Exercise6 extends App {

  def propOfDouble: Element[Boolean] = {
    FromRange(1, 7) === FromRange(1, 7)
  }

  val lst = List.fill(3)(propOfDouble)
  val prob = lst.reduce(_ && _)

  println(VariableElimination.probability(prob, true))
  //1/6*1/6*1/6
}
