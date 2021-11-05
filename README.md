# automata_ej1_TC
Ejercicio de TP Teoría de la computación 2021 UNGS

1. (5 ptos.) Sea la siguiente definici´on de un ε-AFND, que debe leerse de un archivo:
<s´ımboloInput>, <s´ımboloInput>, ..., <s´ımboloInput>
<cantEstados>
<estadoFinal>, <estadoFinal>, ..., <estadoFinal>
<estado>, <s´ımboloInput> -> <estado>
<estado>, <s´ımboloInput> -> <estado>
La primera l´ınea lista los elementos del alfabeto de input. La segunda indica la cantidad de estados,
la tercera el conjunto de estados finales, y el resto de las l´ıneas la funci´on de transici´on (los espacios
extra en el archivo deben ignorarse). El caracter E lo reservamos para una transici´on ε. Ejemplo:
a, b, c, d
10
1, 2, 3
1, a -> 3
1, a -> 4
1, a -> 5
1, d -> 4
4, E -> 4
Asumimos que el estado 1 siempre ser´a el inicial, y que los estados est´an numerados correlativamente de 1 a la cantidad de estados. Se pide
a) Transformar el ε-AFND en AFD.
b) Dar un m´etodo procesar(String w) del AFD, que tome un string compuesto de elementos
del alfabeto, y devuelva verdadero o falso seg´un si el string pertenece o no al lenguaje.

