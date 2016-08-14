package chapter8

import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language._
import com.cra.figaro.library.atomic.discrete.Uniform
import com.cra.figaro.library.compound.If

/**
  * Created by csenol on 8/14/16.
  */
object Exercise5 {
  abstract class Network {
    val nodes: Element[List[Int]]
    // We use an immutable map to represent the state of edges in a
    // network. If an edge is added or deleted, a new map is created that
    // is changed in one place.
    val edges: Element[Map[(Int, Int), Boolean]]
  }
  case class InitialNetwork() extends Network {
    // start with two nodes so we can add an edge on the first iteration
    val nodes = Constant(List(1, 0))
    val edges = Constant(Map[(Int, Int), Boolean]())
  }
  case class NextNetwork(current: Network) extends Network {
    val newNodeAdded = Flip(0.1)
    val nodes =
      Apply(current.nodes, newNodeAdded, (ns: List[Int], b: Boolean) =>
        if (b) ns.length :: ns else ns)
    val edges =
      If(newNodeAdded,
        current.edges,
        Chain(nodes, (ns: List[Int]) => {
          val first = Uniform(ns:_*)
          val second = Uniform(ns.filterNot(_ == first):_*)
          Apply(current.edges, first, second,
            (es: Map[(Int, Int), Boolean], f: Int, s: Int) =>
              es + ((f, s) -> !es.getOrElse((f, s), false)))
        }))
  }
  def nthNetwork(n: Int): Network =
    if (n <= 0) InitialNetwork() else NextNetwork(nthNetwork(n - 1))
  val count =
    Apply(nthNetwork(100).edges, (m: Map[(Int, Int), Boolean]) =>
      m.values.filter(_ == true).size.toDouble)
  val alg = Importance(10000, count)
  alg.start()
  println(alg.mean(count))
}
