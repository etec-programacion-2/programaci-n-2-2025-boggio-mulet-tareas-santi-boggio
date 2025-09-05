package org.example

/**
 * Data class que representa una tarea individual en el sistema de gestión de tareas.
 *
 * @property id | Identificador único de la tarea (inmutable)
 * @property titulo | Título descriptivo de la tarea (inmutable)
 * @property descripcion | Descripción detallada de la tarea (inmutable)
 * @property completada | Estado (True or False) de completitud de la tarea (mutable, por defecto false)
 * @property prioridad | Asignacion de la prioridad de la Tarea
 *
 */

data class Tarea (
    val id: Int,
    val titulo: String,
    val descripcion: String,
    private var completada: Boolean = false, // Ahora es privada para forzar el uso de métodos
    val prioridad: Prioridad
){
    /**
     * Función que alterna la completitud de la tarea, por ejemplo: si la tarea no está completada (completada = false), el valor va a cambiar a completada (completada = true)
     */
    fun alternarCompletada(){
        completada = !completada
    }

    /**
     * El encapsulamiento en la Programación Orientada a Objetos (POO)
     * sirve para agrupar datos y métodos en una sola unidad (la clase) y,
     * al mismo tiempo, restringir el acceso directo a los detalles internos de los datos,
     * ofreciendo una interfaz controlada para su manipulación.
     */

    /**
     * Marca la tarea como completada.
     * Cambia el estado de la tarea a completada (true).
     */
    fun marcarComoCompletada() {
        completada = true
    }

    /**
     * Marca la tarea como pendiente.
     * Cambia el estado de la tarea a pendiente (false).
     */
    fun marcarComoPendiente() {
        completada = false
    }

    /**
     * Getter para consultar el estado de completitud de la tarea.
     * @return true si la tarea está completada, false si está pendiente
     */
    fun estaCompletada(): Boolean {
        return completada
    }

    /**
     * Me permite mostrar el estado de la tarea de una forma más bonita
     */
    override fun toString(): String {
        val estado = if(completada) "Completada" else "Pendiente"
        return "|$estado| [$id] $titulo - $descripcion prioridad: $prioridad"
    }

}