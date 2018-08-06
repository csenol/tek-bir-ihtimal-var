package chapter7

import com.cra.figaro.algorithm.sampling.{ProposalScheme, MetropolisHastings}
import com.cra.figaro.language.{Constant, Apply}
import com.cra.figaro.library.atomic.continuous.Uniform

/**
  * Created by csenol on 8/14/16.
  */
object Exercise3 {
  class Student {
    val ability = Uniform(0, 1)
  }
  class Subject {
    val difficulty = Uniform(0, 1)
  }
  class Instructor {
    val quality = Uniform(0, 1)
  }
  case class Course(subject: Subject, instructor: Instructor)
  case class Enrollment(student: Student, course: Course) {
    val gradeMax =
      Apply(student.ability, course.instructor.quality,
        course.subject.difficulty,
        (ability: Double, quality: Double, difficulty: Double) =>
          (ability + quality - difficulty).max(0.0).min(1.0))
    val grade = Uniform(Constant(0.0), gradeMax)
  }
  val students = Array.fill(5)(new Student)
  val subjects = Array.fill(3)(new Subject)

  val instructors = Array.fill(2)(new Instructor)
  val course1 = Course(subjects(0), instructors(0))
  val course2 = Course(subjects(1), instructors(0))
  val course3 = Course(subjects(1), instructors(1))
  val course4 = Course(subjects(2), instructors(1))
  val enrollment1 = Enrollment(students(0), course1)
  val enrollment2 = Enrollment(students(0), course2)
  val enrollment3 = Enrollment(students(0), course3)
  val enrollment4 = Enrollment(students(1), course1)
  val enrollment5 = Enrollment(students(1), course2)
  val enrollment6 = Enrollment(students(2), course2)
  val enrollment7 = Enrollment(students(2), course3)
  val enrollment8 = Enrollment(students(2), course4)
  val enrollment9 = Enrollment(students(3), course1)
  val enrollment10 = Enrollment(students(3), course3)
  val enrollment11 = Enrollment(students(3), course4)
  val enrollment12 = Enrollment(students(4), course4)

  def constrain(enrollment: Enrollment, grd: Double) {
    enrollment.grade.addConstraint(d => math.exp(-(grd-d)*(grd-d)/0.01))
  }
  constrain(enrollment1, 0.3)
  constrain(enrollment2, 0.6)
  constrain(enrollment3, 0.4)
  constrain(enrollment4, 0.5)
  constrain(enrollment5, 0.8)
  constrain(enrollment6, 0.3)
  constrain(enrollment7, 0.2)
  constrain(enrollment8, 0.2)
  constrain(enrollment9, 0.7)
  constrain(enrollment10, 0.1)
  constrain(enrollment11, 0.2)
  constrain(enrollment12, 0.1)
  val alg =
    MetropolisHastings(100000, ProposalScheme.default,
      students(1).ability, subjects(2).difficulty, instructors(1).quality)
  alg.start()
  println("students(1).ability: " + alg.mean(students(1).ability))
  println("subjects(2).difficulty: " + alg.mean(subjects(2).difficulty))
  println("instructors(1).quality: " + alg.mean(instructors(1).quality))
}
