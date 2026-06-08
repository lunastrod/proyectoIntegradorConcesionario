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