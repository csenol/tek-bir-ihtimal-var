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
    val newNodeAdded = ???
    val nodes =  ???

    val edges = ???

  }
  def nthNetwork(n: Int): Network = ???

  val count = ???
//  val alg = Importance(10000, count)
//  alg.start()
//  println(alg.mean(count))
}
