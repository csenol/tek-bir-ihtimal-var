package chapter4


import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.language.Chain
import com.cra.figaro.library.compound.{RichCPD, OneOf, *}
import com.cra.figaro.language.{Flip, Constant, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination

object Exercise4 {
  def main(args: Array[String]) {
    // To keep the code simple, I just make the cards an integer
    val cards = List(5, 4, 3, 2, 1)
    // The discrete uniform distribution chooses uniformly from a fixed
    // set of possibilities
    val player1Card = ???
    val player2Card = ???
    val player1Bet1 = ???

    val player2Bet = ???

    val player1Bet2 = ???

    val player1Gain = ???

}