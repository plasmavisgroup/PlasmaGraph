Carga un archivo
==================

Actores:
 + Usuario
 + PlasmaGraph
 + Operativo

1) PlasmaGraph le pide a Operativo que le de una lista con todos los archivos que el contiene.

2) Operativo le da a PlasmaGraph una lista con todos los archivos en el filesystem.

3) PlasmaGraph le ense�a a Usuario la lista de archivos que le dio Operativo y le pide que seleccione uno.

4) Usuario selecciona uno y se lo dice PlasmaGraph.

5) PlasmaGraph lee la data el el archivo seleccionado.


Crear una Grafica
==================

Actores:
 + Usuario
 + PlasmaGraph
 + Operativo
 + DB

1) Usuario le pide a PlasmaGraph que genere una grafica.

2) PlasmaGraph 'carga un archivo' y le pide a DB que le de todos los templates disponibles.

3) DB le da a PlasmaGraph una lista con los nombres de todos los templates.

5) PlasmaGraph le ense�a la lista a Usuario y le pide que seleccione un item de esa lista.

6) Usuario selecciona un item y se lo da a PlasmaGraph.

7) PlasmaGraph crea una grafica y se la ense�a a Usuario.


Guardar una Grafica
====================

Actores:
 + Usuario
 + PlasmaGraph
 + Operativo

1) User le dice a PlasmaGraph que guarde la grafica.

2) PlasmaGraph le pide a Operativo que le de la lista de todos los directorios disponibles para escribir.

3) PlasmaGraph le pide a Usuario que seleccione un directorio.

4) Usuario selecciona un directorio.

5) PlasmaGraph le dice a Operativo que guarde la grafica en el directorio indicado por Usuario.


Crear Template
==================

Actores:
 + Usuario
 + PlasmaGraph
 + DB

1) Usuario le pide a PlasmaGraph que cree un template.

2) PlasmaGraph le pide a Usuario los siguientes datos:
+ Nombre del template
+ Que columnas se van a graficar
+ Que tipo de grafica se va a usar (bar, pie, line)
+ Que etiquetas se van a usar

3) Usuario le da a PlasmaGraph los datos del template.

4) PlasmaGraph crea un nuevo template y se lo da a DB.

5) PlasmaGraph le dice a Usuario que se creo un nuevo template.


Modificar un Template
======================

Actores:
 + Usuario
 + PlasmaGraph
 + DB

1) Usuario le pide a PlasmaGraph que modifique un template.

2) PlasmaGraph le pide a DB una lista con los nombres de todos los templates.

3) DB le da a PlasmaGraph una lista con los nombres de todos los templates.

4) PlasmaGraph le pide a Usuario que seleccione un template de la lista.

5) Usuario selecciona un template.

6) PlasmaGraph le pide a DB el template que selecciono Usuario.

7) DB le da a PlasmaGraph el template que tiene el mismo nombre que selecciono Usuario.


8) PlasmaGraph le pide a Usuario que indique cuales son los datos del template que quiere cambiar:
+ Nombre del template
+ Que columnas se van a graficar
+ Que tipo de grafica se va a usar (bar, pie, line)
+ Que etiquetas se van a usar

9) Usuario le da a PlasmaGraph los nuevos valores para los datos del template.

10) PlasmaGraph actualiza los datos del template.

11) PlasmaGraph le da el template a DB.

12) DB guarda el template.

13) PlasmaGraph le dice a Usuario que el template se modifico.
