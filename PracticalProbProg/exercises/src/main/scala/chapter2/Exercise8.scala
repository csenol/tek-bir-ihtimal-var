package chapter2

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.{Chain, Select}
import com.cra.figaro.library.atomic.discrete.FromRange
import com.cra.figaro.library.compound.^^

/**
  * Created by csenol on 5/31/16.
  */
object Exercise8 extends App {


  val spinner1 = Select(0.2 -> 4, 0.2 -> 6, 0.2 -> 8, 0.2 -> 12, 0.2 -> 20)
  val spinner2 = Select(0.2 -> 4, 0.2 -> 6, 0.2 -> 8, 0.2 -> 12, 0.2 -> 20)
//  val paired = spinner1.flatMap( s1 => spinner2.map ((s1, _)))
  val paired = ^^(spinner1, spinner2)
  paired.setConstraint(bb => if (bb._1 == bb._2) 1.0 else 0.5)
  val dice1 = spinner1.flatMap(s => FromRange(1, s + 1))
  val dice2 = spinner2.flatMap(s => FromRange(1, s + 1))


  //a
  println(VariableElimination.probability(dice2, 7))

  //b
  dice1.observe(7)
  println(VariableElimination.probability(dice2, 7))

}
