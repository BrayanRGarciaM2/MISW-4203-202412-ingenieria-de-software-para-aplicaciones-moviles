# Vinilos App

Vinilos app es la versión para dispositivos móvil de la aplicación web Vinilos una aplicación móvil, desarrollada en Kotlin y diseñada para todos los amantes de la música.
La aplicación Vinilos ofrece diversas funcionalidades, como explorar el catálogo de álbumes musicales, obtener información detallada sobre cada álbum, descubrir artistas y consultar datos sobre coleccionistas. Además, los perfiles de coleccionistas permiten agregar álbumes al listado y marcar artistas favoritos.

## Consideraciones
Vinilos app consume la API REST [BackVynils](https://github.com/TheSoftwareDesignLab/BackVynils.git) por lo tanto es importante descargarlo.

## Pasos de ejecución del proyecto

1. Configurar el backend de la aplicación "[Backvynils](https://github.com/TheSoftwareDesignLab/BackVynils.git)" para que se ejecute en el puerto 3000. Esto se puede lograr fácilmente utilizando Docker.
2. Configurar la base de datos PostgreSQL de la aplicación para que se ejecute en el puerto 5432. También se puede hacer mediante Docker.
3. Clonar este repositorio en tu entorno local.
4. Abrir el proyecto utilizando Android Studio.
5. Actualizar la dirección IP en el archivo [Constants.kt](https://github.com/BrayanRGarciaM2/MISW-4203-202412-ingenieria-de-software-para-aplicaciones-moviles/blob/main/Vinilos/app/src/main/java/com/tsdc/vinilos/utils/Constants.kt) con la correspondiente a localhost de tu máquina. Puede ser "10.0.2.2" o la IP de tu equipo.
6. Ejecutar la aplicación desde Android Studio.

## APK de la aplicación 

En el repositorio podemos encontrar el apk generado de la aplicación: [app-release-unsigned.apk](https://github.com/BrayanRGarciaM2/MISW-4203-202412-ingenieria-de-software-para-aplicaciones-moviles/blob/main/app-release-unsigned.apk).
