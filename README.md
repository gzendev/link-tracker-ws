# LinkTracker

Software de rastreo desarrollado para Fiera, que permite enmascarar URL´s y obtener un análisis de la información obtenida. Las tecnologías usadas son: Spring Boot, PostgreSQL, JPA, y Hibernate.

## Requerimientos

1. Java - 1.8.x +

2. Maven - 3.x.x +

3. PostgreSQL 9.x +

## Pasos para Configurar

**1. Clonar la Aplicación

```bash
git clone https://github.com/gzendev/link-tracker-ws.git
```

**2. Crear PostgreSQL database

```bash
run create.sql
```

**3. Cambiar PostgreSQL username y password de acuerdo a tu instalación y entorno

+ open `src/main/resources/application-*.yml`

+ change `spring.datasource.username` , `spring.datasource.password` 

**4. Construir y correr la app usando Maven 

```bash
mvn package
java -jar target/link-tracker-ws.jar
```

Alternativamente, también podés correr la app usando,

```bash
mvn spring-boot:run
```

La app comenzará a correr en <http://localhost:8080>


## Explorar Rest APIs

La app define los siguientes EndPoints

	- POST /link/create
		  Ej. BODY: {"target": "https://www.fierastudio.com", "expiration": "08/10/2020"}
		 	   HEADER: token: 1234567890
		 	   
	- GET /l/Shortened-Code?token=xxxxxxxxx
			  Ej. http://localhost:8080/l/aBsJu?token=1234567890
			  
	- GET /statistic/Shortened-URL
			  Ej. /statistic/http://localhost:8080/l/aBsJu
			  
	- PUT /invalidate/Shortened-URL
			  Ej. /invalidate/http://localhost:8080/l/aBsJu
			  
## Comentarios
Por cuestiones de tiempo, no se ha agregado aspectos de seguridad en los endpoints ni en el manejo de token, tampoco pruebas unitarias.

En entornos productivos de alta demanda es posible implementar un balanceo de la carga de los request distribuidos en diferentes nodos de un cluster. De esta manera se obtiene un alto rendimiento, escalabilidad, y mayor disponibilidad en los servicios bajo un entorno empresarial.








