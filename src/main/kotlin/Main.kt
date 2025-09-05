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

    val lista: List<String> = listOf<String>(tarea.titulo)
    val proyecto: Proyecto = Proyecto(1, "prueba", "pruebita prime", lista)
    print(proyecto)
}
