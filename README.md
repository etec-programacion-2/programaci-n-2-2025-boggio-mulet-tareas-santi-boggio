# SANTIAGO MULET Y MATEO BOGGIO
****
# Sistema de Gestión de Tareas 📋

Un sistema de gestión de tareas personal desarrollado en Kotlin que te permite organizar tu día a día como una agenda digital con gestión de usuarios, prioridades, proyectos y asignación de responsables.

## 🚀 Características

- **Crear tareas**: Añade nuevas tareas con título, descripción detallada y nivel de prioridad
- **Sistema de prioridades**: Clasifica tus tareas por importancia (BAJA, MEDIA, ALTA)
- **Gestión de usuarios**: Registra usuarios con validación de email
- **Asignación de tareas**: Asigna tareas a usuarios específicos como responsables
- **Gestión de proyectos**: Organiza tareas relacionadas bajo proyectos específicos
- **Gestionar estado**: Marca tareas como completadas o pendientes con métodos específicos
- **Identificación única**: Cada tarea, usuario y proyecto tiene un ID único para fácil seguimiento
- **Vista clara**: Visualiza el estado de tus tareas, proyectos y asignaciones de forma organizada

### 🔄 Funcionalidades Actuales

- ✅ Creación de tareas con ID, título, descripción y prioridad
- ✅ Sistema de prioridades con enum class (BAJA, MEDIA, ALTA)
- ✅ Control de estado con métodos específicos (marcar completada/pendiente)
- ✅ Encapsulamiento de la propiedad `completada` para mayor seguridad
- ✅ Métodos getter para consultar el estado (`estaCompletada()`)
- ✅ **Asignación de usuarios a tareas con control de acceso**
- ✅ **Métodos para asignar, desasignar y consultar usuarios responsables**
- ✅ Gestión de usuarios con validación de email
- ✅ Gestión de proyectos con agrupación de tareas
- ✅ Visualización formateada del estado de las tareas, usuarios, proyectos y asignaciones

## 🏗️ Arquitectura

### Clase `Tarea`
Data class que encapsula toda la información de una tarea individual con principios de POO:

**Propiedades:**
- `id: Int` - Identificador único (inmutable)
- `titulo: String` - Título descriptivo (inmutable)
- `descripcion: String` - Descripción detallada (inmutable)
- `completada: Boolean` - Estado de completitud (privado y mutable, por defecto `false`)
- `prioridad: Prioridad` - Nivel de importancia de la tarea (inmutable)
- `asignadoA: Usuario?` - Usuario responsable de la tarea (privado y mutable, por defecto `null`)

**Métodos de Control de Estado:**
- `marcarComoCompletada()` - Establece la tarea como completada (true)
- `marcarComoPendiente()` - Establece la tarea como pendiente (false)
- `alternarCompletada()` - Cambia el estado entre completada y pendiente
- `estaCompletada(): Boolean` - Getter para consultar el estado actual

**Métodos de Gestión de Asignación:**
- `asignarusuario(usuario: Usuario)` - Asigna un usuario específico como responsable
- `desasignarusuario()` - Desasigna el usuario actual (establece como null)
- `obtenerUsuarioAsignado(): Usuario?` - Getter para consultar el usuario asignado

**Características de Encapsulamiento:**
- La propiedad `completada` es privada para forzar el uso de métodos controlados
- La propiedad `asignadoA` es privada para controlar la asignación de usuarios
- Interfaz pública clara y segura para manipular el estado y asignaciones
- Previene modificaciones accidentales del estado interno y asignaciones

**Otros Métodos:**
- `toString()` - Representación formateada de la tarea con estado, prioridad y asignación

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
// Crear un usuario
val usuario = Usuario(1, "Santiago", "santimulet@gmail.com")

// Crear una nueva tarea con prioridad y usuario asignado
val tarea = Tarea(1, "Estudiar Kotlin", "Repasar conceptos de POO y data classes", 
                  prioridad = Prioridad.ALTA, asignadoA = usuario)

// Mostrar el estado actual
println(tarea) 
/* Salida:
|Pendiente| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA 
asignacion: santimulet@gmail.com
*/

// Verificar estado actual
println("¿Está completada? ${tarea.estaCompletada()}") // false

// Marcar como completada usando método específico
tarea.marcarComoCompletada()
println(tarea) 
/* Salida:
|Completada| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA 
asignacion: santimulet@gmail.com
*/

// Verificar nuevo estado
println("¿Está completada? ${tarea.estaCompletada()}") // true

// Alternar estado (completada → pendiente)
tarea.alternarCompletada()
println(tarea)
/* Salida:
|Pendiente| [1] Estudiar Kotlin - Repasar conceptos de POO y data classes prioridad: ALTA 
asignacion: santimulet@gmail.com
*/
```

### Gestión de Asignación de Usuarios

```kotlin
// Crear usuarios
val usuario1 = Usuario(1, "Santiago", "santimulet@gmail.com")
val usuario2 = Usuario(2, "Mateo", "mateo@gmail.com")

// Crear una tarea sin asignar
val tarea = Tarea(1, "Revisar código", "Revisar implementación de POO", prioridad = Prioridad.MEDIA)

