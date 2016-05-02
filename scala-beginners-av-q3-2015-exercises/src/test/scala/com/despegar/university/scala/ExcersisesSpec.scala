package com.despegar.university.scala

import org.specs2.mutable.Specification

class ExcersisesSpec extends Specification {
  val seqExample = List(1,2,3,4,5,6)

  "map" should  {
    "work for seqExample" in {
      Excercises.map[Int, Int](_ + 1)(seqExample) === List(2, 3, 4, 5, 6, 7)
    }

    "work for empty list" in {
      Excercises.map[Int, Int](_ + 1)(Nil) === Nil
    }
  }

  "filter" should {
    "work for seqExample" in {
      Excercises.filter[Int](_ % 2 == 0)(seqExample) === List(2, 4, 6)
    }
    "work for empty list" in {
      Excercises.map[Int, Int](_ + 1)(Nil) === Nil
    }
  }

}
