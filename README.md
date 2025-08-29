# SANTIAGO MULET Y MATEO BOGGIO
****
# Sistema de Gestión de Tareas 📋

Un sistema de gestión de tareas personal desarrollado en Kotlin que te permite organizar tu día a día como una agenda digital con gestión de usuarios y prioridades.

## 🚀 Características

- **Crear tareas**: Añade nuevas tareas con título, descripción detallada y nivel de prioridad
- **Sistema de prioridades**: Clasifica tus tareas por importancia (BAJA, MEDIA, ALTA)
- **Gestión de usuarios**: Registra usuarios con validación de email
- **Gestionar estado**: Marca tareas como completadas o pendientes
- **Identificación única**: Cada tarea y usuario tiene un ID único para fácil seguimiento
- **Vista clara**: Visualiza el estado de tus tareas de forma organizada

### 🔄 Funcionalidades Actuales

- ✅ Creación de tareas con ID, título, descripción y prioridad
- ✅ Sistema de prioridades con enum class (BAJA, MEDIA, ALTA)
- ✅ Alternar estado de completitud (pendiente ↔ completada)
- ✅ Gestión de usuarios con validación de email
- ✅ Visualización formateada del estado de las tareas y usuarios

## 🏗️ Arquitectura

### Clase `Tarea`
Data class que encapsula toda la información de una tarea individual:

**Propiedades:**
- `id: Int` - Identificador único (inmutable)
- `titulo: String` - Título descriptivo (inmutable)
- `descripcion: String` - Descripción detallada (inmutable)
- `completada: Boolean` - Estado de completitud (mutable, por defecto `false`)
- `prioridad: Prioridad` - Nivel de importancia de la tarea (inmutable)

**Métodos:**
- `alternarCompletada()` - Cambia el estado entre completada y pendiente
- `toString()` - Representación formateada de la tarea con prioridad

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

### Creación de Tareas

```kotlin
// Crear una nueva tarea con prioridad
val tarea = Tarea(1, "Estudiar Kotlin", "Repasar conceptos de POO y data classes", prioridad = Prioridad.ALTA)

// Mostrar el estado actual
println(tarea) 
// |Pendiente| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA

// Marcar como completada
tarea.alternarCompletada()
println(tarea) 
// |Completada| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA
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
├── Main.kt        # Punto de entrada de la aplicación
├── Tarea.kt       # Data class para gestión de tareas
├── Usuario.kt     # Data class para gestión de usuarios
└── Prioridad.kt   # Enum class para niveles de prioridad
```
