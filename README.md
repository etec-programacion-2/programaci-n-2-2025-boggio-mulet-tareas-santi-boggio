# SANTIAGO MULET Y MATEO BOGGIO

---

# Sistema de Gestión de Tareas 📋

Un sistema completo de gestión de tareas desarrollado en Kotlin con backend http4k y frontend HTML/JavaScript. Permite organizar proyectos, gestionar tareas con prioridades, administrar usuarios y asignar responsables a través de una interfaz web moderna e intuitiva.

## 🚀 Características

### Gestión Completa
- **Proyectos**: Crea y organiza proyectos con descripción detallada
- **Tareas**: Sistema de tareas con prioridades (BAJA, MEDIA, ALTA)
- **Usuarios**: Registro de usuarios con validación de email
- **Asignaciones**: Vincula tareas con proyectos y usuarios con tareas
- **Estados**: Control de tareas completadas/pendientes
- **Interfaz Web**: UI moderna y responsive con diseño atractivo

### Tecnologías Utilizadas
- **Backend**: Kotlin + http4k framework
- **Servidor**: Jetty embedded server
- **Serialización**: Jackson para manejo de JSON
- **Frontend**: HTML5, CSS3, JavaScript vanilla
- **Build Tool**: Gradle 8.13 con Kotlin DSL

---

## 📋 Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

| Herramienta | Versión Mínima | Versión Recomendada | Verificación |
|-------------|----------------|---------------------|--------------|
| **JDK** | 17 | 21 | `java -version` |
| **Git** | 2.30+ | Última estable | `git --version` |

> ⚠️ **IMPORTANTE**: El proyecto usa **Gradle Wrapper** incluido, por lo que NO necesitas instalar Gradle manualmente.

### Instalación de JDK (si no lo tienes)

