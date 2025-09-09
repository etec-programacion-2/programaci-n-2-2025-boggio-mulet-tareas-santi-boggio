package org.example

fun main() {
    val usuario: Usuario = Usuario(1,"santiago", "santimulet@gmail.com")
    println("Usuario creado:")
    println(usuario)

    val tarea: Tarea = Tarea(1, "Primera tarea", "Esta es la descripción de la primera tarea", prioridad = Prioridad.BAJA, asignadoA = usuario )
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



    //=======Prueba de eliminar Tareas======
    val tarea1: Tarea = Tarea(1,"TAREA 1", "esta es la tarea uno", prioridad = Prioridad.BAJA, completada = true)
    val tarea2: Tarea = Tarea(2,"TAREA 2", "esta es la tarea dos", prioridad = Prioridad.MEDIA)
    val tarea3: Tarea = Tarea(3,"TAREA 3", "esta es la tarea tres", prioridad = Prioridad.ALTA)

    val listaTareas = mutableListOf<Tarea>(tarea1,tarea2,tarea3)

    val proyecto: Proyecto = Proyecto(1, "Primer proyecto", "Este es el primer proyecto", listaTareas)

    println("--------------------------------")
    println(proyecto)
    println("--------------------------------")
    println(proyecto.eliminarTarea(2))
    println("--------------------------------")
    println("Tareas pendientes:")
    println(proyecto.obtenerTareasPendientes())
    println("--------------------------------")
    println("Tareas completadas:")
    println(proyecto.obtenerTareasCompletadas())

}
