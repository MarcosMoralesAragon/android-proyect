# Proyecto Android

## Yushin - manager

Yushin manager es una aplicación de gestión de usuarios orientada hacia mi club de karate. Su funcionamiento actual nos permite :

> **Profesor:** Podemos **registrarnos** y logearnos.

> **Alumnos:** Podemos **añadir, editar , borrar y listar** a los alumnos que un profesor tenga registrado.

> **Centros:** Podemos **crear y listar** los centros asignados al profesor.

## Funcionamiento

La aplicación viene provista de datos para que se pueda testear aunque si desea crearse un usuario vacío puede hacerlo.

Si nos vamos al registro nos saldran los campos para rellenear, el campo usuario sera como se referira hacia nosotros la aplicación apartir de ahora internamente

Para tener acceso al usuario cargado de datos deberá introducir los siguientes datos : 

|        x          |Correo              |Contraseña                           |
|-------------------|--------------------|-------------------------------------|
|Usuario recomendado|`prueba2@mail.com`  |`123456`                             |


Se debe tener en cuenta que los centros y alumnos estan vinculados al usuario, es decir , los datos por defecto estan vinculados unicamente a `prueba2@mail.com` haciendo que los alumnos de un profesor no sean accesibles para los otros

## *Información a tener en cuenta*

Cuando vamos a crear un alumno tenemos que introducir el nombre del centro, para esto se recomienda o crear un centro que conozcamos o listar antes los cursos que tenemos disponibles ( si nos encontramos en el por defecto )

## ER

![inicio](/capturas/diagrama.png)

## Capturas
### Login
![inicio](/capturas/inicio-login.png)
### Registro
![inicio](/capturas/inicio-registro.png)
### Home
![inicio](/capturas/inicio-home.png)
### Crear alumno
![inicio](/capturas/crear-alumno.png)
### Crear centro
![inicio](/capturas/crear-centro.png)
### Listar centro
![inicio](/capturas/lista-centros.png)
### Listar alumnos
![inicio](/capturas/lista-alumnos.png)
### Editar alumnos
![inicio](/capturas/editar.png)
