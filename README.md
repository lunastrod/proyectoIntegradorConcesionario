# AutoGestión
 
Aplicación de escritorio para la gestión integral de un concesionario de vehículos, desarrollada en Java con Swing como proyecto integrador de 1º DAM.
 
---
 
## Descripción del proyecto
 
AutoGestión permite administrar el catálogo de vehículos y modelos de un concesionario, registrar ventas, gestionar clientes y controlar el acceso por roles (administrador y empleado). La interfaz gráfica está construida con **Java Swing** y soporta tema claro y oscuro mediante **FlatLaf**.
 
La arquitectura sigue el patrón **MVC**:
 
- **Modelo** — entidades de datos y DAOs con acceso a SQLite mediante JDBC.
- **Vista** — paneles Swing y ventana principal.
- **Controlador** — `ConcesionarioControlador`, que centraliza todos los eventos de la interfaz.
---
 
## Estructura del proyecto
 
```
AutoGestion/
└── src/
    ├── Inicio.java                        # Punto de entrada
    └── com/dam/
        ├── control/
        │   └── ConcesionarioControlador.java
        ├── model/
        │   ├── data/
        │   │   ├── Cliente.java
        │   │   ├── Login.java
        │   │   ├── ModeloVehiculo.java
        │   │   ├── Trabajador.java
        │   │   ├── Vehiculo.java
        │   │   └── Venta.java
        │   └── db/
        │       ├── AccesoBD.java
        │       ├── AccesoBDTest.java
        │       ├── ClienteDAO.java
        │       ├── ModeloVehiculoDAO.java
        │       ├── SetupBD.java
        │       ├── TrabajadorDAO.java
        │       ├── VehiculoDAO.java
        │       └── VentaDAO.java
        ├── view/
        │   ├── Avisos.java
        │   ├── IPanel.java
        │   ├── IVentana.java
        │   ├── PInformacionVehiculo.java
        │   ├── PLogin.java
        │   ├── PModificarModelo.java
        │   ├── PModificarVehiculo.java
        │   ├── PNuevoModelo.java
        │   ├── PNuevoVehiculo.java
        │   ├── PRegistrarTrabajador.java
        │   ├── PVerCatalogo.java
        │   ├── PVerVentasClientes.java
        │   ├── PVehiculo.java
        │   └── VPrincipal.java
        └── test/
            ├── ConcesionarioDBTest.java
            └── ConcesionarioFuncionalTest.java
DB/
├── ConfiguracionDB.properties             # URL y driver JDBC
└── concesionario.db                       # Base de datos SQLite
```
 
---
 
## Requisitos e instalación
 
### Requisitos
 
| Herramienta | Versión mínima |
|---|---|
| Java JDK | 11 |
| SQLite JDBC | 3.x |
| FlatLaf | 3.x |
| JUnit | 4.x |
 
### Instalación
 
1. Clona o descarga el repositorio.
2. Añade las dependencias al classpath del proyecto:
   - `sqlite-jdbc-*.jar`
   - `flatlaf-*.jar`
   - `junit-*.jar` (solo para tests)
3. Comprueba que el fichero `DB/ConfiguracionDB.properties` existe y contiene:
```properties
DRIVER=org.sqlite.JDBC
URL=jdbc:sqlite:DB/concesionario.db
```
 
4. Si necesitas regenerar la base de datos desde cero, cambia en `Inicio.java`:
```java
public static boolean REGENERAR_BD = true;
```
 
Vuelve a ponerlo a `false` tras el primer arranque para no perder los datos.
 
5. Ejecuta `Inicio.java` como aplicación Java.
---
 
## Guía de uso
 
### Acceso público
 
Al arrancar, la aplicación muestra el **catálogo de vehículos disponibles**. Desde el menú superior están accesibles:
 
- **Ver Catálogo** — muestra las tarjetas de vehículos con color y precio.
- **Más información** — despliega las especificaciones técnicas del vehículo y permite iniciar una compra.
- **Login trabajadores** — formulario de autenticación.
- **Modo claro-oscuro** — alterna el tema visual.
### Acceso como empleado
 
