package org.example

/**
 * Data class que representa una tarea individual en el sistema de gestión de tareas.
 *
 * @property id | Identificador único de la tarea (inmutable)
 * @property titulo | Título descriptivo de la tarea (inmutable)
 * @property descripcion | Descripción detallada de la tarea (inmutable)
 * @property completada | Estado (True or False) de completitud de la tarea (mutable, por defecto false)
 */

data class Tarea (
    val id: Int,
    val titulo: String,
    val descripcion: String,
    var completada: Boolean = false
){
    /**
    * Función que alterna la completitud de la tarea, por ejemplo: si la tarea no está completada (completada = false), el valor va a cambiar a completada (completada = true)
    */
    fun alternarCompletada(){
        completada = !completada
    }

    /**
     * Me permite mostrar el estado de la tarea de una forma más bonita
     */
    override fun toString(): String {
        val estado = if(completada) "Completada" else "Pendiente"
        return "|$estado| [$id] $titulo - $descripcion"
    }

}