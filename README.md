# SANTIAGO MULET Y MATEO BOGGIO
****
# Sistema de Gestión de Tareas 📋

Un sistema de gestión de tareas personal desarrollado en Kotlin que te permite organizar tu día a día como una agenda digital.

## 🚀 Características

- **Crear tareas**: Añade nuevas tareas con título y descripción detallada
- **Gestionar estado**: Marca tareas como completadas o pendientes
- **Identificación única**: Cada tarea tiene un ID único para fácil seguimiento
- **Vista clara**: Visualiza el estado de tus tareas de forma organizada

### 🔄 Funcionalidades Actuales

- ✅ Creación de tareas con ID, título y descripción
- ✅ Alternar estado de completitud (pendiente ↔ completada)
- ✅ Visualización formateada del estado de las tareas

## 🏗️ Arquitectura

### Clase `Tarea`
Data class que encapsula toda la información de una tarea individual:

**Propiedades:**
- `id: Int` - Identificador único (inmutable)
- `titulo: String` - Título descriptivo (inmutable)
- `descripcion: String` - Descripción detallada (inmutable)
- `completada: Boolean` - Estado de completitud (mutable, por defecto `false`)

**Métodos:**
- `alternarCompletada()` - Cambia el estado entre completada y pendiente
- `toString()` - Representación formateada de la tarea

## 🎯 Ejemplo de Uso

```kotlin
// Crear una nueva tarea
val tarea = Tarea(1, "Estudiar Kotlin", "Repasar conceptos de POO y data classes")

// Mostrar el estado actual
println(tarea) // |Pendiente| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes

// Marcar como completada
tarea.alternarCompletada()
println(tarea) // |Completada| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes
```

## 🚀 Cómo Ejecutar

1. Clona este repositorio
2. Abre el proyecto en tu IDE favorito (IntelliJ IDEA recomendado)
3. Ejecuta la función `main()` en `Main.kt`

## 📋 Requisitos

- Kotlin 1.8+
- JVM 11+

