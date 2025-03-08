# NoHayDosSinTres - Sistema de Gestión de Invitados

## Descripción
NoHayDosSinTres es una aplicación web desarrollada en **Java, JSP y Maven**, diseñada para gestionar los invitados de un evento. La aplicación permite a los organizadores registrar, modificar y eliminar invitados, así como visualizar detalles clave en una interfaz amigable.

## Tecnologías Utilizadas
- **Java (JSP & Servlets)**
- **Maven** para la gestión de dependencias
- **MySQL** como base de datos
- **phpMyAdmin** para la gestión de la base de datos
- **Render Web Service** para el despliegue
- **Docker** para la contenedorización

## Instalación y Configuración

### 1. Clonar el Repositorio
```sh
git clone https://github.com/usuario/NoHayDosSinTres.git
cd NoHayDosSinTres

# Configurar el Archivo pom.xml
# Verifica las dependencias en pom.xml y asegúrate de tener configurado el conector de MySQL:
cat <<EOF > pom.xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.26</version>
</dependency>
EOF

# Configurar la Base de Datos
# Importar el esquema de la base de datos desde database.sql
# Acceder a phpMyAdmin en http://localhost/phpmyadmin/
# Crear una base de datos llamada nohaydossintres
# Importar el archivo SQL en phpMyAdmin

# Configurar Render Web Service
# El archivo render.yaml contiene la configuración para el despliegue en Render.
# Asegúrate de configurar las variables de entorno necesarias en el panel de Render.

cat <<EOF > render.yaml
databases:
  - name: nohaydossintres_db
    databaseName: nohaydossintres
    user: usuario
    password: contraseña_segura
EOF

# Construcción y Ejecución con Maven
mvn clean package
mvn jetty:run

# La aplicación estará disponible en http://localhost:8080/NoHayDosSinTres

# Despliegue en Render
# Para desplegar en Render, sigue estos pasos:
# 1. Sube el código a un repositorio de GitHub.
# 2. Entra a Render, crea un nuevo servicio web y conecta el repositorio.
# 3. Configura los entornos y despliega la aplicación.

# Contacto
# Si tienes preguntas o sugerencias, contacta con el dios de la programacion CHATGPT4.
