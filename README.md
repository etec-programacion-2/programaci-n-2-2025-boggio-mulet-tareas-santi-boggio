# SANTIAGO MULET Y MATEO BOGGIO
****
# Sistema de Gestión de Tareas 📋

Un sistema de gestión de tareas personal desarrollado en Kotlin que te permite organizar tu día a día como una agenda digital con gestión de usuarios, prioridades, proyectos y asignación de responsables mediante una interfaz de línea de comandos (CLI).

## 🚀 Características

- **Interfaz CLI interactiva**: Menú intuitivo para gestionar todas las funcionalidades del sistema
- **Crear tareas**: Añade nuevas tareas con título, descripción detallada y nivel de prioridad
- **Sistema de prioridades**: Clasifica tus tareas por importancia (BAJA, MEDIA, ALTA)
- **Gestión de usuarios**: Registra usuarios con validación de email
- **Asignación de tareas**: Asigna tareas a usuarios específicos como responsables
- **Gestión de proyectos**: Organiza tareas relacionadas bajo proyectos específicos
- **Gestionar estado**: Marca tareas como completadas o pendientes con métodos específicos
- **Identificación única**: Cada tarea, usuario y proyecto tiene un ID único autogenerado
- **Vista clara**: Visualiza el estado de tus tareas, proyectos y asignaciones de forma organizada
- **Buscar tareas por usuario**: Encuentra rápidamente todas las tareas asignadas a un usuario específico
- **Filtrado de tareas**: Obtén tareas pendientes o completadas de un proyecto

### 🔄 Funcionalidades Actuales

- ✅ Interfaz CLI con menú interactivo
- ✅ Creación de tareas con ID autogenerado, título, descripción y prioridad
- ✅ Sistema de prioridades con enum class (BAJA, MEDIA, ALTA)
- ✅ Control de estado con métodos específicos (marcar completada/pendiente)
- ✅ Encapsulamiento de la propiedad `completada` para mayor seguridad
- ✅ Métodos getter para consultar el estado (`estaCompletada()`)
- ✅ **Asignación de usuarios a tareas con control de acceso**
- ✅ **Métodos para asignar, desasignar y consultar usuarios responsables**
- ✅ Gestión de usuarios con validación de email y IDs autogenerados
- ✅ Gestión de proyectos con agrupación de tareas e IDs autogenerados
- ✅ **Clase GestorDeTareas para centralizar la lógica del negocio**
- ✅ **Búsqueda de tareas por usuario asignado**
- ✅ **Agregar y eliminar tareas de proyectos dinámicamente**
- ✅ **Obtener tareas pendientes y completadas de un proyecto**
- ✅ Visualización formateada del estado de las tareas, usuarios, proyectos y asignaciones

## 🏗️ Arquitectura

### Clase `GestorDeTareas`
Clase principal que centraliza toda la lógica de negocio del sistema. Implementa el patrón de diseño de responsabilidad única, separando la gestión de datos del punto de entrada de la aplicación.

**Propiedades privadas:**
- `allProyectos: MutableList<Proyecto>` - Lista de todos los proyectos
- `allTareas: MutableList<Tarea>` - Lista de todas las tareas
- `allUsers: MutableList<Usuario>` - Lista de todos los usuarios
- `idProyectoActual: Int` - Contador autoincremental para IDs de proyectos
- `idUsuarioActual: Int` - Contador autoincremental para IDs de usuarios
- `idTareaActual: Int` - Contador autoincremental para IDs de tareas

**Métodos principales:**
- `crearProyecto(nombrePy, descripcionPy): Proyecto` - Crea un nuevo proyecto con ID autogenerado
- `crearUsuario(username, email): Usuario` - Crea un nuevo usuario con ID autogenerado
- `crearTarea(nombreTarea, descripcionTarea, prioridadTarea): Tarea` - Crea una nueva tarea con ID autogenerado
- `agregarTareaAProyecto(idTarea, idProyecto)` - Asocia una tarea existente a un proyecto
- `asignarUsuarioATarea(idTarea, idUsuario)` - Asigna un usuario a una tarea específica
- `buscarTareasPorUsuario(idUsuario): List<Tarea>` - Busca todas las tareas de un usuario
- `mostrarProyectos(): MutableList<Proyecto>` - Retorna todos los proyectos
- `cambiarcompletado(idTarea)` - Alterna el estado de completitud de una tarea

**Ventajas del diseño:**
- Separación de responsabilidades (no mezcla lógica con presentación)
- Fácil de testear unitariamente
- Reutilizable en diferentes interfaces (CLI, GUI, API)
- Escalable y mantenible

### Clase `InterfazCLI`
Clase que maneja toda la interacción con el usuario a través de la línea de comandos.

**Propiedades:**
- `gestor: GestorDeTareas` - Instancia del gestor de tareas
- `scanner: Scanner` - Para leer entrada del usuario

