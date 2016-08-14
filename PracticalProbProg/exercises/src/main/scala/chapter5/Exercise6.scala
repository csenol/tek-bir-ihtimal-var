package chapter5

import com.cra.figaro.language._
import com.cra.figaro.library.compound.If

/**
  * Created by csenol on 8/14/16.
  */
object Exercise6 {

  def tennis(
              probP1ServeWin: Double, probP1Winner: Double, probP1Error: Double,
              probP2ServeWin: Double, probP2Winner: Double, probP2Error: Double):
  Element[Boolean] = {
    def rally(firstShot: Boolean, player1: Boolean): Element[Boolean] = {
      ???
    }
    def game(
              p1Serves: Boolean, p1Points: Element[Int],
              p2Points: Element[Int]): Element[Boolean] = {
      ???
    }
    def play(
              p1Serves: Boolean, p1Sets: Element[Int], p2Sets: Element[Int],
              p1Games: Element[Int], p2Games: Element[Int]): Element[Boolean] = {
      val p1WinsGame = ???
      val newP1Games = ???
      //Solutions to selected exercises
      val newP2Games = ???
      val newP1Sets = ???
      val newP2Sets = ???

      val matchOver = ???
  }

}
