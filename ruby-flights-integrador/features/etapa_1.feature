# enconding: utf-8
# language: es

@gui @busqueda @vuelos @etapa_1
Característica: Búsqueda de vuelos.

Esquema del escenario: Búsqueda de tipo ida y vuelta
  Dado un usuario de '<pais>'
  * que ingresa a Despegar
  Cuando se dirige a vuelos
  Entonces la caja de búsqueda de la página
  * debe tener el título '<titulo>'
  * debe tener un campo para ingresar el origen con el placeholder '<origen>'
  * debe tener un campo para ingresar el destino con el placeholder '<destino>'
  * debe tener un campo para ingresar la fecha de partida con el placeholder '<partida>'
  * debe tener un campo para ingresar la fecha de retorno con el placeholder '<regreso>'
  * debe tener un checkbox de sólo ida
  * debe tener un select de adultos
  * debe tener un select de menores
  * debe tener un botón de búsqueda


@data
Ejemplos:
| pais | partida | regreso | origen | destino | titulo |
| AR | Partida | Regreso | Ingresa desde dónde viajas | Ingresa hacia dónde viajas | Vuelos |
| BR | Ida | Volta | Insira sua cidade de origem | Insira sua cidade de destino | Passagens Aéreas |