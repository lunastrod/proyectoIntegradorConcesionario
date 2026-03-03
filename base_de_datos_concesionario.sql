DROP DATABASE IF EXISTS Concesionario;
CREATE DATABASE Concesionario;
USE Concesionario;

CREATE TABLE Cliente (
id_cliente INT PRIMARY KEY,
nombre_apellidos varchar(100)
);

CREATE TABLE Trabajador (
id_trabajador INT PRIMARY KEY,
nombre_apellidos varchar(100)
);

CREATE TABLE Marca (
nombre_marca varchar(50) PRIMARY KEY
);

CREATE TABLE Modelo (
id_modelo INT PRIMARY KEY,
nombre_modelo varchar(80),
nombre_marca varchar(50),
foreign key (nombre_marca) references Marca(nombre_marca)
);

CREATE TABLE Vehiculo (
id_vehiculo INT PRIMARY KEY,
modelo int,
foreign key (modelo) references Modelo(id_modelo),
precio int
);

CREATE TABLE Venta (
id_venta INT PRIMARY KEY,
id_cliente int,
foreign key (id_cliente) references Cliente(id_cliente),
id_trabajador int,
foreign key (id_trabajador) references Trabajador(id_trabajador),
id_vehiculo int,
foreign key (id_vehiculo) references Vehiculo(id_vehiculo)
);

INSERT INTO Cliente(id_cliente, nombre_apellidos) VALUES(1, "John");

INSERT INTO Trabajador(id_trabajador, nombre_apellidos) VALUES(1, "Pedro");

INSERT INTO Marca(nombre_marca) VALUES("Lamborghini");

INSERT INTO Modelo(id_modelo, nombre_modelo, nombre_marca) VALUES(1, "Aventador", "Lamborghini");

INSERT INTO Vehiculo(id_vehiculo, modelo, precio) VALUES(1, 1, 600000);

INSERT INTO Venta(id_venta, id_cliente, id_trabajador, id_vehiculo) VALUES (1, 1, 1, 1);