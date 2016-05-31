package chapter2

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Select
import com.cra.figaro.library.atomic.discrete.FromRange

/**
  * Created by csenol on 5/31/16.
  */
object Exercise7 extends App {

  val spinner = Select(0.2 -> 4, 0.2 -> 6, 0.2 -> 8, 0.2 -> 12, 0.2 -> 20)
  val dice = spinner.flatMap(s => FromRange(1, s + 1))

  //a
  println(VariableElimination.probability(spinner, 12))

  //b
  println(VariableElimination.probability(dice, 7))

  //c
  dice.observe(7)
  println(VariableElimination.probability(spinner, 12))

  //d
  dice.unobserve()
  spinner.observe(12)
  println(VariableElimination.probability(dice, 7))


}
