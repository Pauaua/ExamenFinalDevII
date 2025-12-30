# Sistema de Gestión de Álbumes de Láminas

Una aplicación Spring Boot para la gestión de álbumes de láminas (sticker albums), permitiendo a los usuarios administrar sus colecciones de manera eficiente.

## Descripción General

Este proyecto es una API REST desarrollada con Spring Boot que permite gestionar álbumes de láminas (como álbumes de cromos, tarjetas coleccionables, etc.). La aplicación incluye funcionalidades para crear, leer, actualizar y eliminar álbumes y láminas individuales, así como funcionalidades especiales para gestionar láminas faltantes y repetidas.

## Características

- Gestión completa de álbumes (CRUD)
- Gestión completa de láminas (CRUD)
- Subida de imágenes para las láminas
- Funcionalidad para agregar múltiples láminas a un álbum
- Identificación de láminas faltantes en un álbum
- Identificación de láminas repetidas en un álbum
- API REST con endpoints bien definidos
- Persistencia de datos con JPA y MySQL

## Tecnologías Utilizadas

- Java 17
- Spring Boot 4.0.1
- Spring Data JPA
- Spring Web
- MySQL Connector
- Maven
- Hibernate

## Requisitos del Sistema

- Java 17 o superior
- Maven 3.6.0 o superior
- MySQL 5.7 o superior
- Node.js (opcional, para frontend)

## Configuración del Proyecto

### 1. Clonar el Repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd examenfinal
```

### 2. Configurar la Base de Datos

1. Crear una base de datos MySQL llamada `albumes_db`
2. Actualizar las credenciales en `src/main/resources/application.properties` si es necesario:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/albumes_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Compilar y Ejecutar la Aplicación

```bash
# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8081`

## Configuración de la Base de Datos

Antes de ejecutar la aplicación, asegúrate de tener MySQL instalado y en ejecución. Luego:

1. Inicia sesión en MySQL:
```bash
mysql -u root -p
```

2. Crea la base de datos:
```sql
CREATE DATABASE albumes_db;
```

3. Sal de MySQL:
```sql
EXIT;
```

La aplicación está configurada para crear automáticamente las tablas necesarias al iniciar.

## API Endpoints

### Álbumes

#### `GET /api/albumes`
- **Descripción**: Obtiene todos los álbumes
- **Response**: 200 OK - Lista de álbumes

#### `GET /api/albumes/{id}`
- **Descripción**: Obtiene un álbum específico por ID
- **Response**: 
  - 200 OK - Álbum encontrado
  - 404 Not Found - Álbum no encontrado

#### `POST /api/albumes`
- **Descripción**: Crea un nuevo álbum
- **Request Body**:
```json
{
  "nombre": "Álbum Mundial 1992",
  "imagen": "url_de_la_imagen",
  "fechaLanzamiento": "1992-11-20",
  "tipoLaminas": "Futbolistas"
}
```
- **Response**: 201 Created - Álbum creado

#### `PUT /api/albumes/{id}`
- **Descripción**: Actualiza un álbum existente
- **Response**: 
  - 200 OK - Álbum actualizado
  - 404 Not Found - Álbum no encontrado

#### `DELETE /api/albumes/{id}`
- **Descripción**: Elimina un álbum
- **Response**: 
  - 204 No Content - Álbum eliminado
  - 404 Not Found - Álbum no encontrado

### Láminas

#### `GET /api/laminas`
- **Descripción**: Obtiene todas las láminas
- **Response**: 200 OK - Lista de láminas

#### `GET /api/laminas/{id}`
- **Descripción**: Obtiene una lámina específica por ID
- **Response**: 
  - 200 OK - Lámina encontrada
  - 404 Not Found - Lámina no encontrada

#### `GET /api/laminas/album/{albumId}`
- **Descripción**: Obtiene todas las láminas de un álbum específico
- **Response**: 200 OK - Lista de láminas del álbum

#### `POST /api/laminas`
- **Descripción**: Crea una nueva lámina
- **Request Body**:
```json
{
  "nombre": "Lámina de Messi",
  "imagen": "url_de_la_imagen",
  "album": {
    "id": 1
  },
  "numeroLamina": 1,
  "cantidadRepetidas": 0
}
```
- **Response**: 201 Created - Lámina creada