**Métodos de interfaz:**
- `mostrarMenu()` - Muestra el menú principal con todas las opciones
- `crearproyecto()` - Interfaz para crear un nuevo proyecto
- `crearusuario()` - Interfaz para crear un nuevo usuario
- `creartarea()` - Interfaz para crear una nueva tarea
- `agregartareaaproyecto()` - Interfaz para agregar tarea a proyecto
- `agregarusuarioatarea()` - Interfaz para asignar usuario a tarea
- `buscartareaporusuario()` - Interfaz para buscar tareas por usuario
- `mostrarlosproyecto()` - Interfaz para mostrar todos los proyectos
- `cambiarestado()` - Interfaz para cambiar estado de una tarea

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
- `getAsignacion(): Usuario?` - Getter alternativo para obtener el usuario asignado

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
- `listadetareas: MutableList<Tarea>` - Lista de tareas asociadas al proyecto (privado y mutable)

**Características:**
- Permite organizar tareas bajo proyectos específicos
- Facilita la visualización y gestión de tareas relacionadas
- Mantiene una lista mutable de objetos Tarea para gestión dinámica
- Encapsulamiento de la lista de tareas para control de acceso

**Métodos:**
- `agregarTarea(tarea: Tarea)` - Agrega una tarea al proyecto
- `eliminarTarea(idTarea: Int)` - Elimina una tarea por su ID
- `obtenerTareasPendientes(): List<Tarea>` - Retorna solo las tareas no completadas
- `obtenerTareasCompletadas(): List<Tarea>` - Retorna solo las tareas completadas
- `tareasBeauty(): String` - Formatea las tareas para visualización
- `toString()` - Representación formateada del proyecto con todas sus tareas

## 🎯 Ejemplo de Uso

### Uso del Sistema con CLI

```kotlin
fun main() {
    val interfas = InterfazCLI()
    val scanner = Scanner(System.`in`)
    var opcion: Int

    do {
        interfas.mostrarMenu()
        opcion = scanner.nextInt()

        when (opcion) {
            1 -> interfas.crearproyecto()
            2 -> interfas.crearusuario()
            3 -> interfas.creartarea()
            4 -> interfas.agregartareaaproyecto()
            5 -> interfas.agregarusuarioatarea()
            6 -> interfas.buscartareaporusuario()
            7 -> interfas.mostrarlosproyecto()
            8 -> interfas.cambiarestado()
            0 -> println("¡Hasta luego!")
            else -> println("Opción inválida. Intente de nuevo.")
        }
    } while (opcion != 0)
}
```

### Ejemplo de Flujo Interactivo

```
=== GESTOR DE TAREAS ===
1. Crear proyecto
2. Crear usuario
3. Crear tarea
4. Agregar Tarea A Proyecto
5. Asignar Usuario A Tarea
6. Buscar Tareas Por Usuario
7. Mostrar Proyectos
8. Cambiar el estado de la tarea (pendiente - completado)
0. Salir
Ingrese su opción: 2

ingrese el nombre del usuario:
Santiago
ingrese un mail para su usuario:
santimulet@gmail.com
[1] Santiago -> santimulet@gmail.com

Ingrese su opción: 3

ingrese el nombre del tarea:
Estudiar Kotlin
ingrese una descripcion:
Repasar conceptos de POO
ingrese la prioridad de la tarea (baja, media, alta)
alta
|Pendiente| [1] Estudiar Kotlin - Repasar conceptos de POO prioridad: ALTA 
usuario respondable: null
```

### Creación Programática con GestorDeTareas

```kotlin
// Crear instancia del gestor
val gestor = GestorDeTareas()

// Crear usuarios (IDs generados automáticamente)
val usuario1 = gestor.crearUsuario("Santiago", "santimulet@gmail.com")
val usuario2 = gestor.crearUsuario("Mateo", "mateo@gmail.com")

// Crear proyecto (ID generado automáticamente)
val proyecto = gestor.crearProyecto("Proyecto Kotlin", "Desarrollo del sistema de tareas")

// Crear tareas (IDs generados automáticamente)
val tarea1 = gestor.crearTarea("Estudiar POO", "Conceptos de encapsulamiento", Prioridad.ALTA)
val tarea2 = gestor.crearTarea("Implementar CLI", "Crear interfaz de usuario", Prioridad.MEDIA)

// Agregar tareas al proyecto
gestor.agregarTareaAProyecto(tarea1.id, proyecto.id)
gestor.agregarTareaAProyecto(tarea2.id, proyecto.id)

// Asignar usuarios a tareas
gestor.asignarUsuarioATarea(tarea1.id, usuario1.id)
gestor.asignarUsuarioATarea(tarea2.id, usuario2.id)

// Buscar tareas de un usuario
val tareasDeUsuario1 = gestor.buscarTareasPorUsuario(usuario1.id)
println("Tareas de ${usuario1.nombre}:")
tareasDeUsuario1.forEach { println(it) }

// Cambiar estado de una tarea
gestor.cambiarcompletado(tarea1.id)

// Mostrar todos los proyectos
val proyectos = gestor.mostrarProyectos()
proyectos.forEach { println(it) }
```

