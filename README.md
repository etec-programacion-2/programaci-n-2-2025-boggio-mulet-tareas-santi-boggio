# SANTIAGO MULET Y MATEO BOGGIO
****
# Sistema de Gestión de Tareas 📋

Un sistema de gestión de tareas personal desarrollado en Kotlin que te permite organizar tu día a día como una agenda digital con gestión de usuarios y prioridades.

## 🚀 Características

- **Crear tareas**: Añade nuevas tareas con título, descripción detallada y nivel de prioridad
- **Sistema de prioridades**: Clasifica tus tareas por importancia (BAJA, MEDIA, ALTA)
- **Gestión de usuarios**: Registra usuarios con validación de email
- **Gestionar estado**: Marca tareas como completadas o pendientes con métodos específicos
- **Identificación única**: Cada tarea y usuario tiene un ID único para fácil seguimiento
- **Vista clara**: Visualiza el estado de tus tareas de forma organizada

### 🔄 Funcionalidades Actuales

- ✅ Creación de tareas con ID, título, descripción y prioridad
- ✅ Sistema de prioridades con enum class (BAJA, MEDIA, ALTA)
- ✅ Control de estado con métodos específicos (marcar completada/pendiente)
- ✅ Encapsulamiento de la propiedad `completada` para mayor seguridad
- ✅ Métodos getter para consultar el estado (`estaCompletada()`)
- ✅ Gestión de usuarios con validación de email
- ✅ Visualización formateada del estado de las tareas y usuarios

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

## 🚀 Cómo Ejecutar

1. Clona este repositorio
2. Abre el proyecto en tu IDE favorito (IntelliJ IDEA recomendado)
3. Ejecuta la función `main()` en `Main.kt`

## 📁 Estructura del Proyecto

```
src/main/kotlin/
├── Main.kt        # Punto de entrada con ejemplos de uso
├── Tarea.kt       # Data class para gestión de tareas con encapsulamiento
├── Usuario.kt     # Data class para gestión de usuarios
└── Prioridad.kt   # Enum class para niveles de prioridad
```

- Filtrado por prioridad y estado
- Interfaz gráfica de usuario
- Sistema de categorías para las tareas
