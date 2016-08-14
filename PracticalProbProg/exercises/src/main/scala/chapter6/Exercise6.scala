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
    def generate(i :Int) =
      Chain(numChildren, (n: Int) =>
        if (n > i) If(Flip(0.5), FromRange(1, 11), Constant(0))
        else Constant(0))
    def generate(indices: List[Int]): Map[Int, Element[Int]] = {
      val result = indices.map(i => (i, apply(i))).toMap
      for {
        i <- indices
        j <- indices
        if j > i
      } {
        val pairElem = ^^(result(i), result(j))
        pairElem.addConstraint((pair: (Int, Int)) =>
          if ((pair._1 > 0) && (pair._2 > 0)) 1.0 else 0.8)
      }
      result
    }
    // ensure that the constraints are created as soon as the group is created
    generate(indices.toList)
  }
  def main(args: Array[String]) {
    val groups = Array.fill(40)(new Group)
    val groupExists = Array.fill(40)(Flip(0.1))
    val groupTotals =
      for { i <- 0 until 40 } yield {
        If(groupExists(i),
          groups(i).foldLeft(0)(_ + _),
          Constant(0))
      }
    val grandTotal: Element[Double] =
      Container(groupTotals:_*).foldLeft(0)(_ + _).map(_.toDouble)
    val alg = Importance(10000, grandTotal)
    alg.start()
    println(alg.mean(grandTotal))
  }

}
