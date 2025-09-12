package org.example

fun main() {
    val gestorDeTareas: GestorDeTareas = GestorDeTareas()

    gestorDeTareas.crearProyecto("proyecto 1", "Descripcion")
    gestorDeTareas.crearProyecto("proyecto 2", "Descripcion")

    gestorDeTareas.crearTarea("tarea para el proyecto 1", "descripcion", Prioridad.ALTA)
    gestorDeTareas.agregarTareaAProyecto(1,1)

    println(gestorDeTareas.mostrarProyectos())

}
