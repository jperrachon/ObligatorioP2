Al comienzo de la ejecucion segun el profiler llega a consumir hasta un 20% del CPU y alrededor de 300 MB de memoria. Esto es
porque decidimos ingresar las tuplas en un arbol ordenado por fecha para poder agilizar las funciones que requieran el uso de fechas.

Tambien a partir de estos datos creamos una lista de fechas y hashes de canciones, paises y artistas para poder acceder a ellos de manera mas rapida.

Creamos una clase Estadistica para implementar las funciones requeridas.

El tiempo de ejecucion promedio al iniciar el programa es de aproximadamente 21 segundos.
La primera funcion tiene un promedio de ejecucion de 33 milisegundos.
La segunda funcion tiene un promedio de ejecucion de 2,6 segundos.
El promedio de ejecucion de la tercera funcion depende del rango de fechas dado. Probamos con un rando de fechas de 5 meses y la ejecucion promedio es de 1.7 segundos
La cuarta consulta tiene un promedio de ejecucion de 2.4 segundos.
La quinta funcion tiene un promedio de ejecucion de 44 para 5 meses de rango.

Para elegir pais hay que escribir su numero de lista correspondiente y no el nombre del pais.