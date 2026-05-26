BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Cliente" (
	"id_cliente"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"metodo_pago"	varchar(50),
	CONSTRAINT "pk_id_cli" PRIMARY KEY("id_cliente" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Modelo" (
	"id_modelo"	INTEGER,
	"nombre_modelo"	varchar(80),
	"numero_plazas"	INTEGER,
	"numero_puertas"	INTEGER,
	"tipo_vehiculo"	varchar(100),
	"tipo_propulsion"	varchar(100),
	"traccion"	varchar(20),
	"marca"	varchar(70),
	"tipo_transmision"	varchar(30),
	CONSTRAINT "pk_id" PRIMARY KEY("id_modelo" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Trabajador" (
	"id_trabajador"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"password_trabajador"	varchar(100),
	CONSTRAINT "pk_id_trabajo" PRIMARY KEY("id_trabajador" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Vehiculo" (
	"id_vehiculo"	INTEGER,
	"modelo"	INTEGER,
	"precio"	INTEGER,
	"matricula"	varchar(40) UNIQUE,
	"color"	varchar(40),
	"year"	INTEGER,
	"kilometraje"	INTEGER,
	"potencia_cv"	INTEGER,
	"cilindrada"	INTEGER,
	"peso_kg"	INTEGER,
	CONSTRAINT "pk_id_car" PRIMARY KEY("id_vehiculo" AUTOINCREMENT),
	CONSTRAINT "fk_model" FOREIGN KEY("modelo") REFERENCES "Modelo"("id_modelo")
);
CREATE TABLE IF NOT EXISTS "Venta" (
	"id_venta"	INTEGER,
	"id_cliente"	INTEGER,
	"id_trabajador"	INTEGER,
	"id_vehiculo"	INTEGER,
	CONSTRAINT "pk_id_venta" PRIMARY KEY("id_venta" AUTOINCREMENT),
	CONSTRAINT "fk_id_cli" FOREIGN KEY("id_cliente") REFERENCES "Cliente"("id_cliente"),
	CONSTRAINT "fk_id_trabajo" FOREIGN KEY("id_trabajador") REFERENCES "Trabajador"("id_trabajador"),
	CONSTRAINT "fk_id_car" FOREIGN KEY("id_vehiculo") REFERENCES "Vehiculo"("id_vehiculo")
);
INSERT INTO "Cliente" VALUES (1,'John',NULL);
INSERT INTO "Modelo" VALUES (1,'Aventador',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "Trabajador" VALUES (1,'Pedro',NULL);
INSERT INTO "Trabajador" VALUES (2,'admin','admin');
INSERT INTO "Trabajador" VALUES (3,'admin','admin');
INSERT INTO "Trabajador" VALUES (4,'admin','admin');
INSERT INTO "Trabajador" VALUES (5,'admin','admin');
INSERT INTO "Vehiculo" VALUES (1,1,600000,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "Venta" VALUES (1,1,1,1);
COMMIT;
