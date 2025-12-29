# Sistema de Gestión de Álbumes de Láminas

## Descripción

Sistema de gestión para coleccionistas de láminas de álbumes, desarrollado con Spring Boot. Permite a los usuarios gestionar sus colecciones de manera eficiente, desde la creación de álbumes hasta el seguimiento de las láminas faltantes y repetidas.

## Características

- API REST completa para la gestión de álbumes y láminas
- Conexión a base de datos MySQL
- Gestión de imágenes para álbumes y láminas
- Funcionalidades especiales para el manejo de láminas:
  - Listado de láminas faltantes
  - Listado de láminas repetidas con cantidad
- Carga masiva de láminas
- Operaciones CRUD completas

## Tecnologías utilizadas

- Java 17
- Spring Boot 4.0.1
- Spring Data JPA
- MySQL
- Maven
- Hibernate

## Requisitos

- Java 17 o superior
- Maven 3.6.0 o superior
- MySQL Server
- Git (opcional, para clonar el proyecto)

## Instalación

1. **Clonar el repositorio** (si aplica):
   ```bash
   git clone <url-del-repositorio>
   cd examenfinal
   ```

2. **Configurar la base de datos**:
   - Asegúrate de tener MySQL instalado y en ejecución
   - Crear una base de datos llamada `albumes_db`
   - Actualizar las credenciales en `src/main/resources/application.properties`

3. **Actualizar credenciales de base de datos** (en `application.properties`):
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/albumes_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña
   ```

4. **Compilar y ejecutar el proyecto**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Endpoints de la API

### Álbumes

- `GET /api/albumes` - Obtener todos los álbumes
- `GET /api/albumes/{id}` - Obtener un álbum específico
- `POST /api/albumes` - Crear un nuevo álbum
- `PUT /api/albumes/{id}` - Actualizar un álbum
- `DELETE /api/albumes/{id}` - Eliminar un álbum

### Láminas

- `GET /api/laminas` - Obtener todas las láminas
- `GET /api/laminas/{id}` - Obtener una lámina específica
- `GET /api/laminas/album/{albumId}` - Obtener láminas de un álbum específico
- `POST /api/laminas` - Crear una nueva lámina
- `POST /api/laminas/upload` - Crear una lámina con imagen
- `PUT /api/laminas/{id}` - Actualizar una lámina
- `DELETE /api/laminas/{id}` - Eliminar una lámina

### Funcionalidades Especiales

- `POST /api/albumes/{albumId}/laminas` - Agregar múltiples láminas a un álbum
- `GET /api/albumes/{albumId}/laminas/faltantes` - Obtener láminas faltantes
- `GET /api/albumes/{albumId}/laminas/repetidas` - Obtener láminas repetidas con cantidad

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/example/examenfinal/
│   │       ├── ExamenfinalApplication.java
│   │       ├── controller/
│   │       │   ├── AlbumController.java
│   │       │   └── LaminaController.java
│   │       ├── model/
│   │       │   ├── Album.java
│   │       │   ├── Lamina.java
│   │       │   ├── LaminaDTO.java
│   │       │   └── LaminaEstado.java
│   │       ├── service/
│   │       │   ├── AlbumService.java
│   │       │   └── LaminaService.java
│   │       ├── repository/
│   │       │   ├── AlbumRepository.java
│   │       │   └── LaminaRepository.java
│   │       └── util/
│   │           └── FileUploadUtil.java
│   └── resources/
│       └── application.properties
```

## Modelo de Datos

### Álbum
- id: Long (clave primaria, auto-generada)
- nombre: String (requerido)
- imagen: String (URL o ruta de la imagen)
- fechaLanzamiento: LocalDate
- tipoLaminas: String (tipo de láminas que contiene el álbum)

### Lámina
- id: Long (clave primaria, auto-generada)
- nombre: String (requerido)
- imagen: String (URL o ruta de la imagen)
- album: Album (relación con el álbum al que pertenece)
- numeroLamina: Integer (número de la lámina en el álbum)
- fechaCreacion: LocalDate
- cantidadRepetidas: Integer (cantidad de veces que se tiene la lámina repetida)

## Configuración de Archivos

El sistema permite la subida de archivos de imagen para álbumes y láminas. Los archivos se almacenan en el directorio especificado en la propiedad `file.upload-dir` del archivo `application.properties`.

## Pruebas

El proyecto incluye pruebas unitarias y de integración para verificar el correcto funcionamiento de todos los endpoints y la lógica de negocio.

## Documentación de la API

Ver el archivo `DOCUMENTACION_API.md` para la documentación completa de los endpoints, con ejemplos de solicitud y respuesta.

## Contribución

1. Haz un fork del proyecto
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`)
3. Haz commit de tus cambios (`git commit -am 'Añadir nueva funcionalidad'`)
4. Sube la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un nuevo Pull Request

## Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT.

## Autor

Coleccionistas de Láminas - Sistema de Gestión