Al comienzo de la ejecucion segun el profiler llega a consumir hasta un 20% del CPU y alrededor de 300 MB de memoria. Esto es
porque decidimos ingresar las tuplas en un arbol ordenado por fecha para poder agilizar las funciones que requieran el uso de fechas.

Tambien a partir de estos datos creamos una lista de fechas y hashes de canciones, paises y artistas para poder acceder a ellos de manera mas rapida.

Creamos una clase Estadistica para implementar las funciones requeridas.

El tiempo de ejecucion promedio al iniciar el programa es de aproximadamente 25 segundos.

