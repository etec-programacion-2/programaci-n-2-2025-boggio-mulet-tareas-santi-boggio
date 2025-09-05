package org.example

fun main() {
    val tarea: Tarea = Tarea(1, "Primera tarea", "Esta es la descripción de la primera tarea", prioridad = Prioridad.BAJA)
    println("Tarea creada:")
    println(tarea)
    println("¿Está completada? ${tarea.estaCompletada()}")
    println()

    // Demostrar métodos de completitud
    println("=== Demostrando métodos de completitud ===")

    // Marcar como completada
    tarea.marcarComoCompletada()
    println("Después de marcarComoCompletada():")
    println(tarea)
    println("¿Está completada? ${tarea.estaCompletada()}")
    println()

    val usuario: Usuario = Usuario(1,"santiago", "santimulet@gmail.com")
    println("Usuario creado:")
    println(usuario)

    //=======Prueba de eliminar Tareas======
    val tarea1: Tarea = Tarea(1,"TAREA 1", "esta es la tarea uno", prioridad = Prioridad.BAJA)
    val tarea2: Tarea = Tarea(2,"TAREA 2", "esta es la tarea dos", prioridad = Prioridad.MEDIA)

    val listaTareas = mutableListOf<Tarea>(tarea1,tarea2)

    val proyecto: Proyecto = Proyecto(1, "Primer proyecto", "Este es el primer proyecto", listaTareas)

    proyecto.eliminarTarea(2)

}
