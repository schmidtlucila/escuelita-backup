# enconding: utf-8
# language: es

@compra @vuelos @etapa_4
Característica: Compra de vuelos

@gui @checkout
Esquema del escenario: Validación de checkout

  Dado un usuario de '<country>'
  * que ingresa a Despegar
  * que ingresa a vuelos y realiza una búsqueda con los siguientes parámetros

  | departure | type | origin | destination | adults | children |
  | en 1 semana | oneway | Buenos Aires | Kuala Lumpur | 2 | 3 |

  Cuando selecciona el primer resultado y hace click en el botón de comprar
  Entonces en la página de checkout tenemos que
  * la cantidad de secciones para infantes debe ser igual a la del detalle de la compra
  * la cantidad de secciones para adultos debe ser igual a la del detalle de la compra
  * todas las secciones de pasajeros deben tener un campo para nombre y otro para apellido

  @data
  Ejemplos:
  |country|
  |AR|
  |BR|