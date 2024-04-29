# Prueba Técnica Desarrollador Android.

https://github.com/gallordev/BZPayTest/assets/91449184/e70fc80d-7134-4e0f-a648-a551d00f95fe

##  **Importante!** 

Debido a problemas con mi setup de gradle y el keystore opte por cambiar la conexión con firebase a una de email con contraseña para no perder más tiempo. Agradezco su comprensión y espero no afecte el resultado.

---

Desarrolle una app que cumpla los siguientes criterios de aceptación:

* - [x] Realice una conexión exitosa con Firebase mediante la autentificación del usuario a través de un login en donde, con una cuenta de google permite el acceso al menú principal. (Al darle click al botón login aparecerá el pop up por defecto de google para pedir el usuario y contraseña, en caso de ser correcto se accede al menú principal)
* - [x] El menú principal deberá contener un ABC de alumnos, el cual funcionará de la siguiente manera:
  * - [x] Se pedirá Nombre de Alumno y Edad (Puede usar EditText u otro elemento a gusto)
  * - [x] Deberá contar con un botón Guardar Alumno para que, mediante ROOM se persista la información del usuario.
  * - [x] Deberá tener un RecyclerView que mostrará los registros de los alumnos, se mostrará el Nombre y la Edad.
  * - [x] El Recyclerview deberá tener la funcionalidad Swipe To Right (Deslizar hacia la derecha) que mostrará dos botones más, Eliminar y Modificar.
    * - [x] Al seleccionar eliminar, deberá de eliminar al Alumno y en automático el RecyclerView deberá obtener mostrar el cambio.
    * - [x] Al seleccionar Modificar, los datos del alumno seleccionado se mostrarán en los EditText anteriores, y el botón antes llamado Guardar deberá nombrarse por Editar, sólo se editará la edad del alumno, al presionar dicho botón, deberá actualizar la edad del alumno y se mostrará el cambio en el RecyclerView.
* - [x] A su elección (mediante un toolbar o actionbar, etc) deberá mostrar la opción “Ver Clima”.
  * - [x] En esta sección, se mostrará la temperatura actual, la cuál debe de mostrarse en grados centígrados.
  * - [x] Deberá pedir el permiso de ubicación al usuario para obtener la localización.
  * - [x] Mediante algún servidor gratuito de su preferencia (Weather API, Open-Meteo, etc) se deberá realizar la consulta por medio de RETROFIT.
  * - [x] Deberá tener un botón “ACTUALIZAR” para realizar N cantidad de consultas y actualizar la temperatura.
