package freestyle

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.factored.beliefpropagation.BeliefPropagation
import com.cra.figaro.language.{Dist, Apply, Element, Select}
import com.cra.figaro.library.atomic.discrete.{AtomicUniform, Uniform}
import com.cra.figaro.library.compound.If

/**
  * Created by csenol on 6/26/16.
  */
object MontyHall extends App {

  def doors(nums: Int) = {
   val l = 1 until nums+1
    Uniform(l:_*)
  }
  def remainingDoor(nums: Int)(a: Int, b: Int) = {
    val lst = (1 until nums+1) diff List(a,b)
    Uniform(lst:_*)
  }

  def monthy1(nums: Int) = for {
    firstChoice <- doors(nums)
    priceDoor <- doors(nums)
    montyOpens <- remainingDoor(nums)(firstChoice, priceDoor)
    secondChoice <- remainingDoor(nums)(firstChoice, montyOpens)
  } yield (firstChoice == priceDoor, secondChoice == priceDoor)

  val monthy = monthy1(10)


  println(BeliefPropagation.probability(monthy, (x:(Boolean, Boolean)) => x._1 ))

  println(BeliefPropagation.probability(monthy, (x:(Boolean, Boolean)) => x._2 ))





}