### Gestión de Proyectos

```kotlin
val gestor = GestorDeTareas()

// Crear proyecto
val proyecto = gestor.crearProyecto("Mi Proyecto", "Descripción del proyecto")

// Crear tareas
val tarea1 = gestor.crearTarea("Tarea 1", "Primera tarea", Prioridad.ALTA)
val tarea2 = gestor.crearTarea("Tarea 2", "Segunda tarea", Prioridad.BAJA)

// Agregar tareas al proyecto
gestor.agregarTareaAProyecto(tarea1.id, proyecto.id)
gestor.agregarTareaAProyecto(tarea2.id, proyecto.id)

// Marcar una tarea como completada
gestor.cambiarcompletado(tarea1.id)

// Obtener tareas pendientes del proyecto
val pendientes = proyecto.obtenerTareasPendientes()
println("Tareas pendientes: ${pendientes.size}")

// Obtener tareas completadas del proyecto
val completadas = proyecto.obtenerTareasCompletadas()
println("Tareas completadas: ${completadas.size}")

// Eliminar una tarea del proyecto
proyecto.eliminarTarea(tarea2.id)
```

## 🔧 Características Avanzadas

### Separación de Responsabilidades
- **GestorDeTareas**: Maneja toda la lógica de negocio y datos
- **InterfazCLI**: Maneja solo la presentación e interacción con el usuario
- **Main**: Solo punto de entrada, delega todo a InterfazCLI
- Facilita testing, mantenimiento y escalabilidad

### Generación Automática de IDs
- Sistema de contadores autoincrementales para cada tipo de entidad
- Garantiza IDs únicos sin intervención del usuario
- Simplifica la creación de objetos

### Encapsulamiento y Seguridad
- **Control de Estado**: Las propiedades `completada` y `asignadoA` están encapsuladas para prevenir modificaciones no controladas
- **Listas Privadas**: Las colecciones en GestorDeTareas son privadas
- **Interfaz Pública Segura**: Métodos específicos para cada operación garantizan la integridad de los datos
- **Validaciones**: Control de acceso y validaciones en la asignación de usuarios

### Gestión de Responsabilidades
- **Asignación Flexible**: Las tareas pueden crearse con o sin usuario asignado
- **Reasignación**: Posibilidad de cambiar el responsable de una tarea en cualquier momento
- **Consulta de Asignación**: Métodos getter para verificar quién es el responsable actual
- **Búsqueda Eficiente**: Método dedicado para encontrar todas las tareas de un usuario

### Gestión Dinámica de Proyectos
- **Agregar Tareas**: Agregar tareas existentes a proyectos dinámicamente
- **Eliminar Tareas**: Remover tareas de proyectos con validación
- **Filtrado**: Obtener listas de tareas pendientes o completadas
- **Visualización**: Formateo especial para mostrar tareas de forma legible

## 📚 Conceptos de Programación Implementados

### ¿Por qué usar GestorDeTareas en lugar de poner todo en main()?

Separar la lógica del main() es una buena práctica porque:

1. **RESPONSABILIDAD ÚNICA**: main() solo debe ser el punto de entrada, no manejar toda la lógica del negocio.

2. **REUTILIZACIÓN**: GestorDeTareas puede usarse en tests, otras clases, interfaces gráficas, APIs web, etc.

3. **TESTING**: Podemos hacer unit tests de cada método por separado. Con todo en main() sería imposible testear funcionalidades específicas.

4. **MANTENIMIENTO**: Es más fácil encontrar y modificar código cuando está organizado en clases con propósitos específicos.

5. **ESCALABILIDAD**: Permite agregar funcionalidades sin que main() se vuelva un archivo gigante e inmanejable.

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
               "usuario respondable: $asig"
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
   - `getAsignacion(): Usuario?` - Consulta alternativa

3. **Integridad de Datos**:
   - Garantiza que los cambios de asignación pasen por la lógica de negocio
   - Permite agregar validaciones futuras (ej: verificar que el usuario esté activo)

## 🚀 Cómo Ejecutar

1. Clona este repositorio
2. Abre el proyecto en tu IDE favorito (IntelliJ IDEA recomendado)
3. Ejecuta la función `main()` en `Main.kt`
4. Interactúa con el menú CLI para gestionar tus tareas, proyectos y usuarios

## 📋 Menú de Opciones CLI

1. **Crear proyecto** - Crea un nuevo proyecto con nombre y descripción
2. **Crear usuario** - Registra un nuevo usuario con email validado
3. **Crear tarea** - Crea una nueva tarea con prioridad
4. **Agregar Tarea A Proyecto** - Asocia una tarea existente a un proyecto
5. **Asignar Usuario A Tarea** - Asigna un usuario como responsable de una tarea
6. **Buscar Tareas Por Usuario** - Muestra todas las tareas de un usuario
7. **Mostrar Proyectos** - Lista todos los proyectos con sus tareas
8. **Cambiar el estado de la tarea** - Alterna entre pendiente y completado
0. **Salir** - Cierra la aplicación
