package com.despegar.university.scala

import org.joda.time.DateTime

object Excercises {

  def fold[A,B](acum: B)(funcAcum: (B, A) => B)(list: Seq[A]): B = {
    var acumVar = acum
    list.foreach { elem =>
        acumVar = funcAcum(acumVar, elem)
    }
    acumVar
  }

  /*
    Ej1:
    Implementar las funciones map y filter usando fold
  */

  def map[A,B](mapeador: A => B)(list: Seq[A]): Seq[B] = {
    fold[A, Seq[B]](acum = Seq[B]())((accum, elem) => accum :+ mapeador(elem))(list)
  }





  def filter[A](criterio: A => Boolean)(list: Seq[A]): Seq[A] = {
    fold[A, Seq[A]](acum = Seq[A]())((accum, elem) => if(criterio(elem)){ accum :+ elem} else {accum})(list)
  }

  /*
    Ej2:
      Necesitamos modelar los vuelos en despegar.
      La informacion que nos importa es el el horario de salida y el de llegada (recomendamos fuertemente que usen
      joda time), la ciudad de origen y la ciudad destino (donde una ciudad tiene un nombre y un pais), y finalmente
      los vuelos tienen una cantidad variable de escalas, las cuales son ciudades.
  */
  case class Vuelo(val horarioSalida: DateTime, val horarioLlegada: DateTime, val origen: Ciudad, val destino: Ciudad, val escalas: Seq[Ciudad])
  case class Ciudad(val nombre: String, val pais: String)


  /*
    Ej 3:
       Necesitamos que implementen las siguientes funciones que operan sobre Vuelos.
  */

  //Esta funcion toma una lista de vuelos y devuelve la lista con las ciudades de origen de cada uno
  def ciudadesDeOrigen(vuelos: Seq[Vuelo]): Seq[Ciudad] = {
    map[Vuelo, Ciudad]( flight => flight.origen )(vuelos)
  }

  //Esta funcion toma una liste de vuelos y devuelve la lista de ciudades por las que pasan, sin repetidos (incluyendo
  //las ciudades de origen y destino)
  def ciudadesPorLasQuePasan(vuelos: Seq[Vuelo]): Seq[Ciudad] = {
    fold[Vuelo, Seq[Ciudad]](Seq[Ciudad]())( (accum, vuelo) => accum ++ (vuelo.origen +: vuelo.escalas :+ vuelo.destino))(vuelos)
  }

  //Esta funcion toma una lista de vuelos y un entero entre 0 y 23 y devuelve la lista de vuelos atrasados esa cantidad
  //de horas
  def atrasarVuelos(vuelos: Seq[Vuelo], hours: Int): Seq[Vuelo] = {
    map[Vuelo, Vuelo](atrasarVuelo(_, hours))(vuelos)
  }

  def atrasarVuelo(vuelo: Vuelo, hours: Int): Vuelo = {
    val Vuelo(horarioSalida, horarioLlegada, origen, destino, escalas) = vuelo
    Vuelo(horarioSalida.plusHours(hours), horarioLlegada.plusHours(hours), origen, destino, escalas)
  }
  
  //Esta funcion toma una lista de vuelos y devuelve aquellos que no salen del pais (es decir ninguna de las ciudades
  //por las que pasa esta en un pais distinto del que sale y el destino es dentro del mismo pais)
  def vuelosDeCabotaje(vuelos: Seq[Vuelo]): Seq[Vuelo] = {
    filter(deCabotaje)(vuelos)
  }

  def deCabotaje(vuelo: Vuelo): Boolean = {
    map[Ciudad, String]( _.pais)(ciudadesPorLasQuePasan(Seq[Vuelo](vuelo))).size == 1
  }

  //Esta funcion toma dos listas de ciudades y calcula todos los posibles vuelos directos entre ellas
  // (no nos importan los horarios)
  def vuelosPosibles(ciudadesOrigen: Seq[Ciudad], ciudadesDestino: Seq[Ciudad]): Seq[Vuelo] = {
    fold[Ciudad, Seq[Vuelo]](Seq[Vuelo]())( (acum, ciudad) => acum ++ vuelosPosiblesDesdeCiudad(ciudad, ciudadesDestino))(ciudadesOrigen)
  }

  def vuelosPosiblesDesdeCiudad(ciudad: Ciudad, ciudadesDestino: Seq[Ciudad]) : Seq[Vuelo] = {
    fold[Ciudad, Seq[Vuelo]](Seq[Vuelo]())( (acum, ciudadDestino) => acum :+ Vuelo(new DateTime(2016,1,1), new DateTime(2016,1,1), ciudad, ciudadDestino, Seq[Ciudad]()) )(ciudadesDestino)
  }



}