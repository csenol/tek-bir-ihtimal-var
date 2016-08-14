package chapter6

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.language._
import com.cra.figaro.library.compound._

/**
  * Created by csenol on 8/14/16.
  */
object Exercise1 {

  val teacherSkill = Array.fill(6)(Flip(0.6))
  val studentAbility = Array.fill(6, 30)(Flip(0.7))
  val pass = Array.tabulate(6, 30)((cls: Int, stdnt: Int) =>
    CPD(teacherSkill(cls), studentAbility(cls)(stdnt),
      (true, true) -> Flip(0.9),
      (true, false) -> Flip(0.6),
      (false, true) -> Flip(0.7),
      (false, false) -> Flip(0.3)))
  for {
    cls <- 0 until 6
    stdnt <- 0 until 30
  } {
    ???
    //pass(cls)(stdnt).observe(data(cls)(stdnt) == 'P')
    //data not provided in PDF
  }
  val ve =
    VariableElimination(teacherSkill(0), teacherSkill(1), teacherSkill(2),
      teacherSkill(3), teacherSkill(4), teacherSkill(5))
  ve.start()
  for { cls <- 0 until 6 } {
    println("Teacher " + cls + ": " + ve.probability(teacherSkill(cls),
      true))
  }

}
