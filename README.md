# SANTIAGO MULET Y MATEO BOGGIO
****
# Sistema de Gestión de Tareas 📋

Un sistema de gestión de tareas personal desarrollado en Kotlin que te permite organizar tu día a día como una agenda digital con gestión de usuarios, prioridades y proyectos.

## 🚀 Características

- **Crear tareas**: Añade nuevas tareas con título, descripción detallada y nivel de prioridad
- **Sistema de prioridades**: Clasifica tus tareas por importancia (BAJA, MEDIA, ALTA)
- **Gestión de usuarios**: Registra usuarios con validación de email
- **Gestión de proyectos**: Organiza tareas relacionadas bajo proyectos específicos
- **Gestionar estado**: Marca tareas como completadas o pendientes con métodos específicos
- **Identificación única**: Cada tarea, usuario y proyecto tiene un ID único para fácil seguimiento
- **Vista clara**: Visualiza el estado de tus tareas y proyectos de forma organizada

### 🔄 Funcionalidades Actuales

- ✅ Creación de tareas con ID, título, descripción y prioridad
- ✅ Sistema de prioridades con enum class (BAJA, MEDIA, ALTA)
- ✅ Control de estado con métodos específicos (marcar completada/pendiente)
- ✅ Encapsulamiento de la propiedad `completada` para mayor seguridad
- ✅ Métodos getter para consultar el estado (`estaCompletada()`)
- ✅ Gestión de usuarios con validación de email
- ✅ Gestión de proyectos con agrupación de tareas
- ✅ Visualización formateada del estado de las tareas, usuarios y proyectos

## 🏗️ Arquitectura

### Clase `Tarea`
Data class que encapsula toda la información de una tarea individual con principios de POO:

**Propiedades:**
- `id: Int` - Identificador único (inmutable)
- `titulo: String` - Título descriptivo (inmutable)
- `descripcion: String` - Descripción detallada (inmutable)
- `completada: Boolean` - Estado de completitud (privado y mutable, por defecto `false`)
- `prioridad: Prioridad` - Nivel de importancia de la tarea (inmutable)

**Métodos de Control de Estado:**
- `marcarComoCompletada()` - Establece la tarea como completada (true)
- `marcarComoPendiente()` - Establece la tarea como pendiente (false)
- `alternarCompletada()` - Cambia el estado entre completada y pendiente
- `estaCompletada(): Boolean` - Getter para consultar el estado actual

**Características de Encapsulamiento:**
- La propiedad `completada` es privada para forzar el uso de métodos controlados
- Interfaz pública clara y segura para manipular el estado
- Previene modificaciones accidentales del estado interno

**Otros Métodos:**
- `toString()` - Representación formateada de la tarea con estado y prioridad

### Enum `Prioridad`
Enum class que define los niveles de prioridad disponibles:

**Valores:**
- `BAJA` - Para tareas de menor urgencia
- `MEDIA` - Para tareas de importancia moderada  
- `ALTA` - Para tareas críticas o urgentes

**Ventajas:**
- Type-safety: solo permite valores predefinidos
- Previene errores de escritura
- Autocompletado en IDE
- Refactoring seguro

### Clase `Usuario`
Data class que representa un usuario del sistema:

**Propiedades:**
- `id: Int` - Identificador único (inmutable)
- `nombre: String` - Nombre del usuario (inmutable)
- `email: String` - Email del usuario con validación (inmutable)

**Validaciones:**
- Email no puede estar vacío
- Email debe contener "@" y ".com"

**Métodos:**
- `isValidEmail()` - Validación privada del formato de email
- `toString()` - Representación formateada del usuario

### Clase `Proyecto`
Data class que representa un proyecto que agrupa tareas relacionadas:

**Propiedades:**
- `id: Int` - Identificador único del proyecto (inmutable)
- `nombre: String` - Nombre descriptivo del proyecto (inmutable)
- `descripcion: String` - Descripción detallada del proyecto (inmutable)
- `listadetareas: List<String>` - Lista de títulos de tareas asociadas al proyecto (inmutable)

**Características:**
- Permite organizar tareas bajo proyectos específicos
- Facilita la visualización y gestión de tareas relacionadas
- Mantiene una referencia a las tareas mediante sus títulos

**Métodos:**
- `toString()` - Representación formateada del proyecto con su información y lista de tareas

## 🎯 Ejemplo de Uso

### Creación y Gestión de Tareas

```kotlin
// Crear una nueva tarea con prioridad
val tarea = Tarea(1, "Estudiar Kotlin", "Repasar conceptos de POO y data classes", prioridad = Prioridad.ALTA)

// Mostrar el estado actual
println(tarea) 
// |Pendiente| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA

// Verificar estado actual
println("¿Está completada? ${tarea.estaCompletada()}") // false

// Marcar como completada usando método específico
tarea.marcarComoCompletada()
println(tarea) 
// |Completada| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA

// Verificar nuevo estado
println("¿Está completada? ${tarea.estaCompletada()}") // true

// Alternar estado (completada → pendiente)
tarea.alternarCompletada()
println(tarea)
// |Pendiente| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA
```

### Gestión de Usuarios

```kotlin
// Crear un nuevo usuario
val usuario = Usuario(1, "Santiago", "santimulet@gmail.com")

// Mostrar información del usuario
println(usuario) // [1] Santiago -> santimulet@gmail.com
```

### Gestión de Proyectos

```kotlin
// Crear una tarea
val tarea = Tarea(1, "Primera tarea", "Esta es la descripción de la primera tarea", prioridad = Prioridad.BAJA)

// Crear una lista de tareas para el proyecto
val listaTareas = listOf(tarea.titulo)

// Crear un nuevo proyecto
val proyecto = Proyecto(1, "Proyecto de Desarrollo", "Sistema de gestión de tareas en Kotlin", listaTareas)

// Mostrar información del proyecto
println(proyecto)
/* Salida:
ID: 1
Nombre: Proyecto de Desarrollo
Esta es la descripcion del proyecto: Sistema de gestión de tareas en Kotlin
Estas son sus tareas: [Primera tarea]
*/
```

## 🚀 Cómo Ejecutar

1. Clona este repositorio
2. Abre el proyecto en tu IDE favorito (IntelliJ IDEA recomendado)
3. Ejecuta la función `main()` en `Main.kt`
