package org.example
import java.util.Scanner


class InterfasCLI {
    val gestor = GestorDeTareas()
    val scanner = Scanner(System.`in`)

    fun mostrarMenu() {
        println("=== GESTOR DE TAREAS ===")
        println("1. Crear proyecto")
        println("2. Crear usuario")
        println("3. Crear tarea")
        println("4. Agregar Tarea A Proyecto")
        println("5. Asignar Usuario A Tarea")
        println("6. buscar Tareas Por Usuario")
        println("7. Mostrar Proyectos")
        println("0. Salir")
        println("Ingrese su opción: ")
    }

    fun crearproyecto(){

        println("ingrese el nombre del proyecto:")
        var nombre = scanner.nextLine() // String
        println("ingrese una descripcion:")
        var descripcion = scanner.nextLine() // String

        val proyecto = gestor.crearProyecto(nombre, descripcion)
        println(proyecto)
    }
    fun crearusuario(){
        println("ingrese el nombre del usuario:")
        var nombre = scanner.nextLine() // String
        println("ingrese un mail para su usuario:")
        var mail = scanner.nextLine() // String
        val usuario = gestor.crearUsuario(nombre, mail)
        println(usuario)
    }
    fun creartarea(){
        var priori = Prioridad.BAJA
        println("ingrese el nombre del tarea:")
        var nombre = scanner.nextLine() // String
        println("ingrese una descripcion:")
        var descripcion = scanner.nextLine() // String
        println("ingrese la prioridad de la tarea (baja, media, alta)")
        var prioridad = scanner.nextLine()

        if (prioridad == "baja"){
            priori = Prioridad.BAJA
        }
        else if (prioridad == "media" ){
            priori = Prioridad.MEDIA
        }
        else if (prioridad == "alta" ){
            priori = Prioridad.ALTA
        }


        val tarea = gestor.crearTarea(nombre, descripcion, priori)
        println(tarea)
    }
    fun agregartareaaproyecto(){
        println("ingrese el ID de la tarea:")
        var ID_tarea = scanner.nextInt() // int
        println("ingrese el ID del proyecto:")
        var ID_proyecto = scanner.nextInt() // int
        val asignartarea = gestor.agregarTareaAProyecto(ID_tarea, ID_proyecto)

    }

    fun agregarusuarioatarea(){
        println("ingrese el ID de la tarea:")
        var ID_tarea = scanner.nextInt() // int
        println("ingrese el ID del usuario:")
        var ID_usuario = scanner.nextInt() // int
        val asignartarea = gestor.asignarUsuarioATarea(ID_tarea, ID_usuario)

    }

    fun buscartareaporusuario (){
        println("ingrese el ID del usuario:")
        var ID_usuario = scanner.nextInt() // int
        val lista = gestor.buscarTareasPorUsuario(ID_usuario)
    }
    fun mostrarlosproyecto(){
        val mostrar = gestor.mostrarProyectos()
        println(mostrar)
    }

}