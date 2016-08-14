package chapter6

import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language._
import com.cra.figaro.library.atomic.discrete.FromRange
import com.cra.figaro.library.collection.Container
import com.cra.figaro.library.compound._

/**
  * Created by csenol on 8/14/16.
  */
object Exercise6 {

  class Group extends Container[Int, Int] {
    val indices = 0 until 512
    val numChildren = FromRange(1, 6)

    def generate(i: Int) = ???

    def generate(indices: List[Int]): Map[Int, Element[Int]] = {
      ???
      // ensure that the constraints are created as soon as the group is created

    }

    def main(args: Array[String]) {
      val groups = ???
      val groupExists = ???
      val groupTotals = ???

      val grandTotal: Element[Double] = ???

      val alg = Importance(10000, grandTotal)
      alg.start()
      println(alg.mean(grandTotal))
    }

  }

}
