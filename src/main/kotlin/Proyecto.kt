package org.example

data class Proyecto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val listadetareas: List<String>

) {
    override fun toString(): String {
        return "ID: $id \n" +
                "Nombre: $nombre \n" +
                "Esta es la descripcion del proyecto: $descripcion \n" +
                "Estas son sus tareas: $listadetareas"
    }

}