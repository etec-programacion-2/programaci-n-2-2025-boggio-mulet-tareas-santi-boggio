package org.example

fun main() {
    val tarea: Tarea = Tarea(1, "Primera tarea", "Esta es la descripción de la primera tarea", prioridad = Prioridad.BAJA  )
    println(tarea)

    val usuario: Usuario = Usuario(1,"santiago", "santimulet")
    println(usuario)
}