package com.despegar.university.scala

sealed trait LinkedList[+A] {

	def append[B >: A](elem: B): LinkedList[B] = this match {
		case list: Node[A] => Node(elem, list)
		case Empty => Node(elem, Empty)
	}

	def head: A = this match {
		case Node(elem, _) => elem
		case Empty => throw new NoSuchElementException
	}

	def headOpt: Option[A] = this match {
		case Node(elem, _) => Some(elem)
		case Empty => None
	}

	def tail: LinkedList[A] = this match {
		case Node(_, tail) => tail
		case Empty => Empty
	}

	def fold[B](accumulator: B)(f: (B, A) => B): B = {
		this match {
			case Node(head, tail) => {
				val current = f(accumulator, head)
				tail.fold(current)(f)
			}
			case Empty => accumulator
		}
	}

	def reverse() = this.fold(LinkedList[A]()) { (list, elem) =>
		Node(elem, list)
	}

	def foreach(f: A => Unit): Unit = {
		this.reverse().fold({}) { (_, elem) =>
			f(elem)
		}
	}

	//recibe una funcion de A en B y devuelve la lista resultante
	//de aplicar la funcion en cada elemento


	// PATTERN MATCHING VERSION
	//def map[B](func: A => B): LinkedList[B] = this match {
	//		case Node(head, tail) => Node(func(head), tail.map(func))
	//		case Empty => Empty
	//	}


	def map[B](func: A => B): LinkedList[B] = fold(LinkedList[B]()) { (accum, elem) => Node(func(elem), accum) }.reverse()


	// PATTERN MATCHING VERSION
	//recibe un predicado y devuelve la lista de elementos que lo cumplen
	//	def filter(predicate: A => Boolean): LinkedList[A] = this match{
	//		case Node(head, tail) => if(predicate(head)) {
	//			Node(head, tail.filter(predicate))
	//		} else {
	//			tail.filter(predicate)
	//		}
	//		case Empty => Empty
	//	}

	def filter(predicate: A => Boolean): LinkedList[A] =
		fold(LinkedList[A]()) { (accum, elem) => if (predicate(elem)) {
			Node(elem, accum)
		} else {
			accum
		}
		}.reverse()


	//PATTERN MATCHING VERSION
	//devuelve si el predicado es verdadero para todos los elementos de la lista
	//(si la lista es vacia devuelve true)
	//	def forall(predicate: A => Boolean): Boolean = this match {
	//		case Node(head, tail) => if(!predicate(head)){
	//			false
	//		} else {
	//			tail.forall(predicate)
	//		}
	//		case Empty => true
	//	}


	def forall(predicate: A => Boolean): Boolean = fold(true) { (accum, elem) => accum && predicate(elem) }


	//PATTERN MATCHING VERSION
	//devuelve si la lista contiene el elemento
	//	def contains(elem: Any): Boolean = this match {
	//		case Node(head, tail) => if(head == elem){ return true } else { tail.contains(elem)}
	//		case Empty => false
	//	}


	def contains(elem: Any): Boolean = fold(false) { (accum, element) => accum || (element == elem) }


	//PATTERN MATCHING VERSION
	//devuelve la cantidad de veces que se cumple el predicado
	//	def count(predicate: A => Boolean): Int = this match {
	//		case Node(head, tail) => if(predicate(head)){
	//			1 + tail.count(predicate)
	//		} else {
	//			tail.count(predicate)
	//		}
	//		case Empty => 0
	//	}


	def count(predicate: A => Boolean): Int = fold(0) { (accum, elem) => if (predicate(elem)) {
		accum + 1
	} else {
		accum
	}
	}


	//PATTERN MATCHING VERSION
	//devuelve la lista sin repetidos
	//	def distinct: LinkedList[A] = this match {
	//		case Node(head, tail) => Node(head, tail.filter(x => x != head).distinct)
	//		case Empty => Empty
	//	}

	def distinct: LinkedList[A] = fold(LinkedList[A]()) { (accum, elem) => if (accum contains elem) {
		accum
	} else {
		Node(elem, accum)
	}
	}.reverse()


