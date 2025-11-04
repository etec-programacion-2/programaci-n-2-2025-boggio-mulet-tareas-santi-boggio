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

## 🚀 Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

| Herramienta | Versión recomendada |
|--------------|--------------------|
| **JDK** | 17 o superior |
| **Gradle** | No necesario instalar manualmente (usa el wrapper incluido) |
| **Git** | 2.30 o superior |
| **Sistema operativo** | Windows, Linux o macOS |

> ⚠️ El proyecto usa `Gradle Wrapper`, por lo que no es necesario tener Gradle instalado globalmente.

---

## 📦 Clonar o descargar el proyecto

Puedes clonar el repositorio desde GitHub o descargarlo como archivo ZIP.

### 🔹 Clonar con Git

```bash
git clone https://github.com/santi/programacion-2-2025-boggio-mulet-tareas-santi-boggio.git
cd programacion-2-2025-boggio-mulet-tareas-santi-boggio
```
### 🔹 Compilar y ejecutar la aplicación

## Paso 1: Compilar

```bash
./gradlew build
```

## Paso 2: Ejecutar

```bash
./gradlew run
```

### Configuración adicional

JVM: Usa Java 17 (verificado en el archivo build.gradle.kts con jvmToolchain(17)).

Framework: El proyecto utiliza http4k