// Verificar si tiene usuario asignado
println("Usuario asignado: ${tarea.obtenerUsuarioAsignado()}") // null

// Asignar usuario a la tarea
tarea.asignarusuario(usuario1)
println("Usuario asignado: ${tarea.obtenerUsuarioAsignado()?.nombre}") // Santiago

// Reasignar a otro usuario
tarea.asignarusuario(usuario2)
println("Usuario asignado: ${tarea.obtenerUsuarioAsignado()?.nombre}") // Mateo

// Desasignar usuario
tarea.desasignarusuario()
println("Usuario asignado: ${tarea.obtenerUsuarioAsignado()}") // null
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

## 🔧 Características Avanzadas

### Encapsulamiento y Seguridad
- **Control de Estado**: Las propiedades `completada` y `asignadoA` están encapsuladas para prevenir modificaciones no controladas
- **Interfaz Pública Segura**: Métodos específicos para cada operación garantizan la integridad de los datos
- **Validaciones**: Control de acceso y validaciones en la asignación de usuarios

### Gestión de Responsabilidades
- **Asignación Flexible**: Las tareas pueden crearse con o sin usuario asignado
- **Reasignación**: Posibilidad de cambiar el responsable de una tarea en cualquier momento
- **Consulta de Asignación**: Métodos getter para verificar quién es el responsable actual

## 📚 Conceptos de Programación Implementados

### ¿Por qué `asignadoA` es de tipo `Usuario?` (nullable)?

La propiedad `asignadoA` está declarada como `Usuario?` (nullable) por las siguientes razones fundamentales:

#### 🎯 **Razones de Diseño:**

1. **Flexibilidad en la Creación**:
   ```kotlin
   // Una tarea puede crearse sin asignar inicialmente
   val tareaNoAsignada = Tarea(1, "Tarea libre", "Sin responsable", Prioridad.BAJA)
   
   // O puede crearse ya asignada
   val tareaAsignada = Tarea(2, "Tarea específica", "Con responsable", Prioridad.ALTA, asignadoA = usuario)
   ```

2. **Estado Real del Mundo**:
   - En la vida real, no todas las tareas tienen un responsable asignado desde el momento de su creación
   - Algunas tareas pueden quedar "abiertas" para que cualquier miembro del equipo las tome
   - Representa mejor la realidad de gestión de proyectos

3. **Workflow de Asignación**:
   - Permite un flujo de trabajo donde las tareas se crean primero y se asignan después
   - Facilita la reasignación de tareas entre usuarios
   - Permite "liberar" tareas cuando un usuario no puede completarlas

#### ⚠️ **Implicaciones del Tipo Nullable:**

1. **Manejo Obligatorio de Null**:
   ```kotlin
   // Siempre debemos verificar si hay un usuario asignado
   val usuario = tarea.obtenerUsuarioAsignado()
   if (usuario != null) {
       println("Asignada a: ${usuario.nombre}")
   } else {
       println("Tarea sin asignar")
   }
   
   // O usando safe call operator
   println("Email: ${tarea.obtenerUsuarioAsignado()?.email ?: "No asignada"}")
   ```

2. **Prevención de NullPointerException**:
   - Kotlin nos obliga a manejar el caso null, evitando errores en tiempo de ejecución
   - El compilador no permite acceso directo sin verificación null

3. **Impacto en el toString()**:
   ```kotlin
   override fun toString(): String {
       val estado = if(completada) "Completada" else "Pendiente"
       val asig = asignadoA?.email  // Safe call - retorna null si asignadoA es null
       return "|$estado| [$id] $titulo - $descripcion prioridad: $prioridad \n" +
               "asignacion: $asig"
   }
   ```

#### 🔒 **Ventajas del Encapsulamiento:**

1. **Control de Acceso**:
   - Al ser `private var`, solo los métodos de la clase pueden modificar la asignación
   - Previene asignaciones accidentales o no autorizadas

2. **Métodos Específicos**:
   - `asignarusuario(usuario: Usuario)` - Asignación controlada
   - `desasignarusuario()` - Desasignación segura
   - `obtenerUsuarioAsignado(): Usuario?` - Consulta segura

3. **Integridad de Datos**:
   - Garantiza que los cambios de asignación pasen por la lógica de negocio
   - Permite agregar validaciones futuras (ej: verificar que el usuario esté activo)

#### 💡 **Alternativas de Diseño Consideradas:**

1. **Usuario no nullable**: Obligaría a asignar siempre un usuario, reduciendo flexibilidad
2. **String en lugar de Usuario**: Perdería la relación fuerte y validaciones
3. **Lista de usuarios**: Complicaría innecesariamente el modelo para casos simples

#### 🎓 **Conceptos de Kotlin Aplicados:**

- **Null Safety**: Sistema de tipos que previene NullPointerException
- **Safe Call Operator (`?.`)**: Permite acceso seguro a propiedades de objetos nullable
- **Elvis Operator (`?:`)**: Proporciona valores por defecto cuando hay null
- **Encapsulamiento**: Principio de POO para controlar el acceso a los datos

## 🚀 Cómo Ejecutar

1. Clona este repositorio
2. Abre el proyecto en tu IDE favorito (IntelliJ IDEA recomendado)
3. Ejecuta la función `main()` en `Main.kt`
