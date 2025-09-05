package org.example

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
     * Getter: Muestra el valor del atributo listadetareas
     * return: Valor de tipo lista que contiene elementos de tipo Tarea
     */
    fun mostrarTareas(): List<Tarea>{
        return listadetareas
    }

    fun eliminarTarea(idTarea: Int){
        for (tar in listadetareas){
            if (tar.id == idTarea){
                listadetareas.remove(tar)
            }
        }
        println(listadetareas)
    }

    override fun toString(): String {
        var formateoTareas: String = ""

        for(i in listadetareas){
            formateoTareas = "$formateoTareas \n $i"
        }
        return "ID: $id \n" +
                "Nombre: $nombre \n" +
                "Esta es la descripcion del proyecto: $descripcion \n" +
                "Estas son las tareas: $formateoTareas"

    }

}