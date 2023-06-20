package vista

import controlador.Crud
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Console(private val crud: Crud) {

    private val scanner = Scanner(System.`in`)

    fun mostrarMenuPrincipal() {
        var opc: Int
        val menu: String = "-------Deber 01 Móviles-------" +
                "\n1. Menú de Marca" +
                "\n2. Menú de Bicicleta" +
                "\n3. Salir" +
                "\nEscoge una opción: "

        do {
            print(menu)

            opc = scanner.nextInt()
            scanner.nextLine()

            when (opc) {
                1 -> mostrarOpcionesMarca()
                2 -> mostrarOpcionesBicicleta()
                3 -> break

                else -> println("Opción inválida")
            }
            println()
        } while (opc != 3)
    }

    private fun mostrarOpcionesMarca() {
        var opc: Int
        val menu: String = "-------------MARCAS-------------" +
                "\n1. Agregar marca" +
                "\n2. Listar marcas" +
                "\n3. Actualizar marca" +
                "\n4. Eliminar marca" +
                "\n5. Regresar al menú" +
                "\nEscoge una opción: "

        do {
            print(menu)

            opc = scanner.nextInt()
            scanner.nextLine()

            when (opc) {
                1 -> crud.agregarMarca()
                2 -> crud.listarMarcas()
                3 -> crud.actualizarMarca()
                4 -> crud.eliminarMarca()
                5 -> mostrarMenuPrincipal()

                else -> println("Opción inválida")
            }
            println()
        } while (opc != 5)
    }

    private fun mostrarOpcionesBicicleta() {
        var opc: Int
        val menu: String = "-------------BICICLETAS-------------" +
                "\n1. Agregar bicicleta de una marca" +
                "\n2. Mostrar bicicletas" +
                "\n3. Actualizar bicicleta" +
                "\n4. Eliminar bicicleta" +
                "\n5. Regresar al menú" +
                "\nEscoge una opción: "

        do {
            print(menu)

            opc = scanner.nextInt()
            scanner.nextLine()

            when (opc) {
                1 -> crud.crearBicicletaAMarca()
                2 -> crud.listarBicicletas()
                3 -> crud.actualizarBicicleta()
                4 -> crud.eliminarBicicleta()
                5 -> mostrarMenuPrincipal()
                else -> println("Opción inválida")
            }
            println()
        } while (opc != 5)
    }

    fun obtenerFecha(mensaje: String): LocalDate {
        while (true) {
            print(mensaje)
            val input = readLine()
            try {
                val formatter = DateTimeFormatter.ISO_LOCAL_DATE
                return LocalDate.parse(input, formatter)
            } catch (e: Exception) {
                print("Entrada inválida, intenta de nuevo")
            }
        }
    }

    fun obtenerTexto(mensaje: String): String {
        print(mensaje)
        return readLine() ?: ""
    }

    fun obtenerInt(mensaje: String): Int {
        while (true) {
            print(mensaje)
            val input = readLine()
            try {
                return input?.toInt() ?: 0
            } catch (e: NumberFormatException) {
                println("Entrada inválida, intenta de nuevo")
            }
        }
    }

    fun obtenerDouble(mensaje: String): Double {
        while (true) {
            print(mensaje)
            val input = readLine()
            try {
                return input?.toDouble() ?: 0.0
            } catch (e: NumberFormatException) {
                println("Entrada inválida, intenta de nuevo")
            }
        }
    }

}