# Como realizar los tests de vista
## Modo claro oscuro
hacer click en el botón del menú principal para alternar entre el modo claro y oscuro

## Cerrar
hacer click en el botón cerrar de la ventana principal
Mensaje de confirmación

## Ver catalogo
debería arrancar con el catálogo abierto
Deben salir todos los vehículos a la venta, con los colores correspondientes

## Mas info
Al hacer click en cada botón de más información debería salir un panel con la información detallada del vehículo correspondiente

## Comprar
Al hacer click en el botón de comprar del panel de más info debería aparecer el formulario de compra para introducir los datos
### datos incorrectos:
- Nombre de cliente vacio
mensaje de error
### datos correctos:
- Nombre de cliente introducido correctamente
mensaje de información

## Volver al catálogo
Al hacer click en el botón de volver al catálogo del panel de más info debería volver al catálogo

## Login
Al hacer click en el botón de login debería salir el formulario de login
### datos incorrectos:
Empleado o password vacíos o incorrectos: 
mensaje de error
### datos correctos:
- Empleado y password introducido correctamente
mensaje de información
aparecen nuevas opciones en el menú

Un ejemplo de login correcto de empleado es:
- usuario:    Empleado
- contraseña: empleado

empleado debería ver:
- administrar vehículos
- administrar modelos
- ver ventas y clientes

además de login y catalogo

Un ejemplo de login correcto de admin es:
- usuario:    Daniel
- contraseña: admin
admin debería ver:
- administrar trabajadores

además de todas las demás opciones

## Ver ventas y clientes
Aparecen 2 tablas con todas las ventas y los clientes.

## Administrar modelos
Aparece un formulario para registrar un nuevo modelo. Al introducir los datos, el nuevo modelo queda registrado.

Aparece una lista con todos los modelos

Aparecen 2 botones, para eliminar o modificar un modelo seleccionado en la lista
al hacer click en modificar aparece un formulario nuevo que modifica el modelo seleccionado

al hacer click en eliminar aparece un mensaje de confirmación
si el modelo tiene vehículos asociados no se puede eliminar y aparece un mensaje de error

## Administrar vehículos
Aparece un formulario para registrar un nuevo vehículo. Está deshabilitado hasta que se selecciona una marca.
Cuando se hace click en buscar marca se habilita el formulario para elegir el modelo.
La matrícula debe tener un formato especifico.
Al introducir los datos, el nuevo vehículo queda registrado.
Se puede hacer click en el botón de ver color para ver el color seleccionado.

Aparece una lista con todos los vehículos

Aparecen 2 botones, para eliminar o modificar un vehículo seleccionado en la lista
al hacer click en modificar aparece un formulario nuevo que modifica el vehículo seleccionado

al hacer click en eliminar aparece un mensaje de confirmación
si el vehículo tiene ventas asociadas no se puede eliminar y aparece un mensaje de error

## Administrar trabajadores
Aparece un formulario para registrar un nuevo trabajador. Al introducir los datos, el nuevo trabajador queda registrado.

Se comprueba que el nombre y contraseña no esté vacío.
Al introducir los datos, el nuevo trabajador queda registrado.

Aparece una tabla con todos los trabajadores

Aparece un botón para eliminar un trabajador seleccionado en la tabla

# Guía de Pruebas de la Interfaz de Usuario

## 1. Modo Claro / Oscuro
* **Acción:** Hacer clic en el botón del menú principal para alternar entre el modo claro y el modo oscuro.
* **Resultado esperado:** La interfaz cambia visualmente de tema de forma inmediata.

## 2. Cerrar Aplicación
* **Acción:** Hacer clic en el botón "Cerrar" de la ventana principal.
* **Resultado esperado:** Aparece un mensaje emergente de confirmación antes de salir del programa.

## 3. Ver Catálogo
* **Comportamiento inicial:** La aplicación debe arrancar con el catálogo abierto por defecto.
* **Resultado esperado:** Deben mostrarse todos los vehículos a la venta con sus colores correspondientes.

## 4. Más Información
* **Acción:** Hacer clic en el botón "Más información" de cualquier vehículo del catálogo.
* **Resultado esperado:** Se despliega un panel lateral o ventana con la información detallada del vehículo seleccionado.

## 5. Proceso de Compra
* **Acción:** Dentro del panel de "Más información", hacer clic en el botón "Comprar" para abrir el formulario de introducción de datos.

### 5.1. Datos Incorrectos
* **Escenario:** El campo "Nombre de cliente" se deja vacío.
* **Resultado esperado:** Se muestra un mensaje de error.

### 5.2. Datos Correctos
* **Escenario:** Se introduce el "Nombre de cliente" correctamente.
* **Resultado esperado:** Se muestra un mensaje de información confirmando la acción.

