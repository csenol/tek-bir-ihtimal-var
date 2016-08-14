package chapter8

import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language._
import com.cra.figaro.library.compound.If

/**
  * Created by csenol on 8/14/16.
  */
object Exercise1 {
  abstract class State {
    val alicePoints: Element[Int]
    val bobPoints: Element[Int]
    // Note: terminal and bobWinning are defs to avoid null pointer
    // exceptions on initialization. It's a Scala quirk that when you
    // initialize a variable that uses abstract variables in it's
    // definition, it will throw a null pointer exception.
    def terminal: Element[Boolean] = ???

    def bobWinning: Element[Boolean] = ???

  }
  case class InitialState() extends State {
    val alicePoints = ???
    val bobPoints = ???
  }
  case class NextState(current: State) extends State {
    val aliceWinsPoint = ???
    val alicePoints = ???

    val bobPoints = ???

  }
  def playFrom(current: State): Element[Boolean] = {
    ???
  }
  val bobWins = ???
  println(Importance.probability(bobWins, true))
}