#### `POST /api/laminas/upload`
- **Descripción**: Crea una nueva lámina con imagen subida
- **Request**: Form data con los siguientes campos:
  - nombre: Nombre de la lámina
  - albumId: ID del álbum al que pertenece
  - numeroLamina: Número de la lámina (opcional)
  - imagen: Archivo de imagen
- **Response**: 
  - 201 Created - Lámina creada con imagen
  - 404 Not Found - Álbum no encontrado
  - 500 Internal Server Error - Error al procesar la imagen

#### `PUT /api/laminas/{id}`
- **Descripción**: Actualiza una lámina existente
- **Response**: 
  - 200 OK - Lámina actualizada
  - 404 Not Found - Lámina no encontrada

#### `DELETE /api/laminas/{id}`
- **Descripción**: Elimina una lámina
- **Response**: 
  - 204 No Content - Lámina eliminada
  - 404 Not Found - Lámina no encontrada

### Funcionalidades Especiales para Álbumes

#### `POST /api/albumes/{albumId}/laminas`
- **Descripción**: Agrega múltiples láminas a un álbum
- **Request**: ID del álbum en la URL y lista de láminas en el body
```json
[
  {
    "nombre": "Lámina de Cabani",
    "numeroLamina": 1
  },
  {
    "nombre": "Lámina de Jara",
    "numeroLamina": 2
  }
]
```
- **Response**: 
  - 201 Created - Láminas agregadas
  - 404 Not Found - Álbum no encontrado

#### `GET /api/albumes/{albumId}/laminas/faltantes`
- **Descripción**: Obtiene la lista de láminas faltantes para un álbum
- **Response**: 200 OK - Lista de láminas faltantes

#### `GET /api/albumes/{albumId}/laminas/repetidas`
- **Descripción**: Obtiene la lista de láminas repetidas para un álbum con su cantidad
- **Response**: 200 OK - Lista de láminas repetidas

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/example/examenfinal/
│   │       ├── ExamenfinalApplication.java
│   │       ├── config/
│   │       │   └── WebConfig.java
│   │       ├── controller/
│   │       │   ├── AlbumController.java
│   │       │   └── LaminaController.java
│   │       ├── exception/
│   │       ├── model/
│   │       │   ├── Album.java
│   │       │   ├── Lamina.java
│   │       │   ├── LaminaDTO.java
│   │       │   └── LaminaEstado.java
│   │       ├── repository/
│   │       │   ├── AlbumRepository.java
│   │       │   └── LaminaRepository.java
│   │       ├── service/
│   │       │   ├── impl/
│   │       │   ├── AlbumService.java
│   │       │   ├── AlbumServiceInterface.java
│   │       │   └── LaminaService.java
│   │       └── util/
│   │           └── FileUploadUtil.java
│   └── resources/
│       ├── application.properties
│       └── uploads/ (directorio para imágenes subidas)
```

## Configuración Adicional

### Subida de Archivos
- Tamaño máximo de archivo: 10MB
- Directorio de almacenamiento: `./src/main/resources/uploads/`
- El sistema genera nombres únicos para los archivos usando UUID

### Configuración CORS
- Permite solicitudes desde `http://localhost:3000`
- Métodos permitidos: GET, POST, PUT, DELETE, OPTIONS

## Ejemplos de Uso

### Crear un nuevo álbum
```bash
curl -X POST http://localhost:8081/api/albumes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Álbum Mundial 2026",
    "imagen": "https://ejemplo.com/album-mundial.jpg",
    "fechaLanzamiento": "2026-06-11",
    "tipoLaminas": "Jugadores de Fútbol"
  }'
```

### Crear una lámina con imagen
```bash
curl -X POST http://localhost:8081/api/laminas/upload \
  -F "nombre=Lámina de Messi" \
  -F "albumId=1" \
  -F "numeroLamina=1" \
  -F "imagen=@ruta/a/la/imagen.jpg"
```

### Obtener láminas faltantes de un álbum
```bash
curl -X GET http://localhost:8081/api/albumes/1/laminas/faltantes
```

## Contribución

1. Haz un fork del proyecto
2. Crea una rama para tu característica (`git checkout -b feature/NuevaCaracteristica`)
3. Haz commit de tus cambios (`git commit -m 'Añadir nueva característica'`)
4. Haz push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

## Uso de IA

Corrección de errores y complemento de códigos.

## EXAMEN FINAL 
## Desarrollo de Software Web II 

## Creado por

Paulina Acuña 

