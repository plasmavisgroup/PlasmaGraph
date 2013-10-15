Carga un archivo
==================

Actores:
 + Usuario (principal)
 + Operativo (secundario)

1. PlasmaGraph le pide a Usuario que le de la direccion de un archivo.
2. Usuario le da a PlasmaGraph la direccion de un archivo.
4. Operativo le pide a PlasmaGraph la direccion de un archivo.
5. PlasmaGraph le da a Operativo la direccion que le dio Usuario.
6. Operativo le da a PlasmaGraph la data contenida en el archivo.

Crear una Grafica
==================

Actores:
 + Usuario (principal)
 + DB (secundario)

1. PlasmaGraph 'carga un archivo' y le pide a DB que le de todos los templates.
2. DB le da todos los templates a PlasmaGraph.
3. PlasmaGraph le pide a Usuario que le de el nombre del template que quiere usar.
4. Usuario le da el nombre de un template a PlasmaGraph.
5. PlasmaGraph crea una grafica.


Guardar una Grafica
====================

Actores:
 + Usuario (principal)
 + Operativo (secundario)

Condicion:
 + PlasmaGraph pudo 'crear una grafica'

1. PlasmaGraph le pide a Usuario que le de una direccion en el file system.
2. Usuario le da a PlasmaGraph una direccion del file system.
3. Operativo le pide a PlasmaGraph una grafica y una direccion en el file system.
4. PlasmaGraph le da a Operativo la grafica que resulto al 'crear una grafica' y la direccion del file system que le dio Usuario.
5. Operativo guarda la grafica que le dio PlasmaGraph en la direccion del file system que le dio PlasmaGraph.


Crear Template
==================

Actores:
 + Usuario (principal)
 + DB (secundario)

1. PlasmaGraph le pide a Usuario los datos del template. Estos datos son:
 + Nombre del template
 + Que columnas se van a graficar
 + Que tipo de grafica se va a usar (bar, pie, line)
 + Que etiquetas se van a usar
2. Usuario le da a PlasmaGraph los datos del template.
3. PlasmaGraph crea un template con los datos que le dio Usuario.
4. DB le pide a PlasmaGraph que le de un template.
5. PlasmaGraph le da el template que creo a DB.


Modificar un Template
======================

Actores:
 + Usuario (principal)
 + DB (secundario)

1. PlasmaGraph le pide a Usuario que le de el nombrte de un template.
2. Usuario le da a PlasmaGraph el nombre de un template.
4. DB le pide a PlasmaGraph el nombre de un template.
5. PlasmaGraph le da a DB el nombre del template que le dio Usuario.
7. PlasmaGraph le pide a DB un template.
8. DB le da a PlasmaGraph el template que conincede con el nombre que le dio PlasmaGraph anteriormente.
10. PlasmaGraph le pide a Usuario que le de los nuevos datos del template.
 + Nombre del template
 + Que columnas se van a graficar
 + Que tipo de grafica se va a usar (bar, pie, line)
 + Que etiquetas se van a usar
11. Usuario le da a PlasmaGraph los nuevos datos del template.
 + Nombre del template
 + Que columnas se van a graficar
 + Que tipo de grafica se va a usar (bar, pie, line)
 + Que etiquetas se van a usar
9. PlasmaGraph sustitulle los los datos del template con los datos nuevos que le dio Usuario.
10. DB le pide un template a PlasmaGraph.
13. PlasmaGraph le da a DB el template con los datos modificados.
14. DB guarda el template.
