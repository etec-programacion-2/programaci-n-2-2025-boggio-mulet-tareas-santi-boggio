package org.example

import org.http4k.core.*
import org.http4k.core.Method.*
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.format.Jackson.auto
import java.io.File

// Data classes para las peticiones
data class ProyectoRequest(val nombre: String, val descripcion: String)
data class TareaRequest(val nombre: String, val descripcion: String, val prioridad: String)
data class UsuarioRequest(val username: String, val email: String)
data class AsignarTareaProyectoRequest(val idTarea: Int, val idProyecto: Int)
data class AsignarUsuarioTareaRequest(val idTarea: Int, val idUsuario: Int)
data class CompletarTareaRequest(val idTarea: Int)

fun main() {
    // Lenses para extraer/enviar JSON
    val proyectoRequestLens = Body.auto<ProyectoRequest>().toLens()
    val proyectosLens = Body.auto<List<Proyecto>>().toLens()
    val proyectoLens = Body.auto<Proyecto>().toLens()

    val tareaRequestLens = Body.auto<TareaRequest>().toLens()
    val tareasLens = Body.auto<List<Tarea>>().toLens()
    val tareaLens = Body.auto<Tarea>().toLens()

    val usuarioRequestLens = Body.auto<UsuarioRequest>().toLens()
    val usuariosLens = Body.auto<List<Usuario>>().toLens()
    val usuarioLens = Body.auto<Usuario>().toLens()

    val asignarTareaProyectoLens = Body.auto<AsignarTareaProyectoRequest>().toLens()
    val asignarUsuarioTareaLens = Body.auto<AsignarUsuarioTareaRequest>().toLens()
    val completarTareaLens = Body.auto<CompletarTareaRequest>().toLens()

    val gestorDeTareas = GestorDeTareas()

    val app = routes(
        // Servir el archivo HTML
        "/" bind GET to { request: Request ->
            // MODIFICACIÓN: Cambiamos la ruta relativa
            val htmlFile = File("index.html") // Antes: File("../../index.html")
            if (htmlFile.exists()) {
                Response(OK)
                    .header("Content-Type", "text/html; charset=UTF-8")
                    .body(htmlFile.readText())
            } else {
                Response(Status.NOT_FOUND).body("index.html no encontrado")
            }
        },

        // ============ PROYECTOS ============
        // GET: Obtener todos los proyectos
        "/proyectos" bind GET to { request: Request ->
            val proyectos = gestorDeTareas.mostrarProyectos()
            val response = proyectosLens(proyectos, Response(OK))
            response.header("Content-Type", "application/json")
        },

        // POST: Crear un nuevo proyecto
        "/proyectos" bind POST to { request: Request ->
            try {
                val proyectoRequest = proyectoRequestLens(request)
                val proyecto = gestorDeTareas.crearProyecto(proyectoRequest.nombre, proyectoRequest.descripcion)
                val response = proyectoLens(proyecto, Response(OK))
                response.header("Content-Type", "application/json")
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al crear proyecto: ${e.message}")
            }
        },

        // ============ TAREAS ============
        // GET: Obtener todas las tareas
        "/tareas" bind GET to { request: Request ->
            val tareas = gestorDeTareas.mostrarTareas()
            val response = tareasLens(tareas, Response(OK))
            response.header("Content-Type", "application/json")
        },

        // POST: Crear una nueva tarea
        "/tareas" bind POST to { request: Request ->
            try {
                val tareaRequest = tareaRequestLens(request)
                val prioridad = Prioridad.valueOf(tareaRequest.prioridad)
                val tarea = gestorDeTareas.crearTarea(tareaRequest.nombre, tareaRequest.descripcion, prioridad)
                val response = tareaLens(tarea, Response(OK))
                response.header("Content-Type", "application/json")
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al crear tarea: ${e.message}")
            }
        },

        // GET: Buscar tareas por usuario
        "/tareas/usuario/{idUsuario}" bind GET to { request: Request ->
            try {
                val idUsuario = request.uri.path.split("/").lastOrNull()?.toIntOrNull()
                if (idUsuario == null) {
                    Response(Status.BAD_REQUEST).body("ID de usuario inválido")
                } else {
                    val tareas = gestorDeTareas.buscarTareasPorUsuario(idUsuario)
                    val response = tareasLens(tareas, Response(OK))
                    response.header("Content-Type", "application/json")
                }
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al buscar tareas: ${e.message}")
            }
        },

        // POST: Cambiar estado de completado de tarea
        "/tareas/completar" bind POST to { request: Request ->
            try {
                val completarRequest = completarTareaLens(request)
                gestorDeTareas.cambiarcompletado(completarRequest.idTarea)
                Response(OK).body("{\"mensaje\": \"Estado de tarea actualizado\"}")
                    .header("Content-Type", "application/json")
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al actualizar estado: ${e.message}")
            }
        },

        // ============ USUARIOS ============
        // GET: Obtener todos los usuarios
        "/usuarios" bind GET to { request: Request ->
            val usuarios = gestorDeTareas.mostrarUsuarios()
            val response = usuariosLens(usuarios, Response(OK))
            response.header("Content-Type", "application/json")
        },

        // POST: Crear un nuevo usuario
        "/usuarios" bind POST to { request: Request ->
            try {
                val usuarioRequest = usuarioRequestLens(request)
                val usuario = gestorDeTareas.crearUsuario(usuarioRequest.username, usuarioRequest.email)
                val response = usuarioLens(usuario, Response(OK))
                response.header("Content-Type", "application/json")
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al crear usuario: ${e.message}")
            }
        },

        // ============ ASIGNACIONES ============
        // POST: Agregar tarea a proyecto
        "/asignaciones/tarea-proyecto" bind POST to { request: Request ->
            try {
                val asignacion = asignarTareaProyectoLens(request)
                gestorDeTareas.agregarTareaAProyecto(asignacion.idTarea, asignacion.idProyecto)
                Response(OK).body("{\"mensaje\": \"Tarea agregada al proyecto exitosamente\"}")
                    .header("Content-Type", "application/json")
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al asignar tarea a proyecto: ${e.message}")
            }
        },

        // POST: Asignar usuario a tarea
        "/asignaciones/usuario-tarea" bind POST to { request: Request ->
            try {
                val asignacion = asignarUsuarioTareaLens(request)
                gestorDeTareas.asignarUsuarioATarea(asignacion.idTarea, asignacion.idUsuario)
                Response(OK).body("{\"mensaje\": \"Usuario asignado a tarea exitosamente\"}")
                    .header("Content-Type", "application/json")
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al asignar usuario a tarea: ${e.message}")
            }
        }
    )

    val server = app.asServer(Jetty(8080)).start()
    println("🚀 Server started on http://localhost:8080")
    println("📋 Gestor de Tareas - Sistema completo funcionando")
    server.block()
}