package org.example

fun main() {
    val gestorDeTareas: GestorDeTareas = GestorDeTareas()
    gestorDeTareas.crearProyecto("proyecto1","Descripcion del proyecto 1")
    println(gestorDeTareas.mostrarProyectos())

    println("---------------------------------------")
    val tarea1: Tarea = gestorDeTareas.crearTarea("Tarea 1", "123", Prioridad.ALTA)

    gestorDeTareas.agregarTareaAProyecto(1,1)

    println(gestorDeTareas.mostrarProyectos())

    println("---------------------------------------")
    val usuario1: Usuario = gestorDeTareas.crearUsuario("santi", "santi@gmail.com")
    gestorDeTareas.asignarUsuarioATarea(1,1)
    println(gestorDeTareas.mostrarProyectos())
}
