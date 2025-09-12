package org.example

data class GestorDeTareas (
    private var allProyectos: MutableList<Proyecto> = mutableListOf<Proyecto>(),
    private var allTareas: MutableList<Tarea> = mutableListOf<Tarea>(),
    private var allUsers: MutableList<Usuario> = mutableListOf<Usuario>(),
    private var idProyectoActual: Int = 1,
    private var idUsuarioActual: Int = 1,
    private var idTareaActual: Int = 1
) {
    fun crearProyecto(nombrePy: String, descripcionPy: String ){
        val proyecto: Proyecto = Proyecto(idProyectoActual, nombrePy, descripcionPy)
        allProyectos.add(proyecto)
        idProyectoActual += 1
    }

    fun crearUsuario(username: String, email: String){
        val usuario: Usuario = Usuario(idUsuarioActual, username, email)
        allUsers.add(usuario)
        idUsuarioActual += 1
    }

    fun crearTarea(nombreTarea: String, descripcionTarea: String, prioridadTarea: Prioridad){
        val tarea: Tarea = Tarea(idTareaActual,nombreTarea, descripcionTarea, prioridad = prioridadTarea)
        allTareas.add(tarea)
        idTareaActual += 1
    }

    fun agregarTareaAProyecto(idTarea: Int, idProyecto: Int){
        for (proyectoid in 0..allProyectos.size-1){
            if (allProyectos[proyectoid].id == idProyecto){
                for (tarea in allTareas){
                    if (tarea.id == idTarea){
                        allProyectos[proyectoid].agregarTarea(tarea)
                    }
                }
            }
        }
    }

    fun mostrarProyectos(): MutableList<Proyecto>{
        return allProyectos
    }
}