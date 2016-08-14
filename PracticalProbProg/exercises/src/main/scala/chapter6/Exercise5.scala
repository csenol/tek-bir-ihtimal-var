package chapter6

import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Flip, Constant}
import com.cra.figaro.library.atomic.discrete.{FromRange, Binomial}
import com.cra.figaro.library.collection.VariableSizeArray
import com.cra.figaro.library.compound.If

/**
  * Created by csenol on 8/14/16.
  */
object Exercise5 {

  val purchases =
    VariableSizeArray(Binomial(100, 0.1), (i: Int) =>
      If(Flip(0.5), FromRange(1, 11), Constant(0)))
  val total = purchases.foldLeft(0)(_ + _).map(_.toDouble)
  val alg = Importance(10000, total)
  alg.start()
  println(alg.mean(total))

}
