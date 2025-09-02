package org.example

fun main() {
    val tarea: Tarea = Tarea(1, "Primera tarea", "Esta es la descripción de la primera tarea", prioridad = Prioridad.BAJA  )
    println(tarea)

    val usuario: Usuario = Usuario(1,"santiago", "santimulet@.com")
    println(usuario)

    val lista: List<String> = listOf<String>(tarea.titulo)
    val proyecto: Proyecto = Proyecto(1, "prueba", "pruebita prime", lista)
    print(proyecto)
}
