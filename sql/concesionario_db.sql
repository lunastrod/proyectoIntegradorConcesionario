BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Cliente" (
	"id_cliente"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"metodo de pago"	varchar(100),
	CONSTRAINT "pk_id_cli" PRIMARY KEY("id_cliente" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Marca" (
	"nombre_marca"	varchar(50),
	CONSTRAINT "pk_id_marca" PRIMARY KEY("nombre_marca")
);
CREATE TABLE IF NOT EXISTS "Modelo" (
	"id_modelo"	INTEGER,
	"nombre_modelo"	varchar(80),
	"nombre_marca"	varchar(50),
	CONSTRAINT "pk_id" PRIMARY KEY("id_modelo" AUTOINCREMENT),
	CONSTRAINT "fk_name" FOREIGN KEY("nombre_marca") REFERENCES "Marca"("nombre_marca")
);
CREATE TABLE IF NOT EXISTS "Trabajador" (
	"id_trabajador"	INTEGER,
	"nombre_apellidos"	varchar(100),
	"es_admin"	int,
	"password_trabajador"	varchar(100),
	CONSTRAINT "pk_id_trabajo" PRIMARY KEY("id_trabajador" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Vehiculo" (
	"id_vehiculo"	INTEGER,
	"modelo"	int,
	"precio"	int,
	"id_trabajador"	int,
	CONSTRAINT "pk_id_car" PRIMARY KEY("id_vehiculo" AUTOINCREMENT),
	CONSTRAINT "fk_id_trabajo" FOREIGN KEY("id_trabajador") REFERENCES "Trabajador"("id_trabajador"),
	CONSTRAINT "fk_model" FOREIGN KEY("modelo") REFERENCES "Modelo"("id_modelo")
);
CREATE TABLE IF NOT EXISTS "Venta" (
	"id_venta"	INTEGER,
	"id_cliente"	int,
	"id_trabajador"	int,
	"id_vehiculo"	int,
	CONSTRAINT "pk_id_venta" PRIMARY KEY("id_venta" AUTOINCREMENT),
	CONSTRAINT "fk_id_cli" FOREIGN KEY("id_cliente") REFERENCES "Cliente"("id_cliente"),
	CONSTRAINT "fk_id_trabajo" FOREIGN KEY("id_trabajador") REFERENCES "Trabajador"("id_trabajador"),
	CONSTRAINT "fk_id_car" FOREIGN KEY("id_vehiculo") REFERENCES "Vehiculo"("id_vehiculo")
);
INSERT INTO "Cliente" VALUES (1,'John');
INSERT INTO "Marca" VALUES ('Lamborghini');
INSERT INTO "Modelo" VALUES (1,'Aventador','Lamborghini');
INSERT INTO "Trabajador" VALUES (1,'Pedro',NULL);
INSERT INTO "Vehiculo" VALUES (1,1,600000);
INSERT INTO "Venta" VALUES (1,1,1,1);
COMMIT;
