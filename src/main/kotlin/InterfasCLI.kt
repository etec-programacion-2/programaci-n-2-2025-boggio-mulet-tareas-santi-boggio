package org.example
import java.util.Scanner


class InterfasCLI {
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
        println("ingrese el id que quiere utilizar para su proyecto, debe de ser un numero entero:")
        var idstr = scanner.nextLine() // String
        val id = idstr.toInt()//tranforma a entero
        println("ingrese el nombre del proyecto:")
        var nombre = scanner.nextLine() // String
        println("ingrese una descripcion:")
        var descripcion = scanner.nextLine() // String

        val proyecto = Proyecto(id = id ,nombre = nombre, descripcion = descripcion)
        println(proyecto)
    }
    fun crearusuario(){
        println("ingrese el id que quiere utilizar para su usuario, debe de ser un numero entero:")
        var idstr = scanner.nextLine() // String
        val id = idstr.toInt()//tranforma a entero
        println("ingrese el nombre del usuario:")
        var nombre = scanner.nextLine() // String
        println("ingrese un mail para su usuario:")
        var mail = scanner.nextLine() // String
        val usuario = Usuario(id = id, nombre = nombre, mail)
        println(usuario)
    }
    fun creartarea(){
        var priori = Prioridad.BAJA
        println("ingrese el id que quiere utilizar para su tarea, debe de ser un numero entero:")
        var idstr = scanner.nextLine() // String
        val id = idstr.toInt()//tranforma a entero
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
        else{
        }

        val tarea = Tarea(id = id, titulo = nombre, descripcion = descripcion, prioridad = priori)
        println(tarea)
    }
    fun agregartareaaproyecto(){

    }

}