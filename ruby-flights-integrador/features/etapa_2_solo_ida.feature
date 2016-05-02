# enconding: utf-8
# language: es
@gui @busqueda @vuelos @etapa_2_solo_ida @etapa_2
Característica: Búsqueda de vuelos.

@solo_ida @AR
Esquema del escenario: Búsqueda de tipo solo ida para Argentina
  Dado un usuario de '<pais>'
  * que ingresa a Despegar
  * que ingresa a vuelos a realizar una búsqueda
  * con fecha de salida '<salida>'
  * de tipo <tipo>
  * con origen '<origen>'
  * con destino '<destino>'
  * para <infantes> infantes
  * para <adultos> adultos
  Cuando hace click en buscar
  Entonces para el primer resultado de la página
  * la tarifa debe ser no nula
  * la cantidad de infantes debe ser igual a la de la búsqueda
  * la cantidad de adultos debe ser igual a la de la búsqueda
  * la suma de los precios e impuestos debe ser igual al total
  
@data
Ejemplos:
| pais | salida | tipo | origen | destino | adultos | infantes |
| AR | en 5 semanas | oneway | Buenos Aires | Kuala Lumpur | 3 | 2 |



@solo_ida @BR
Esquema del escenario: Búsqueda solo ida para Brasil
  Dado un usuario de '<pais>'
  * que ingresa a Despegar
  * que ingresa a vuelos a realizar una búsqueda
  * con fecha de salida '<salida>'
  * de tipo <tipo>
  * con origen '<origen>'
  * con destino '<destino>'
  * para <infantes> infantes
  * para <adultos> adultos
  Cuando hace click en buscar
  Entonces para el primer resultado de la página
  * la tarifa debe ser no nula


@data
Ejemplos:
| pais | salida | tipo | origen | destino | adultos | infantes |
| BR | en 5 semanas  | oneway | Buenos Aires | Kuala Lumpur | 3 | 2 |