# encoding: utf-8
# language: es

@ejemplotabla
Característica: Ejemplo de uso de tablas en Cucumber [ESQUEMA DE ESCENARIO].

Esquema del escenario: Esta es mi descripción del escenario a probar

Dado que cuento con dos numeros '<numero1>' y '<numero2>'
Cuando los sumo
Entonces el resultado es '<resultado>'

@tabla1
Ejemplos:
| numero1 | numero2 | resultado |
| 2 | 3 | 5 |
| 10 | 101 | 121 |
| 3 | 8 | 11 |
| 8 | 2 | 10 |

@tabla2
Ejemplos:
| numero1 | numero2 | resultado |
| 6 | 12 | 18 |

#cucumber -t @ejemplotabla -t @tabla1