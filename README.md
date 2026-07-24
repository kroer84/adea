# adea
Examen Java
Teniendo la siguiente tabla:
USUARIO
COLUMN	PK	NULL?	TYPE	DEFAULT
LOGIN	YES	N	VARCHAR2 (20 Byte)	 
PASSWORD	 	N	VARCHAR2 (30 Byte)	 
NOMBRE	 	N	VARCHAR2 (50 Byte)	 
CLIENTE	 	N	FLOAT	 
EMAIL	 	Y	VARCHAR2 (50 Byte)	 
FECHAALTA	 	N	DATE	SYSDATE               
FECHABAJA	 	Y	DATE	 
STATUS	 	N	CHAR (1 Byte)	'A'                   
INTENTOS	 	N	FLOAT	0
FECHAREVOCADO	 	Y	DATE	 
FECHA_VIGENCIA	 	Y	DATE	 
NO_ACCESO	 	Y	INTEGER	 
APELLIDO_PATERNO	 	Y	VARCHAR2 (50 Byte)	 
APELLIDO_MATERNO	 	Y	VARCHAR2 (50 Byte)	 
AREA	 	Y	NUMBER (4)	 
FECHAMODIFICACION	 	N	DATE	SYSDATE 

Realizar las siguientes actividades:
1.- Login: 
1.	Validar que el usuario existe en la tabla usuario.
2.	Validar que la contraseña coincida con la registrada en usuario, este dato se guarda encriptado con SHA Base 64.
3.	Mostrar una página de Bienvenida y menú con 2 opciones:
a.	Gestión de Usuarios (2).
b.	Tablero de Usuarios (3).
4.	No permitir el acceso si la fecha de caducidad a vencido.
 2.- Gestión de Usuarios
Generar formulario para Altas, Bajas, Modificaciones de registros de usuario.
 
3.- Tablero y buscador de Usuarios.
Generar una vista de tablero por estatus de usuario y filtros de búsqueda similar a la siguiente imagen. 
 

•	Al dar clic en los recuadros el grid se debe actualizar con los registros referentes al estatus seleccionado.
o	Activos:       usuario.status = ‘A’
o	Inactivos:    usuario.status = ‘B’
o	Revocados: usuario.status = ‘R’