## 6. Volver al Catálogo
* **Acción:** Dentro del panel de "Más información", hacer clic en el botón "Volver al catálogo".
* **Resultado esperado:** La vista actual se cierra y el usuario regresa a la pantalla del catálogo general.

## 7. Autenticación (Login)
* **Acción:** Hacer clic en el botón de "Login" del menú para abrir el formulario de acceso.

### 7.1. Datos Incorrectos
* **Escenario:** Los campos de empleado o contraseña están vacíos, o las credenciales son erróneas.
* **Resultado esperado:** Se muestra un mensaje de error indicando el fallo.

### 7.2. Datos Correctos (Rol: Empleado)
* **Credenciales de prueba:**
  * **Usuario:** `Empleado`
  * **Contraseña:** `empleado`
* **Resultado esperado:** * Se muestra un mensaje de información confirmando el acceso.
  * Aparecen nuevas opciones en el menú principal. El empleado debe tener acceso exclusivo a:
    * Administrar vehículos
    * Administrar modelos
    * Ver ventas y clientes
    * *(Además de las opciones públicas: Login y Catálogo)*

### 7.3. Datos Correctos (Rol: Administrador)
* **Credenciales de prueba:**
  * **Usuario:** `Daniel`
  * **Contraseña:** `admin`
* **Resultado esperado:** * Se muestra un mensaje de información confirmando el acceso.
  * El administrador debe tener acceso a **todas** las opciones del empleado y, adicionalmente, a la opción exclusiva de:
    * Administrar trabajadores

## 8. Ver Ventas y Clientes
* **Requisito:** Acceso exclusivo para Empleados y Administradores.
* **Resultado esperado:** Se muestran 2 tablas detalladas que contienen el histórico completo de las ventas y el registro de los clientes.

## 9. Administrar Modelos
* **Requisito:** Acceso exclusivo para Empleados y Administradores.

### 9.1. Registro de Modelos
* **Acción:** Introducir los datos en el formulario de registro de nuevo modelo.
* **Resultado esperado:** El nuevo modelo queda guardado y registrado en el sistema.

### 9.2. Visualización y Gestión
* Se muestra una lista interactiva con todos los modelos registrados en el sistema.
* **Modificar:** Al seleccionar un modelo de la lista y hacer clic en el botón "Modificar", se abre un nuevo formulario que permite editar los datos del modelo seleccionado.
* **Eliminar:** Al seleccionar un modelo de la lista y hacer clic en el botón "Eliminar":
  * Aparece un mensaje de confirmación previa.
  * *Restricción:* Si el modelo seleccionado tiene vehículos asociados en la base de datos, la eliminación se cancela y se muestra un mensaje de error.

## 10. Administrar Vehículos
* **Requisito:** Acceso exclusivo para Empleados y Administradores.

### 10.1. Registro de Vehículos
* **Comportamiento inicial:** El formulario de registro aparece completamente deshabilitado por defecto hasta que se seleccione una marca.
* **Flujo de activación:** Al hacer clic en el botón "Buscar marca", se habilita el resto del formulario para permitir la selección del modelo.
* **Validación:** El campo de la matrícula debe cumplir estrictamente con el formato específico requerido.
* **Visualización de color:** Se incluye un botón para visualizar interactivamente el color que se ha seleccionado para el vehículo.
* **Resultado esperado:** Al completar y enviar los datos válidos, el vehículo queda registrado.

### 10.2. Visualización y Gestión
* Se muestra una lista interactiva con todos los vehículos registrados en el sistema.
* **Modificar:** Al seleccionar un vehículo de la lista y hacer clic en el botón "Modificar", se abre un nuevo formulario que permite editar los datos del vehículo seleccionado.
* **Eliminar:** Al seleccionar un vehículo de la lista y hacer clic en el botón "Eliminar":
  * Aparece un mensaje de confirmación previa.
  * *Restricción:* Si el vehículo seleccionado tiene ventas asociadas en la base de datos, la eliminación se cancela y se muestra un mensaje de error.

## 11. Administrar Trabajadores
* **Requisito:** Acceso exclusivo para Administrador.

### 11.1. Registro de Trabajadores
* **Validación:** El sistema comprueba de forma obligatoria que ni el campo del nombre ni el de la contraseña estén vacíos.
* **Resultado esperado:** Al introducir datos válidos en el formulario de registro, el nuevo trabajador queda guardado en el sistema.

### 11.2. Visualización y Gestión
* Se muestra una tabla organizada con la información de todos los trabajadores de la empresa.
* **Eliminar:** Al seleccionar un trabajador de la tabla, se habilita un botón que permite eliminar de forma permanente el registro seleccionado.