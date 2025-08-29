package org.example

fun main() {
    val tarea: Tarea = Tarea(1, "Primera tarea", "Esta es la descripción de la primera tarea", prioridad = Prioridad.BAJA  )
    println(tarea)

    val usuario: Usuario = Usuario(1,"santiago", "santimulet@.com")
    println(usuario)
}
    val lista: MutableList<String> = mutableListOf()
    val proyecto: Proyecto = Proyecto(1, "prueba", "pruebita prime", )