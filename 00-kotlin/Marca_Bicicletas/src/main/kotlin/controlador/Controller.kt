package controlador

import vista.View
import com.google.gson.Gson
import modelo.Bicicleta
import modelo.Marca
import java.io.File

class Controller(
    private val gson: Gson
) {
    private val marcas: MutableList<Marca> = mutableListOf()
    private val view: View = View(this)

    init {
        cargarDatos()
    }

    fun agregarMarca() {
        val marca = Marca(
            id = generarNuevoIdMarca(),
            nombre = view.obtenerTexto("Ingrese el nombre de la marca: "),
            pais = view.obtenerTexto("País de origen: "),
            //fechaCreacion = console.obtenerFecha("Fecha de creación (YYYY-MM-DD): "),
            fechaCreacion = view.obtenerTexto("Fecha de creación (YYYY-MM-DD): "),
            sede = view.obtenerTexto("Sede: ")
        )
        marcas.add(marca)

        guardarEnArchivo()

        println("Marca creada exitosamente")
    }

    fun mostrarMarcas() {
        if (marcas.isNotEmpty()) {
            println("Marcas:")
            for (marca in marcas) {
                println(marca)
            }
        } else {
            println("No hay marcas registradas")
        }
    }

    fun actualizarMarca() {

        mostrarMarcas()
        val idMarca = view.obtenerInt("Ingresa el ID de la marca a actualizar: ")
        val marca = obtenerMarcaPorId(idMarca)

        if (marca != null) {
            println("Ingresa los nuevos datos de la marca:")
            val nuevoNombre = view.obtenerTexto("Nuevo nombre de la marca: ")
            val nuevoPais = view.obtenerTexto("Nuevo país: ")
            //val nuevaFechaCreacion = console.obtenerFecha("Nueva fecha de creación (YYYY-MM-DD): ")
            val nuevaFechaCreacion = view.obtenerTexto("Nueva fecha de creación (YYYY-MM-DD): ")
            val nuevaSede = view.obtenerTexto("Nueva sede: ")

            marca.nombre = nuevoNombre
            marca.pais = nuevoPais
            marca.fechaCreacion = nuevaFechaCreacion
            marca.sede = nuevaSede

            guardarEnArchivo()

            println("Marca actualizada")
        } else {
            println("Marca no encontrada")
        }
    }

    fun eliminarMarca() {
        val maxIdMarca: Int = marcas.maxOfOrNull { it.id } ?: 0

        while (true) {
            val id = view.obtenerInt("Ingresa el ID de la marca a eliminar: ")

            if (id in 1..maxIdMarca) {
                eliminarMarcaPorId(id)
                guardarEnArchivo()
                println("Marca eliminada")
                break
            } else {
                println("Entrada incorrecta, intenta de nuevo")
            }
        }
    }

    fun crearBicicletaDeMarca() {
        val marcaId = view.obtenerTexto("Ingresa el ID de la marca: ").toIntOrNull()
        if (marcaId != null) {
            agregarBicicleta(marcaId)
        } else {
            println("ID de marca incorrecto")
        }
    }

    private fun agregarBicicleta(marcaId: Int) {
        val marca = obtenerMarcaPorId(marcaId)
        if (marca != null) {
            val bicicleta = Bicicleta(
                id = generarNuevoIdBicicleta(),
                modelo = view.obtenerTexto("Ingresa el modelo de la bicicleta: "),
                tipo = view.obtenerTexto("Tipo: "),
                anio = view.obtenerInt("Año: "),
                precio = view.obtenerDouble("Precio: "),
                marcaId = marcaId
            )
            marca.bicicletas.add(bicicleta)
            guardarEnArchivo()
            println("Bicicleta agregada")
        } else {
            println("Marca no encontrada")
        }
    }

    fun mostrarBicicletas() {
        val bicicletas = obtenerListaBicicletas()
        for (bicicleta in bicicletas) {
            println(bicicleta)
        }
    }

    fun actualizarBicicleta() {
        mostrarMarcas()
        val idMarca =
            view.obtenerInt("\nIngresa el ID de la marca donde se encuentra la bicicleta a actualizar: ")
        val marca = obtenerMarcaPorId(idMarca)

        if (marca != null) {
            println("Las bicicletas disponibles para actualizar son:")

            for (bicicleta in marca.bicicletas) {
                println(bicicleta)

            }

            if (marca.bicicletas.isNotEmpty()) {

                val idBicicleta = view.obtenerInt("\nIngresa el ID de la bicicleta a actualizar: ")
                val bicicleta = obtenerBicicletaPorId(idBicicleta)

                if (bicicleta != null) {
                    println("Ingresa los nuevos datos de la bici:")
                    val nuevoModelo = view.obtenerTexto("Nuevo modelo de la bicicleta: ")
                    val nuevoTipo = view.obtenerTexto("Nuevo tipo: ")
                    val nuevoAnio = view.obtenerInt("Nuevo año: ")
                    val nuevoPrecio = view.obtenerDouble("Nuevo precio: ")

                    bicicleta.modelo = nuevoModelo
                    bicicleta.tipo = nuevoTipo
                    bicicleta.anio = nuevoAnio
                    bicicleta.precio = nuevoPrecio

                    guardarEnArchivo()

                    println("Bicicleta actualizada")
                } else {
                    println("Bicicleta no encontrada")
                }
            } else {
                println("No existen bicicletas de esa marca")
            }
        } else {
            println("Marca no encontrada")
        }
    }

    fun eliminarBicicleta() {
        val idMarca =
            view.obtenerInt("Ingresa el ID de la marca donde se encuentra la bicicleta a eliminar: ")
        val marca: Marca? = obtenerMarcaPorId(idMarca)

        println("Las bicicletas disponibles para eliminar son las siguientes:")
        println(marca?.bicicletas)

        val idBicicleta = view.obtenerInt("Ingresa el ID de la bicicleta a eliminar: ")
        val bicicleta: Bicicleta? = obtenerBicicletaPorId(idBicicleta)

        marca?.bicicletas?.remove(bicicleta)

        guardarEnArchivo()
    }

    private fun generarNuevoIdMarca(): Int {
        return if (marcas.isEmpty()) 1 else marcas.maxByOrNull { it.id }!!.id + 1
    }

    private fun generarNuevoIdBicicleta(): Int {
        val platillos = obtenerListaBicicletas()
        return if (platillos.isEmpty()) 1 else platillos.maxByOrNull { it.id }!!.id + 1
    }

    private fun obtenerListaBicicletas(): MutableList<Bicicleta> {
        val bicicletas = mutableListOf<Bicicleta>()
        if (marcas.isNotEmpty()) {
            for (marca in marcas) {
                for (bicicleta in marca.bicicletas) {
                    bicicletas.add(bicicleta)
                }
            }
        } else {
            println("No existen marcas registradas")
        }
        if (bicicletas.isEmpty()) println("No existen bicicletas registradas")
        return bicicletas
    }

    private fun guardarEnArchivo() {
        val json = gson.toJson(marcas)
        val file = File("src/main/resources/marcas.json")
        file.writeText(json)
    }

    private fun obtenerMarcaPorId(id: Int): Marca? {
        return marcas.find { it.id == id }
    }

    private fun obtenerBicicletaPorId(id: Int): Bicicleta? {
        val platillos = obtenerListaBicicletas()
        return platillos.find { it.id == id }
    }

    private fun eliminarMarcaPorId(id: Int) {
        val marca: Marca? = obtenerMarcaPorId(id)
        marcas.remove(marca)
    }

    private fun cargarDatos() {
        val file = File("src/main/resources/marcas.json")
        if (file.exists()) {
            val json = file.readText()
            val marcaArray = gson.fromJson(json, Array<Marca>::class.java)
            marcas.addAll(marcaArray)
        }
        obtenerListaBicicletas()
    }

}