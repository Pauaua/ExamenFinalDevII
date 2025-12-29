# Documentación de la API - Sistema de Gestión de Álbumes de Láminas

## Descripción General
API REST para la gestión de álbumes de láminas, permitiendo a los usuarios administrar sus colecciones de manera eficiente.

## Endpoints

### Álbumes

#### GET /api/albumes
- **Descripción**: Obtiene todos los álbumes
- **Request**: Ninguno
- **Response**: 
  - 200 OK: Lista de álbumes
  - Ejemplo:
  ```json
  [
    {
      "id": 1,
      "nombre": "Álbum Mundial 2022",
      "imagen": "url_de_la_imagen",
      "fechaLanzamiento": "2022-11-20",
      "tipoLaminas": "Futbolistas"
    }
  ]
  ```

#### GET /api/albumes/{id}
- **Descripción**: Obtiene un álbum específico por ID
- **Request**: ID del álbum en la URL
- **Response**:
  - 200 OK: Álbum encontrado
  - 404 Not Found: Álbum no encontrado
  - Ejemplo:
  ```json
  {
    "id": 1,
    "nombre": "Álbum Mundial 2022",
    "imagen": "url_de_la_imagen",
    "fechaLanzamiento": "2022-11-20",
    "tipoLaminas": "Futbolistas"
  }
  ```

#### POST /api/albumes
- **Descripción**: Crea un nuevo álbum
- **Request**: Datos del álbum en el body
  ```json
  {
    "nombre": "Álbum Mundial 2022",
    "imagen": "url_de_la_imagen",
    "fechaLanzamiento": "2022-11-20",
    "tipoLaminas": "Futbolistas"
  }
  ```
- **Response**:
  - 201 Created: Álbum creado
  - Ejemplo:
  ```json
  {
    "id": 1,
    "nombre": "Álbum Mundial 2022",
    "imagen": "url_de_la_imagen",
    "fechaLanzamiento": "2022-11-20",
    "tipoLaminas": "Futbolistas"
  }
  ```

#### PUT /api/albumes/{id}
- **Descripción**: Actualiza un álbum existente
- **Request**: ID del álbum en la URL y datos actualizados en el body
- **Response**:
  - 200 OK: Álbum actualizado
  - 404 Not Found: Álbum no encontrado

#### DELETE /api/albumes/{id}
- **Descripción**: Elimina un álbum
- **Request**: ID del álbum en la URL
- **Response**:
  - 204 No Content: Álbum eliminado
  - 404 Not Found: Álbum no encontrado

### Láminas

#### GET /api/laminas
- **Descripción**: Obtiene todas las láminas
- **Request**: Ninguno
- **Response**:
  - 200 OK: Lista de láminas

#### GET /api/laminas/{id}
- **Descripción**: Obtiene una lámina específica por ID
- **Request**: ID de la lámina en la URL
- **Response**:
  - 200 OK: Lámina encontrada
  - 404 Not Found: Lámina no encontrada

#### GET /api/laminas/album/{albumId}
- **Descripción**: Obtiene todas las láminas de un álbum específico
- **Request**: ID del álbum en la URL
- **Response**:
  - 200 OK: Lista de láminas del álbum

#### POST /api/laminas
- **Descripción**: Crea una nueva lámina
- **Request**: Datos de la lámina en el body
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
- **Response**:
  - 201 Created: Lámina creada

#### POST /api/laminas/upload
- **Descripción**: Crea una nueva lámina con imagen subida
- **Request**: Form data con los siguientes campos:
  - nombre: Nombre de la lámina
  - albumId: ID del álbum al que pertenece
  - numeroLamina: Número de la lámina (opcional)
  - imagen: Archivo de imagen
- **Response**:
  - 201 Created: Lámina creada con imagen
  - 404 Not Found: Álbum no encontrado
  - 500 Internal Server Error: Error al procesar la imagen

#### PUT /api/laminas/{id}
- **Descripción**: Actualiza una lámina existente
- **Request**: ID de la lámina en la URL y datos actualizados en el body
- **Response**:
  - 200 OK: Lámina actualizada
  - 404 Not Found: Lámina no encontrada

#### DELETE /api/laminas/{id}
- **Descripción**: Elimina una lámina
- **Request**: ID de la lámina en la URL
- **Response**:
  - 204 No Content: Lámina eliminada
  - 404 Not Found: Lámina no encontrada

### Funcionalidades Especiales para Álbumes

#### POST /api/albumes/{albumId}/laminas
- **Descripción**: Agrega múltiples láminas a un álbum
- **Request**: ID del álbum en la URL y lista de láminas en el body
  ```json
  [
    {
      "nombre": "Lámina de Messi",
      "numeroLamina": 1
    },
    {
      "nombre": "Lámina de Neymar",
      "numeroLamina": 2
    }
  ]
  ```
- **Response**:
  - 201 Created: Láminas agregadas
  - 404 Not Found: Álbum no encontrado

#### GET /api/albumes/{albumId}/laminas/faltantes
- **Descripción**: Obtiene la lista de láminas faltantes para un álbum
- **Request**: ID del álbum en la URL
- **Response**:
  - 200 OK: Lista de láminas faltantes
  - Ejemplo:
  ```json
  [
    {
      "nombre": "Lámina #3",
      "numeroLamina": 3,
      "cantidadRepetidas": 0
    }
  ]
  ```

#### GET /api/albumes/{albumId}/laminas/repetidas
- **Descripción**: Obtiene la lista de láminas repetidas para un álbum con su cantidad
- **Request**: ID del álbum en la URL
- **Response**:
  - 200 OK: Lista de láminas repetidas
  - Ejemplo:
  ```json
  [
    {
      "nombre": "Lámina de Messi",
      "numeroLamina": 1,
      "cantidadRepetidas": 3
    }
  ]
  ```