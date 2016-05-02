# enconding: utf-8
# language: es
@busqueda @vuelos @etapa_3
Característica: Búsqueda de vuelos.

@back @solo_ida
Escenario: Búsqueda por FSM de one way

  Dado un servicio de FSM al que se le piden vuelos
  * que realiza una búsqueda con los siguientes parámetros

  | country | departure | type | origin | destination | adults | children |
  | AR | en 1 semana | oneway | BUE | MIA | 1 | 0 |

  Cuando se convoca al servicio
  Entonces para el primer resultado del servicio
  * la cantidad de infantes debe ser igual a la de la búsqueda
  * la cantidad de adultos debe ser igual a la de la búsqueda

@back @ida_y_vuelta
Escenario: Búsqueda por FSM de ida y vuelta

  Dado un servicio de FSM al que se le piden vuelos
  * que realiza una búsqueda con los siguientes parámetros

  | country | departure | return | type | origin | destination | adults | children |
  | AR | en 1 semana | en 2 semana | oneway | BUE | MIA | 1 | 0 |

  Cuando se convoca al servicio
  Entonces para el primer resultado del servicio
  * la cantidad de infantes debe ser igual a la de la búsqueda
  * la cantidad de adultos debe ser igual a la de la búsqueda