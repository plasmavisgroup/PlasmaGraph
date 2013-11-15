##Carga un archivo
###Actores:

    Usuario (principal)
    Operativo (secundario)

###Escenarios:
|	|Principal |	Alterno: (A1) |	Exepcion: (E5) "Error Cargando el archivo" |	Exepcion: (E6) "Data mala"
|--	|----------|---------------|--------------------------------------------|---------------------------|
|1 |	PlasmaGraph le pide a Usuario que le de la direccion de un archivo |	|||		
|2 	|Usuario le da a PlasmaGraph la direccion de un archivo| 			|||
|3 |	Operativo le pide a PlasmaGraph la direccion de un archivo| 	|||		
|4 |	PlasmaGraph le da a Operativo la direccion que le dio Usuario |		|||	
|5 |	Operativo le da a PlasmaGraph la data contenida en el archivo |	||	Operativo le da a PlasmaGraph un mensaje de error 	
|6 |	PlasmaGraph le dice a Usuario que el archivo fue cargado |	PlasmaGraph le dice a Usuario que errores en el archivo fueron arreglados antes de subirlo al sistema |	PlasmaGraph le dice a Usuario que ocurrio un error |	PlasmaGraph le dice a Usuario que la data contenida en el archivo no es valida

Crear una Grafica
Actores:

    Usuario (principal)
    DB (secundario)

Escenarios:
	Principal 	Exepcion: (E6) "El template seleccionado no existe" 	Exepcion: (E7) "Combinacion invalida de template vs data"
1 	Carga un archivo 		
2 	PlasmaGraph le pide a Usuario el nombre de un template. 		
3 	Usuario le da a PlasmaGraph el nombre de un template. 		
4 	DB le pide a PlasmaGraph el nombre de un template. 		
5 	PlasmaGraph le da a DB el nombre del template que le dio Usuario. 		
6 	DB le da a PlasmaGraph el template que coincide con el nombre que le dio PlasmaGraph. 	DB le dice a PlasmaGraph que el template indicado no existe. 	
7 	PlasmaGraph le dice a Usuario que la grafica fue creada. 	PlasmaGraph le dice a Usuario que el template que selecciono no existe. 	PlasmaGraph le dice a Usuario que la data no puede ser graficada con el template seleccionado
Guardar un Archivo
Actores:

    Operativo (principal)

Pre-condicion:

    PlasmaGraph tiene una direccion y un archivo

Escenarios:
	Principal 	Exepcion: (E3) "Direccion invalida 	Exepcion: (E5) "Error guardando archivo"
1 	Operativo le pide a PlasmaGraph una direccion. 		
2 	PlasmaGraph le da a Operativo una direccion. 		
3 	Operativo le pide a PlasmaGraph un archivo. 	Operativo le dice a PlasmaGraph que la direccion es invalida 	
4 	PlasmaGraph le da a Operativo una archivo. 		
5 	Operativo le dicce a PlasmaGraph que se guardo el archivo. 		Operativo le dicce a PlasmaGraph que hubo un problema guardando el archivo.
Guardar una Grafica
Actores:

    Usuario (principal)

Escenarios:
	Principal
1 	Crear una Grafica
2 	PlasmaGraph le pide a Usuario que le de una direccion.
3 	Usuario le da a PlasmaGraph una direccion.
4 	Guardar un Archivo.
5 	PlasmaGraph le dice a Usuario que se guardo la grafica.
Llenar un Template
Actores:

    Usuario (principal)

Escenarios:
	Principal
1 	PlasmaGraph le pide a Usuario que le de un nombre para el template.
2 	Usuario le da un nombre a PlasmaGraph.
3 	PlasmaGraph le pide a Usuario los numeros de las columnas que se van a graficar.
4 	Usuario le da a PlasmaGraph los numeros de las columnas que se van a graficar.
5 	PlasmaGraph le pide a Usuario el nombre de las columnas que se van a graficar.
6 	Usuario le da a PlasmaGraph los nombres de las columnas que se van a graficar.
7 	PlasmaGraph le pide a Usuario el nombre del tipo de grafica que se va a hacer (pie, chart, line)
8 	Usuario le da a PlasmaGraph el nombre del tipo de grafica que se va a usar.
Crear Template

Actores:

    Usuario (principal)
    DB (secundario)

Escenarios:
	Principal 	Exepcion: (E4) "No se guardo el template"
1 	Llenar un Template. 	
2 	DB le pide a PlasmaGraph que le de un template. 	
3 	PlasmaGraph le da un template a DB. 	
4 	DB le dice a PlasmaGraph que se guardo el template. 	DB le dice a PlasmaGraph que no se pudo guardar el template.
5 	PlasmaGraph le dice a Usuario que se creo el template. 	PlasmaGraph le dice a Usuario que no se creo el template.
Modificar un Template
Actores:

    Usuario (principal)
    DB (secundario)

Escenario principal:
	Principal 	Exepcion: (E5) "DB no puede regresar template" 	Exepcion: (E9) "El template no se modifico"
1 	PlasmaGraph le pide a Usuario que le de el nombrte de un template. 		
2 	Usuario le da a PlasmaGraph el nombre de un template. 		
3 	DB le pide a PlasmaGraph el nombre de un template. 		
4 	PlasmaGraph le da a DB el nombre del template que le dio Usuario. 		
5 	DB le da a PlasmaGraph el template que conincede con el nombre que le dio PlasmaGraph anteriormente. 	DB le dice a PlasmaGraph que no puede darle el template. 	
6 	Llenar un Template. 	PlasmaGraph le dice a Usuario que el template seleccionado no pudo ser extraido de la base de datos 	
7 	DB le pide un template a PlasmaGraph. 		
8 	PlasmaGraph le da a DB el template que acaba de llenar. 		
9 	DB le dice a PlasmaGraph que guardo el template. 		DB le dice a PlasmaGraph que no pudo guardar el template.
10 	PlasmaGraph le dice a Usuario que el template fue modificado. 		PlasmaGraph le dice a Usuario que el template no pudo ser modificado.
