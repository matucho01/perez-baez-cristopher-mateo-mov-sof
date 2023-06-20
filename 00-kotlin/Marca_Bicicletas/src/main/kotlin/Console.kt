import com.google.gson.Gson
import controlador.Crud
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Console(private val crud: Crud) {
    //val gson = Gson()
    private val scanner = Scanner(System.`in`)

    fun mostrarMenuPrincipal() {
        var opc: Int
        do {
            println("-------Deber 01 Móviles-------")
            println("1. Menú de Marca")
            println("2. Menú de Bicicleta")
            println("3. Salir")
            print("Elige la opción: ")

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
        do {
            println("---------------MARCAS---------------")
            println("1. Agregar marca")
            println("2. Listar marcas")
            println("3. Actualizar marca")
            println("4. Eliminar marca")
            println("5. Regresar al menú principal")
            print("Escoge una opción: ")

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
        do {
            println("---------------BICICLETAS---------------")
            println("1. Agregar bicicleta de una marca")
            println("2. Mostrar bicicletas")
            println("3. Actualizar bicicleta")
            println("4. Eliminar bicicleta")
            println("5. Regresar al menú principal")
            print("Escoge una opción: ")

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


}