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
    def terminal: Element[Boolean] =
      Apply(alicePoints, bobPoints, (a: Int, b: Int) => a >= 21 || b >= 21)
    def bobWinning: Element[Boolean] =
      Apply(alicePoints, bobPoints, (a: Int, b: Int) => b > a)
  }
  case class InitialState() extends State {
    val alicePoints = Constant(0)
    val bobPoints = Constant(0)
  }
  case class NextState(current: State) extends State {
    val aliceWinsPoint = Flip(0.52)
    val alicePoints =
      Apply(current.alicePoints, aliceWinsPoint,
        (curr: Int, wins: Boolean) => if (wins) curr + 1 else curr)
    val bobPoints =
      Apply(current.bobPoints, aliceWinsPoint,
        (curr: Int, wins: Boolean) => if (!wins) curr + 1 else curr)
  }
  def playFrom(current: State): Element[Boolean] = {
    If(current.terminal, current.bobWinning, playFrom(NextState(current)))
  }
  val bobWins = playFrom(InitialState())
  println(Importance.probability(bobWins, true))
}
