package com.despegar.university.scala

import org.specs2.mutable.Specification

class LinkedListSpec extends Specification {

  "linked list" should {

    "map correctly" in {
      val original = LinkedList(2, 3, 4)
      original.map(x => x * x) === LinkedList(4, 9, 16)
    }

    "map and fold to sum the squares" in {
      val original = LinkedList(2, 3, 4)
      original.map(x => x * x).fold(0)((acc, x) => acc + x) === 29
    }

    "filter a list" in {
      val original = LinkedList(1, 2, 3, 4, 5)
      original.filter(x => (x % 2) == 0) === LinkedList(2, 4)
    }

    "forall correctly" in {
      val items = LinkedList(2, 4, 6, 8)
      items.forall(_ % 2 == 0) === true
      items.forall(_ < 10) === true
      items.forall(_ < 5) === false
    }

    "contains John" in {
      val items = LinkedList("John", "Josh", "Mary")
      items.contains("John") === true
    }

    "not contains John" in {
      val items = LinkedList("Josh", "Mary")
      items.contains("John") === false
    }

    "count correctly" in {
      val items = LinkedList("John", "Josh", "Mary")
      items.count(_.startsWith("J")) == 2
      items.count(_.startsWith("M")) == 1
    }

    "distinct works" in {
      val items = LinkedList("John", "Josh", "Mary", "John")
      items.distinct === LinkedList("John", "Josh", "Mary")
    }

    "distinct works in empty list" in {
      Empty.distinct === Empty
    }

    "drop works correctly" in {
      val items = LinkedList(2, 4, 6, 8)
      items.drop(2) === LinkedList(6, 8)
    }

    "take works correctly" in {
      val items = LinkedList(2, 4, 6, 8)
      items.take(2) === LinkedList(2, 4)
    }

    "appending two lists" in {
      val current = LinkedList(1, 2, 3, 4)
      val other = LinkedList(10, 11, 12, 13)

      (other ++ current) === LinkedList(10, 11, 12, 13, 1, 2, 3, 4)
    }

    "appending with empty list at right" in {
      val items = LinkedList(1, 2, 3, 4)
      items ++ Empty === items
    }
    "appending with empty list at left" in {
      val items = LinkedList(1, 2, 3, 4)
      Empty ++ items === items
    }

    "count items in a list" in {
      LinkedList(1, 2, 4).size === 3
      LinkedList(1, 2).size === 2
      LinkedList(1, 2, 3, 4, 5, 6).size === 6
    }

    "apply return element" in {
      val items = LinkedList(1, 2, 4)
      items(0) === Some(1)
      items(1) === Some(2)
      items(2) === Some(4)
    }

    "apply not return element" in {
      val items = LinkedList(1, 2, 4)
      items(5) === None
      items(6) === None
    }

    "find John" in {
      val items = LinkedList("John", "Josh", "Mary")
      items.find(name => name == "John") === Some("John")
    }

    "not find John" in {
      val items = LinkedList("Josh", "Mary")
      items.find(name => name == "John") === None
    }

    "find with pattern matching" in {
      val items = LinkedList("Josh", "Mary")
      items.find(name => name == "Mary") match {
        case Some(item) => success
        case None => failure("Should not have come here")
      }
    }

    "wont find anything" in {
      val numbers = LinkedList(1, 2, 3, 4, 5)

      val result = for {one <- numbers.find(x => x == 1)
                        two <- numbers.find(x => x == 2)
                        moreThan4 <- numbers.find(x => x > 5)
      } yield one + two + moreThan4

      result === None
    }

  }
}
