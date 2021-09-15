# Certificación II 2893
Ejercicio de ejemplo para la materia de Certificación II NRC 7618: Spring Boot + Thymeleaf + jQuery + Bootstrap

## Trabajar en el proyecto
Usaremos un estandar de Git llamado Git Flow:
![Git Flow](https://3.bp.blogspot.com/-K7fGmmH0kI8/WkHMyoUy3nI/AAAAAAAACA0/oU4PdlbrD_wMY5qerk4h-Btz4eiZH5zogCLcBGAs/s640/03%2B%25282%2529.png)

El primer paso es crear una rama que se derive de la rama `develop`:

### ¿Cómo nombrar a la rama?
`feature-verbo infinitivo principal-complemento...`\
Ejemplo:\
`feature-cambiar-interfaz-login`

### ¿Cómo creo la rama desde consola?
`git checkout -b 'NOMBRE-DE-LA-RAMA'`\
Ejemplo:\
`git checkout -b 'feature-crear-proyecto-paciente'`

## Actualizar el repositorio
Para esto es necesario dirigirse al repositorio a actualizar y correr:\
`git pull`

### ¿Cómo subo mi código?
1. Agregar los nuevos cambios\ `git add -A`.
1. Hacer un commit\ `git commit -m 'Mensaje del commit'`.
2. Luego `git push` y copiar el comando que sugiere git para publicar la nueva rama.

### ¿Qué hago después subir?
1. Se debe hacer un Pull Request de la rama subida hacia `develop`.
2. Asignar la revisión del Pull Request a la persona encargada de hacerlo.
3. Esperar la revisión y merge del Pull Request.💪

