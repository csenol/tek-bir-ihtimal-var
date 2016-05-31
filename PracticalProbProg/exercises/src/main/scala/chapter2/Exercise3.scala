package chapter2

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language.Flip

/**
  * Created by csenol on 5/30/16.
  */
object Exercise3 extends App {

  //a
  {
    val x = Flip(0.4)
    val y = Flip(0.4)
    val z = x
    val w = x === z
    println(VariableElimination.probability(w, true))
  }
  // 1.0 because x is nothing but z

  //b
  {
    val x = Flip(0.4)
    val y = Flip(0.4)
    val z = y
    val w = x === z
    println(VariableElimination.probability(w, true))
  }
  //0.52 because 0.4*0.4 + 0.6*0.6
}
