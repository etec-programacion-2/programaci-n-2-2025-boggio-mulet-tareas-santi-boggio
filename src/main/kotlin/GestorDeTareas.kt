package org.example

/**
 * IMPORTANTE: ¿Por qué usar GestorDeTareas en lugar de poner todo en main()?
 *
 * Separar la lógica del main() es una buena práctica porque:
 *
 * 1. RESPONSABILIDAD ÚNICA: main() solo debe ser el punto de entrada,
 *    no manejar toda la lógica del negocio.
 *
 * 2. REUTILIZACIÓN: GestorDeTareas puede usarse en tests, otras clases,
 *    interfaces gráficas, APIs web, etc.
 *
 * 3. TESTING: Podemos hacer unit tests de cada método por separado.
 *    Con todo en main() sería imposible testear funcionalidades específicas.
 *
 * 4. MANTENIMIENTO: Es más fácil encontrar y modificar código cuando está
 *    organizado en clases con propósitos específicos.
 *
 * 5. ESCALABILIDAD: Permite agregar funcionalidades sin que main()
 *    se vuelva un archivo gigante e inmanejable.
 */

/**
 * Clase principal que gestiona proyectos, tareas y usuarios
 * Utiliza data class para aprovechar funcionalidades automáticas como equals, hashCode, toString
 */
data class GestorDeTareas (
    // Lista mutable que almacena todos los proyectos del sistema
    private var allProyectos: MutableList<Proyecto> = mutableListOf<Proyecto>(),
    // Lista mutable que almacena todas las tareas del sistema
    private var allTareas: MutableList<Tarea> = mutableListOf<Tarea>(),
    // Lista mutable que almacena todos los usuarios del sistema
    private var allUsers: MutableList<Usuario> = mutableListOf<Usuario>(),
    // Contador para generar IDs únicos de proyectos (autoincremental)
    private var idProyectoActual: Int = 1,
    // Contador para generar IDs únicos de usuarios (autoincremental)
    private var idUsuarioActual: Int = 1,
    // Contador para generar IDs únicos de tareas (autoincremental)
    private var idTareaActual: Int = 1
) {
    /**
     * Crea un nuevo proyecto con los datos proporcionados
     * @param nombrePy Nombre del proyecto
     * @param descripcionPy Descripción del proyecto
     * @return El proyecto creado
     */
    fun crearProyecto(nombrePy: String, descripcionPy: String ): Proyecto{
        // Crear nuevo proyecto con ID actual y datos proporcionados
        val proyecto: Proyecto = Proyecto(idProyectoActual, nombrePy, descripcionPy)
        // Agregar el proyecto a la lista de todos los proyectos
        allProyectos.add(proyecto)
        // Incrementar el contador de ID para el próximo proyecto
        idProyectoActual += 1
        // Retornar el proyecto creado
        return proyecto
    }

    /**
     * Crea un nuevo usuario con los datos proporcionados
     * @param username Nombre de usuario
     * @param email Email del usuario
     * @return El usuario creado
     */
    fun crearUsuario(username: String, email: String): Usuario{
        // Crear nuevo usuario con ID actual y datos proporcionados
        val usuario: Usuario = Usuario(idUsuarioActual, username, email)
        // Agregar el usuario a la lista de todos los usuarios
        allUsers.add(usuario)
        // Incrementar el contador de ID para el próximo usuario
        idUsuarioActual += 1
        // Retornar el usuario creado
        return usuario
    }

    /**
     * Crea una nueva tarea con los datos proporcionados
     * @param nombreTarea Nombre de la tarea
     * @param descripcionTarea Descripción de la tarea
     * @param prioridadTarea Prioridad de la tarea (enum Prioridad)
     * @return La tarea creada
     */
    fun crearTarea(nombreTarea: String, descripcionTarea: String, prioridadTarea: Prioridad): Tarea{
        // Crear nueva tarea con ID actual y datos proporcionados
        val tarea: Tarea = Tarea(idTareaActual,nombreTarea, descripcionTarea, prioridad = prioridadTarea)
        // Agregar la tarea a la lista de todas las tareas
        allTareas.add(tarea)
        // Incrementar el contador de ID para la próxima tarea
        idTareaActual += 1
        // Retornar la tarea creada
        return tarea
    }

    /**
     * Agrega una tarea existente a un proyecto existente
     * @param idTarea ID de la tarea a agregar
     * @param idProyecto ID del proyecto donde agregar la tarea
     */
    fun agregarTareaAProyecto(idTarea: Int, idProyecto: Int){
        // Buscar el proyecto por ID recorriendo todos los proyectos
        for (proyectoid in 0..allProyectos.size-1){
            // Si encontramos el proyecto con el ID buscado
            if (allProyectos[proyectoid].id == idProyecto){
                // Buscar la tarea por ID recorriendo todas las tareas
                for (tarea in allTareas){
                    // Si encontramos la tarea con el ID buscado
                    if (tarea.id == idTarea){
                        // Agregar la tarea al proyecto encontrado
                        allProyectos[proyectoid].agregarTarea(tarea)
                    }
                }
            }
        }
    }

    /**
     * Retorna la lista completa de proyectos
     * @return Lista mutable con todos los proyectos
     */
    fun mostrarProyectos(): MutableList<Proyecto>{
        return allProyectos
    }

    fun mostrartareas(): MutableList<Tarea>{
        return allTareas
    }

    /**
     * Busca todas las tareas asignadas a un usuario específico
     * @param idUsuario ID del usuario del cual buscar las tareas
     * @return Lista inmutable con las tareas asignadas al usuario
     */
    fun buscarTareasPorUsuario(idUsuario: Int): List<Tarea>{
        // Crear lista mutable temporal para almacenar las tareas del usuario
        var listaMutableDeTareasUser: MutableList<Tarea> = mutableListOf()
        // Recorrer todas las tareas del sistema
        for(tarea in allTareas){
            // Verificar si la tarea está asignada al usuario buscado
            if (tarea.getAsignacion()?.id == idUsuario){
                // Agregar la tarea a la lista temporal
                listaMutableDeTareasUser.add(tarea)
            }
        }
        // Convertir a lista inmutable antes de retornar
        val listaDeRetorno: List<Tarea> = listaMutableDeTareasUser
        return listaDeRetorno
    }

    /**
     * Asigna un usuario existente a una tarea existente
     * @param idTarea ID de la tarea a la cual asignar el usuario
     * @param idUsuario ID del usuario a asignar
     */
    fun asignarUsuarioATarea(idTarea: Int, idUsuario: Int){
        // Buscar la tarea por ID usando índices
        for (tareaId in 0..allTareas.size-1){
            // Si encontramos la tarea con el ID buscado
            if (allTareas[tareaId].id == idTarea){
                // Buscar el usuario por ID recorriendo todos los usuarios
                for (usuario in allUsers){
                    // Si encontramos el usuario con el ID buscado
                    if (usuario.id == idUsuario){
                        // Asignar el usuario a la tarea encontrada
                        allTareas[tareaId].asignarusuario(usuario)
                    }
                }
            }
        }
    }
    fun cambiarcompletado(idTarea: Int){
        for (tareaId in 0..allTareas.size-1){
            // Si encontramos la tarea con el ID buscado
            if (allTareas[tareaId].id == idTarea){
                //le cambia el completado
                allTareas[tareaId].alternarCompletada()
            }
        }

    }
}