#### Windows
1. Descarga OpenJDK desde [Adoptium](https://adoptium.net/) o [Oracle](https://www.oracle.com/java/technologies/downloads/)
2. Ejecuta el instalador
3. Verifica la instalación abriendo CMD y ejecutando: `java -version`

#### macOS
```bash
# Usando Homebrew
brew install openjdk@17

# Configurar JAVA_HOME
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.zshrc
source ~/.zshrc
```

#### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-17-jdk

# Verificar
java -version
```

---

## 📦 Descargar el Proyecto

### Opción 1: Clonar con Git (Recomendado)

```bash
# Clonar el repositorio
git clone https://github.com/etec-programacion-2/programaci-n-2-2025-boggio-mulet-tareas-santi-boggio.git

# Entrar al directorio del proyecto
cd programaci-n-2-2025-boggio-mulet-tareas-santi-boggio
```

### Opción 2: Descargar ZIP

1. Ve a la página del repositorio en GitHub
2. Click en el botón verde **"Code"**
3. Selecciona **"Download ZIP"**
4. Extrae el archivo ZIP en tu ubicación preferida
5. Abre una terminal/CMD en esa carpeta

---

## 🔧 Configuración del Proyecto

### Estructura de Archivos

```
proyectoFeria/
├── src/
│   └── main/
│       └── kotlin/
│           ├── GestorDeTareas.kt    # Lógica de negocio
│           ├── Main.kt              # Servidor HTTP + API REST
│           ├── Proyecto.kt          # Modelo de Proyecto
│           ├── Tarea.kt             # Modelo de Tarea
│           ├── Usuario.kt           # Modelo de Usuario
│           └── Prioridad.kt         # Enum de prioridades
├── index.html                       # Interfaz web del usuario
├── build.gradle.kts                 # Configuración de Gradle
├── gradlew                          # Gradle Wrapper (Unix/Mac)
├── gradlew.bat                      # Gradle Wrapper (Windows)
└── README.md                        # Este archivo
```

### Verificar Permisos (Linux/macOS)

```bash
# Dar permisos de ejecución al Gradle Wrapper
chmod +x gradlew
```

---

## ▶️ Compilar y Ejecutar la Aplicación

### IMPORTANTE: Configuración Previa

⚠️ **Antes de ejecutar**, debes modificar el archivo `build.gradle.kts` para agregar el plugin de aplicación.

Abre `build.gradle.kts` y modifica la sección de `plugins` para que quede así:

```kotlin
plugins {
    kotlin("jvm") version "2.2.0"
    application  // ← AGREGAR ESTA LÍNEA
}
```

Luego, **agrega al final del archivo** (después de la sección `kotlin`):

```kotlin
application {
    mainClass.set("org.example.MainKt")
}
```

El archivo completo debería verse así:

```kotlin
val http4kVersion = "5.13.4.0"
plugins {
    kotlin("jvm") version "2.2.0"
    application  // ← AGREGADO
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.http4k:http4k-core:$http4kVersion")
    implementation("org.http4k:http4k-server-jetty:$http4kVersion")
    implementation("org.http4k:http4k-format-jackson:$http4kVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("org.example.MainKt")
}
```

### Paso 1: Compilar el Proyecto

Abre una terminal en la raíz del proyecto y ejecuta:

#### Windows (CMD/PowerShell)
```cmd
gradlew.bat build
```

#### Linux/macOS
```bash
./gradlew build
```

**Qué hace este comando:**
- Descarga las dependencias necesarias (http4k, Jackson)
- Compila el código Kotlin
- Ejecuta tests (si existen)
- Genera el archivo JAR ejecutable en `build/libs/`

**Tiempo estimado**: 30 segundos - 2 minutos (primera vez puede tardar más por descarga de dependencias)

### Paso 2: Ejecutar la Aplicación

Ahora sí puedes ejecutar:

#### Windows
```cmd
gradlew.bat run
```

#### Linux/macOS
```bash
./gradlew run
```

**Salida esperada en consola:**
```
🚀 Server started on http://localhost:8080
📋 Gestor de Tareas - Sistema completo funcionando
```

> ✅ Si ves este mensaje, el servidor está corriendo correctamente.

---

## 🌐 Acceder a la Aplicación

1. **Abre tu navegador web** (Chrome, Firefox, Edge, Safari)
2. **Navega a**: `http://localhost:8080`
3. La interfaz web se cargará automáticamente

### Interfaz Principal

La aplicación tiene 4 secciones principales:

- **📁 Proyectos**: Crear y visualizar proyectos con sus tareas
- **✅ Tareas**: Gestionar tareas individuales con prioridades
- **👥 Usuarios**: Administrar usuarios del sistema
- **🔗 Asignaciones**: Vincular tareas con proyectos y usuarios

---

## 🎯 Guía de Uso Rápido

### 1. Crear un Usuario
1. Ir a la sección **"Usuarios"**
2. Completar nombre y email
3. Click en **"Crear Usuario"**

### 2. Crear una Tarea
1. Ir a la sección **"Tareas"**
2. Completar título, descripción y seleccionar prioridad
3. Click en **"Crear Tarea"**

### 3. Crear un Proyecto
1. Ir a la sección **"Proyectos"**
2. Completar nombre y descripción
3. Click en **"Crear Proyecto"**

### 4. Asignar Tarea a Proyecto
1. Ir a la sección **"Asignaciones"**
2. Seleccionar proyecto y tarea
3. Click en **"Agregar"**

### 5. Asignar Usuario a Tarea
1. En la sección **"Asignaciones"**
2. Seleccionar tarea y usuario
3. Click en **"Asignar"**

---

## 🔧 API REST - Endpoints Disponibles

El backend expone los siguientes endpoints:

### Proyectos
- `GET /proyectos` - Listar todos los proyectos
- `POST /proyectos` - Crear nuevo proyecto
  ```json
  {
    "nombre": "Proyecto Ejemplo",
    "descripcion": "Descripción del proyecto"
  }
  ```

### Tareas
- `GET /tareas` - Listar todas las tareas
- `POST /tareas` - Crear nueva tarea
  ```json
  {
    "nombre": "Tarea Ejemplo",
    "descripcion": "Descripción de la tarea",
    "prioridad": "ALTA"
  }
  ```
- `GET /tareas/usuario/{idUsuario}` - Buscar tareas por usuario
- `POST /tareas/completar` - Alternar estado de tarea
  ```json
  {
    "idTarea": 1
  }
  ```

### Usuarios
- `GET /usuarios` - Listar todos los usuarios
- `POST /usuarios` - Crear nuevo usuario
  ```json
  {
    "username": "Juan Pérez",
    "email": "juan@example.com"
  }
  ```

### Asignaciones
- `POST /asignaciones/tarea-proyecto` - Agregar tarea a proyecto
  ```json
  {
    "idTarea": 1,
    "idProyecto": 1
  }
  ```
- `POST /asignaciones/usuario-tarea` - Asignar usuario a tarea
  ```json
  {
    "idTarea": 1,
    "idUsuario": 1
  }
  ```

---

## ❌ Detener la Aplicación

Para detener el servidor:

- **Windows/Linux/macOS**: Presiona `Ctrl + C` en la terminal donde está corriendo
- El servidor se detendrá y liberará el puerto 8080

---

## 🐛 Solución de Problemas Comunes

### Error: "Task 'run' not found"
**Problema**: El archivo `build.gradle.kts` no tiene configurado el plugin `application`

**Solución**: Sigue las instrucciones en la sección "Configuración Previa" para agregar el plugin al archivo `build.gradle.kts`. O usa el método alternativo con el comando `java -cp` directo.

### Error: "java: command not found"
**Problema**: Java no está instalado o no está en el PATH

**Solución**:
1. Instala JDK 17 o superior (ver sección de requisitos previos)
2. Verifica con `java -version`
3. Si está instalado pero no reconocido, agrega Java al PATH del sistema

### Error: "Address already in use" (puerto 8080 ocupado)
**Problema**: Otra aplicación está usando el puerto 8080

**Solución**:
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID [número_proceso] /F

# Linux/macOS
lsof -ti:8080 | xargs kill -9
```

O modifica el puerto en `Main.kt`:
```kotlin
val server = app.asServer(Jetty(8081)).start() // Cambiar 8080 por 8081
```

### Error: "Permission denied" en Linux/macOS
**Problema**: El archivo `gradlew` no tiene permisos de ejecución

**Solución**:
```bash
chmod +x gradlew
```

### Error: "index.html not found"
**Problema**: El archivo HTML no está en la ubicación correcta

**Solución**:
1. Verifica que `index.html` esté en la raíz del proyecto (mismo nivel que `build.gradle.kts`)
2. Si el error persiste, en `Main.kt` verifica la ruta: `File("index.html")`

### Error de compilación: "Unresolved reference"
**Problema**: Dependencias no descargadas correctamente

**Solución**:
```bash
# Limpiar y reconstruir
./gradlew clean build --refresh-dependencies
```

---

## 📊 Tecnologías y Dependencias

### Backend
- **Kotlin**: 2.2.0
- **http4k-core**: 5.13.4.0 (Manejo de HTTP)
- **http4k-server-jetty**: 5.13.4.0 (Servidor embebido)
- **http4k-format-jackson**: 5.13.4.0 (Serialización JSON)

### Frontend
- HTML5, CSS3, JavaScript ES6+
- Sin frameworks (vanilla JS)
- Diseño responsive con Flexbox/Grid

### Build Tool
- Gradle 8.13 con Kotlin DSL
- JVM Toolchain: Java 17

---

## 📝 Notas Adicionales

### Persistencia de Datos
⚠️ **Los datos se almacenan en memoria**: Al detener el servidor, toda la información (proyectos, tareas, usuarios) se pierde. Es normal para esta versión del proyecto.

### CORS y Seguridad
- El servidor actualmente no implementa CORS
- Adecuado para desarrollo local
- No apto para producción sin medidas de seguridad adicionales

### Extensiones Futuras
Posibles mejoras al proyecto:
- Persistencia en base de datos (PostgreSQL, MongoDB)
- Autenticación y autorización de usuarios
- Fechas de vencimiento para tareas
- Notificaciones y recordatorios
- Exportación de datos (CSV, PDF)

---

## 👥 Autores

**Santiago Mulet y Mateo Boggio**

## 📄 Licencia

Proyecto educativo desarrollado para la materia Programación 2.

---

## 🆘 Soporte

Si encuentras problemas no listados en esta documentación:

1. Verifica que todos los requisitos previos estén cumplidos
2. Asegúrate de estar en el directorio correcto del proyecto
3. Revisa los logs de error en la consola para detalles específicos
4. Intenta limpiar y reconstruir: `./gradlew clean build`

---

**¡Listo para usar! 🎉**

Una vez completados todos los pasos, tu Sistema de Gestión de Tareas estará funcionando en `http://localhost:8080`
