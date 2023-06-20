package controlador

import vista.Console
import com.google.gson.Gson
import modelo.Bicicleta
import modelo.Marca
import java.io.File

class Crud(private val gson: Gson) {
    private val marcas: MutableList<Marca> = mutableListOf()
    private val console: Console = Console(this)

    init {
        cargarDatos()
    }

    fun agregarMarca() {
        val marca = Marca(
            id = generarNuevoIdMarca(),
            nombre = console.obtenerTexto("Ingrese el nombre de la marca: "),
            pais = console.obtenerTexto("País de origen: "),
            //fechaCreacion = console.obtenerFecha("Fecha de creación (YYYY-MM-DD): "),
            fechaCreacion = console.obtenerTexto("Fecha de creación (YYYY-MM-DD): "),
            sede = console.obtenerTexto("Sede: ")
        )
        marcas.add(marca)

        guardarDatos()

        println("Marca creada exitosamente")
    }

    fun listarMarcas() {
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

        listarMarcas()
        val idMarca = console.obtenerInt("Ingresa el ID de la marca a actualizar: ")
        val marca = obtenerMarcaPorId(idMarca)

        if (marca != null) {
            println("Ingresa los nuevos datos de la marca:")
            val nuevoNombre = console.obtenerTexto("Nuevo nombre de la marca: ")
            val nuevoPais = console.obtenerTexto("Nuevo país: ")
            //val nuevaFechaCreacion = console.obtenerFecha("Nueva fecha de creación (YYYY-MM-DD): ")
            val nuevaFechaCreacion = console.obtenerTexto("Nueva fecha de creación (YYYY-MM-DD): ")
            val nuevaSede = console.obtenerTexto("Nueva sede: ")

            marca.nombre = nuevoNombre
            marca.pais = nuevoPais
            marca.fechaCreacion = nuevaFechaCreacion
            marca.sede = nuevaSede

            guardarDatos()

            println("Marca actualizada")
        } else {
            println("Marca no encontrada")
        }
    }


    fun eliminarMarca() {
        val maxIdMarca: Int = marcas.maxOfOrNull { it.id } ?: 0

        while (true) {
            val id = console.obtenerInt("Ingrese el ID de la marca a eliminar: ")

            if (id in 1..maxIdMarca) {
                eliminarMarcaPorId(id)
                guardarDatos()
                println("Marca eliminada")
                break
            } else {
                println("Entrada inválida, intenta de nuevo")
            }
        }
    }

    fun crearBicicletaAMarca() {
        val marcaId = console.obtenerTexto("Ingrese el ID de la marca: ").toIntOrNull()
        if (marcaId != null) {
            agregarBicicleta(marcaId)
        } else {
            println("ID de marca inválido")
        }
    }

    private fun agregarBicicleta(marcaId: Int) {
        val marca = obtenerMarcaPorId(marcaId)
        if (marca != null) {
            val bicicleta = Bicicleta(
                id = generarNuevoIdBicicleta(),
                modelo = console.obtenerTexto("Ingresa el modelo de la bicicleta: "),
                tipo = console.obtenerTexto("Tipo: "),
                anio = console.obtenerInt("Año: "),
                precio = console.obtenerDouble("Precio: "),
                marcaId = marcaId
            )
            marca.bicicletas.add(bicicleta)
            guardarDatos()
            println("Bicicleta agregada")
        } else {
            println("Marca no encontrada")
        }
    }

    fun listarBicicletas() {
        val bicicletas = obtenerListaBicicletas()
        for (bicicleta in bicicletas) {
            println(bicicleta)
        }
    }

    fun actualizarBicicleta() {
        listarMarcas()
        val idMarca =
            console.obtenerInt("\nIngresa el ID de la marca donde se encuentra la bicicleta a actualizar: ")
        val marca = obtenerMarcaPorId(idMarca)

        if (marca != null) {
            println("Las bicicletas disponibles para actualizar son:")

            for (bicicleta in marca.bicicletas) {
                println(bicicleta)

            }

            if (marca.bicicletas.isNotEmpty()) {

                val idBicicleta = console.obtenerInt("\nIngresa el ID de la bicicleta a actualizar: ")
                val bicicleta = obtenerBicicletaPorId(idBicicleta)

                if (bicicleta != null) {
                    println("Ingresa los nuevos datos de la bici:")
                    val nuevoModelo = console.obtenerTexto("Nuevo modelo de la bicicleta: ")
                    val nuevoTipo = console.obtenerTexto("Nuevo tipo: ")
                    val nuevoAnio = console.obtenerInt("Nuevo año: ")
                    val nuevoPrecio = console.obtenerDouble("Nuevo precio: ")

                    bicicleta.modelo = nuevoModelo
                    bicicleta.tipo = nuevoTipo
                    bicicleta.anio = nuevoAnio
                    bicicleta.precio = nuevoPrecio

                    guardarDatos()

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
            console.obtenerInt("Ingresa el ID de la marca donde se encuentra la bicicleta a eliminar: ")
        val marca: Marca? = obtenerMarcaPorId(idMarca)

        println("Las bicicletas disponibles para eliminar son las siguientes:")
        println(marca?.bicicletas)

        val idBicicleta = console.obtenerInt("Ingresa el ID de la bicicleta a eliminar: ")
        val bicicleta: Bicicleta? = obtenerBicicletaPorId(idBicicleta)

        marca?.bicicletas?.remove(bicicleta)

        guardarDatos()
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

    private fun guardarDatos() {
        val json = gson.toJson(marcas)
        val file = File("marcas.json")
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
        val file = File("marcas.json")
        if (file.exists()) {
            val json = file.readText()
            val marcaArray = gson.fromJson(json, Array<Marca>::class.java)
            marcas.addAll(marcaArray)
        }
        obtenerListaBicicletas()
    }

}