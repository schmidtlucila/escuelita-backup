# enconding: utf-8
# language: es

@gui @busqueda @vuelos @etapa_2_ida_y_vuelta @etapa_2
Característica: Búsqueda de vuelos.


@ida_y_vuelta @AR
Esquema del escenario: Búsqueda de tipo ida y vuelta para Argentina
  Dado un usuario de '<pais>'
  * que ingresa a Despegar
  * que ingresa a vuelos a realizar una búsqueda
  * con fecha de salida '<salida>'
  * con fecha de regreso '<regreso>'
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
| pais | salida | regreso | tipo | origen | destino | adultos | infantes |
| AR |  en 1 semana | en 3 semanas | roundtrip | Buenos Aires | Kuala Lumpur | 2 | 0|



@smoke @ida_y_vuelta @BR
Esquema del escenario: Búsqueda de tipo ida y vuelta para Brasil
  Dado un usuario de '<pais>'
  * que ingresa a Despegar
  * que ingresa a vuelos a realizar una búsqueda
  * con fecha de salida '<salida>'
  * con fecha de regreso '<regreso>'
  * de tipo <tipo>
  * con origen '<origen>'
  * con destino '<destino>'
  * para <adultos> adultos
  * para <infantes> infantes
  Cuando hace click en buscar
  Entonces para el primer resultado de la página
  * la tarifa debe ser no nula

@data
Ejemplos:
| pais | salida | regreso | tipo | origen | destino | adultos | infantes |
| BR | en 1 semana | en 3 semanas | roundtrip | Buenos Aires | Kuala Lumpur | 2 | 0|