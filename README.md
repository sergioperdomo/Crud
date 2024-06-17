# Crud

### Requisitos previos para una configuración óptima

Para asegurar el correcto funcionamiento de este proyecto, se recomienda seguir los siguientes pasos:

1. Clonación del repositorio:

La clonación del repositorio se puede realizar sin ningún inconveniente.

2. Creación de la base de datos:

Es necesario crear una base de datos con el nombre learning y dentro de ella, una tabla con el nombre estudiante.

Nota: La creación de la base de datos y la tabla estudiante puede variar según el sistema de gestión de bases de datos que se esté utilizando. Consulte la documentación específica para su caso.

Ejemplo:

En MySQL, la creación de la base de datos y la tabla se puede realizar mediante los siguientes comandos:

CREATE DATABASE learning;
USE learning;
CREATE TABLE estudiante (
	id_estudiante INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
	apellido VARCHAR(255) NOT NULL,
    edad INT NOT NULL,
    telefono VARCHAR(255) NOT NULL,
	direccion VARCHAR(255) NOT NULL UNIQUE
);