Credenciales de prueba: `Empleado` / `empleado`
 
Añade al menú:
 
- **Administrar Vehículos** — alta, modificación y baja de vehículos del catálogo. El formulario se habilita al seleccionar marca. La matrícula debe seguir el formato `0000 AAA`.
- **Administrar Modelos** — alta, modificación y baja de modelos. No se puede eliminar un modelo con vehículos asociados.
- **Ver ventas y clientes** — tablas con el histórico completo de ventas y el registro de clientes.
### Acceso como administrador
 
Credenciales de prueba: `Daniel` / `admin`
 
Incluye todo lo anterior más:
 
- **Administrar Trabajadores** — alta y baja de empleados. La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un símbolo.
### Proceso de compra
 
1. Desde el catálogo, pulsa **Más información** en el vehículo deseado.
2. Pulsa **Comprar** para desplegar el formulario.
3. Introduce el nombre del cliente, selecciona el trabajador que atiende y el método de pago.
4. Pulsa **Realizar Compra**. El vehículo desaparece del catálogo al quedar vendido.
---
 
## Base de datos
 
La base de datos es **SQLite** y se gestiona íntegramente mediante JDBC sin ningún ORM.
 
### Esquema
 
```
Cliente        (id_cliente PK, nombre_apellidos_c, metodo_pago)
Modelo         (id_modelo PK, nombre_modelo, numero_plazas, numero_puertas,
                tipo_vehiculo, tipo_propulsion, traccion, marca, tipo_transmision)
Trabajador     (id_trabajador PK, nombre_apellidos_t, password_trabajador, es_admin)
Vehiculo       (id_vehiculo PK, modelo FK→Modelo, precio, matricula UNIQUE,
                color, year, kilometraje, potencia_cv, cilindrada, peso_kg)
Venta          (id_venta PK, id_cliente FK, id_trabajador FK, id_vehiculo FK,
                fecha DATETIME DEFAULT CURRENT_TIMESTAMP)
```
 
### Integridad referencial
 
Las claves foráneas están activadas con `PRAGMA foreign_keys = ON` en cada conexión. Intentar eliminar un modelo con vehículos asociados, o un vehículo/trabajador con ventas asociadas, devuelve error y no modifica la base de datos.
 
### Datos de prueba
 
El script en `SetupBD.java` genera el esquema completo e inserta:
 
- 15 modelos de distintas marcas y categorías.
- 15 vehículos con matrículas y colores únicos.
- 3 trabajadores (2 administradores, 1 empleado).
- 2 clientes de ejemplo.
---
 
## Tests
 
El proyecto incluye dos clases de test bajo `com.dam.test`:
 
### `ConcesionarioDBTest`
 
Tests de integración sobre todos los DAOs usando una base de datos SQLite temporal (`DB/concesionarioPrueba.db`) que se recrea antes de cada test mediante `@Before`.
 
Cubre:
 
- CRUD completo de `ModeloVehiculoDAO`, `VehiculoDAO`, `TrabajadorDAO`, `ClienteDAO` y `VentaDAO`.
- Restricciones de clave foránea (eliminar con registros dependientes).
- Consistencia de conteos tras inserciones, actualizaciones y borrados.
- Disponibilidad de vehículos tras registrar una venta.
### `ConcesionarioFuncionalTest`
 
Tests unitarios sobre la lógica de validación de las entidades del modelo:
 
- `Vehiculo.matriculaValida()` — formato `0000 AAA`, casos válidos, inválidos, bordes y nulos.
- `Vehiculo.getColorParsed()` — parsing de color hexadecimal, colores inválidos y nulos.
- `Trabajador.contrasenaValida()` — requisitos de seguridad (longitud, mayúscula, minúscula, número, símbolo).
### Ejecución
 
Con Eclipse o IntelliJ: botón derecho sobre la clase de test → *Run As → JUnit Test*.
 
---
 
## Autores
 
| Nombre | Rol |
|---|---|
| Luis Daniel López Milicia | Desarrollador |
| Daniel Parra Segovia | Desarrollador |
| Hugo García Salazar | Desarrollador |
 
Proyecto integrador — 1º DAM
