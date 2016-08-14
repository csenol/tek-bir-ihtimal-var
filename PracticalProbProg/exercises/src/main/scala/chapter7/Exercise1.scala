package chapter7

import com.cra.figaro.algorithm.sampling.{ProposalScheme, MetropolisHastings}
import com.cra.figaro.library.atomic.continuous.{Normal, Uniform}
import com.cra.figaro.library.collection.Container

/**
  * Created by csenol on 8/14/16.
  */
object Exercise1 {
  class Actor {
    val liked = Uniform(0, 1)
  }
  class Movie(actors: List[Actor]) {
    private val likedContainer = ???
    private val popularityMean = ???
    val popularity = ???
  }
  val actors = Array.fill(10)(new Actor)
  val movie1 = new Movie(List(actors(0), actors(1)))

  val movie2 = new Movie(List(actors(0), actors(2), actors(3)))

  val movie3 = new Movie(List(actors(1), actors(4), actors(5)))

  val movie4 = new Movie(List(actors(2), actors(4), actors(6)))

  val movie5 =
    new Movie(List(actors(5), actors(7), actors(8), actors(9)))

  val movie6 = new Movie(List(actors(6), actors(7), actors(8)))

  val movie7 = new Movie(List(actors(1), actors(4), actors(9)))


  // The evidence is provided as soft constraints. Hard conditions would
  // be extremely unlikely to be satisfied during sampling
  def constrain(movie: Movie, pop: Double) {
    ???
  }
  constrain(movie1, 0.4)
  constrain(movie2, 0.2)
  constrain(movie3, 0.7)
  constrain(movie4, 0.5)
  constrain(movie5, 0.8)
  constrain(movie6, 0.4)
//  val alg =
//    MetropolisHastings(100000, ProposalScheme.default, movie7.popularity)
//  alg.start()
//  println(alg.mean(movie7.popularity))
}
