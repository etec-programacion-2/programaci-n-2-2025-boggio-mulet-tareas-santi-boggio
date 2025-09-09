package org.example

/**
 * Clase Proyecto que encapsula la lógica de manejo de tareas mediante:
 * - Ocultación de datos: listadetareas es privada, solo accesible por métodos controlados
 * - Interfaz pública: expone métodos específicos (agregar, eliminar, consultar, visualizar)
 * - Protección de integridad: operaciones validadas que retornan copias inmutables
 * Garantiza que las tareas solo se modifiquen de forma segura y predecible
 */

data class Proyecto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    private var listadetareas: MutableList<Tarea>

) {
    /**
     * Agrega al atributo listadetareas un elemento de tipo Tarea
     */
    fun agregarTarea(tarea: Tarea){
        listadetareas.add(tarea)
    }

    /**
     * Getter: Toma el valor del atributo listadetareas
     * return: Valor de tipo lista que contiene elementos de tipo Tarea
     */
    private fun mostrarTareas(): List<Tarea>{
        return listadetareas
    }

    /**
     * Muestra las tareas de forma más bonita
     */
    fun tareasBeauty(): String{
        var formateoTareas: String = ""

        for(i in listadetareas){
            formateoTareas = "$formateoTareas \n $i"
        }
        return formateoTareas
    }

    /**
     * Elimina una tarea de la lista de tareas del proyecto basándose en su ID
     * @param idTarea El ID de la tarea que se desea eliminar
     * Muestra un mensaje de confirmación si la tarea fue eliminada exitosamente,
     * o un mensaje de error si no se encontró ninguna tarea con el ID especificado
     */
    fun eliminarTarea(idTarea: Int): Unit{
        val eliminada = listadetareas.removeIf { it.id == idTarea }
        if (eliminada) {
            println("La tarea de id $idTarea ha sido eliminada.")
        } else {
            println("No existe una tarea con ID $idTarea")
        }
    }

    /**
     * Obtiene todas las tareas que aún no han sido completadas
     * @return Lista de tareas pendientes (no completadas) del proyecto
     * Recorre la lista de tareas y filtra únicamente aquellas que no están marcadas como completadas
     */
    fun obtenerTareasPendientes(): List<Tarea>{
        var tareasPendientes: MutableList<Tarea> = mutableListOf()

        for (tarea in listadetareas){
            if (tarea.estaCompletada() == false){
                tareasPendientes.add(tarea)
            }
        }
        return tareasPendientes
    }

    /**
     * Obtiene todas las tareas que han sido completadas
     * @return Lista de tareas completadas del proyecto
     * Recorre la lista de tareas y filtra únicamente aquellas que están marcadas como completadas
     */
    fun obtenerTareasCompletadas(): List<Tarea>{
        var tareasCompletadas: MutableList<Tarea> = mutableListOf()

        for (tarea in listadetareas){
            if (tarea.estaCompletada()){
                tareasCompletadas.add(tarea)
            }
        }
        return tareasCompletadas
    }

    override fun toString(): String {
        var formateoTareas: String = tareasBeauty()
        return "ID: $id \n" +
                "Nombre: $nombre \n" +
                "Esta es la descripcion del proyecto: $descripcion \n" +
                "Estas son las tareas: $formateoTareas"

    }

}