	//PATTERN MATCHING VERSION
	//devuelve la lista resultante de descartar los primeros n elementos
	//si n > size devuelve la lista vacia
	//	def drop(n: Int): LinkedList[A] = this match {
	//		case Node(head, tail) => if(n > this.size) {
	//			Empty
	//		} else if (n == 0) {
	//			this
	//		} else {
	//			tail.drop(n - 1)
	//		}
	//		case Empty => Empty
	//	}

	def drop(n: Int): LinkedList[A] = fold(this) { (accum, elem) => if (accum.size == this.size - n) {
		accum
	} else {
		accum.tail
	}
	}


	//PATTERN MATCHING VERSION
	//devuelve los primeros n elementos
	//si n > size devuelve toda la lista
	//	def take(n: Int): LinkedList[A] = this match {
	//		case Node(head, tail) => if(n > this.size){
	//			this
	//		}	else if (n == 0){
	//			Empty
	//		} else {
	//			Node(head, tail.take(n - 1))
	//		}
	//		case Empty => Empty
	//	}

	def take(n: Int): LinkedList[A] = fold(LinkedList[A]()) { (accum, elem) => if (accum.size == n) {
		accum
	} else {
		accum.append(elem)
	}
	}.reverse


	//PATTERN MATCHING VERSION
	//devuelve la lista actual concatenada con other al final
	//	def ++[B >: A](other: LinkedList[B]): LinkedList[B] = this.reverse match {
	//		case Node(head, tail) => tail.reverse ++ other.append(head)
	//		case Empty => other
	//	}

	def ++[B >: A](other: LinkedList[B]): LinkedList[B] = this.reverse.fold(other)((accum, elem) => accum.append(elem))


	//PATTERN MATCHING VERSION
	//devuelve el tamaño de la lista
	//	val size: Int = this match {
	//		case Node(_, tail) => 1 + tail.size
	//		case Empty => 0
	//	}

	val size: Int = fold(0) { (accum, elem) => accum + 1 }


	//PATTERN MATCHING VERSION
	//devuelve un option del elemento en el indice idx
	//en caso de que idx > size devuelve None
	//	def apply(idx: Int): Option[A] = this match {
	//		case Node(head, tail) => if(idx >= this.size || idx < 0){
	//			None
	//		} else if(idx == 0) {
	//			Some(head)
	//		} else {
	//			tail(idx - 1)
	//		}
	//		case Empty => None
	//	}
	def apply(idx: Int): Option[A] = {
		if (idx >= size || idx < 0) {
			None
		} else {
			Some(this.drop(idx).head);
		}
	}

	//PATTERN MATCHING VERSION
	//devuelve el primer elemento para el cual se cumple el predicado
	//si nadie lo cumple devuelve None
	//	def find(predicate: A => Boolean): Option[A] = this match {
	//		case Node(head, tail) => if(predicate(head)){
	//			Some(head)
	//		}	else {
	//			tail.find(predicate)
	//		}
	//		case Empty => None
	//	}
	//}

	def find(predicate: A => Boolean): Option[A] = {
		val filtered = filter(predicate)
		if (filtered.size == 0) {
			None
		} else {
			Some(filtered.head)
		}

	}

}


object LinkedList {
	/*
	Cuando ponemos A* quiere decir que apply recibe una lista de parametros variable
	Por Ejemplo: 
	LinkedList(1,2,3,4,5,6)  
	LinkedList(1)
	LinkedList() 
	*/
	def apply[A](items : A*) : LinkedList[A] = {
	  if (items.isEmpty) {
	    Empty
	  } else {
	  	/* 
	  	Al hacer items.tail: _* estamos descomponiendo una secuencia en una lista de parametros
		Por Ejemplo:
		val list = List(1,2,3)
		LinkedList(list:_*) == LinkedList(1,2,3)
		LinkedList(list) == LinkedList(List(1,2,3))
	  	*/
	    Node(items.head, apply(items.tail: _*))
	  }
	}
}

case class Node[+A](override val head: A, override val tail: LinkedList[A]) extends LinkedList[A]

case object Empty extends LinkedList[Nothing]


