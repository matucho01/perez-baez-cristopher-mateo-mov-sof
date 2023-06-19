class Main {
    private val marcas: MutableList<Marca> = mutableListOf()

    fun ejecutar() {
        while (true) {
            mostrarMenu()
            val opcion = readLine()?.toIntOrNull()

            when (opcion) {
                1 -> crearMarca()
                2 -> editarMarca()
                3 -> eliminarMarca()
                4 -> agregarBicicleta()
                5 -> editarBicicleta()
                6 -> eliminarBicicleta()
                7 -> mostrarMarcas()
                8 -> break
                else -> println("Opción inválida. Inténtalo nuevamente.")
            }
        }
    }

    private fun mostrarMenu() {
        println("----- MENÚ -----")
        println("1. Crear marca")
        println("2. Editar marca")
        println("3. Eliminar marca")
        println("4. Agregar bicicleta")
        println("5. Editar bicicleta")
        println("6. Eliminar bicicleta")
        println("7. Mostrar marcas y bicicletas")
        println("8. Salir")
        println("-----------------")
        println("Ingresa el número de la opción deseada:")
    }

    private fun crearMarca() {
        println("Ingresa el nombre de la marca:")
        val nombre = readLine().orEmpty()

        println("Ingresa el país de origen de la marca:")
        val pais = readLine().orEmpty()

        println("Ingresa el año de creación de la marca:")
        val anioCreacion = readLine()?.toIntOrNull() ?: 0

        println("Ingresa la sede de la marca:")
        val sede = readLine().orEmpty()

        val marca = Marca(nombre, pais, anioCreacion, sede)
        marcas.add(marca)
        println("Marca creada exitosamente.")
    }

    private fun editarMarca() {
        val marca = seleccionarMarca("Ingresa el número de la marca que deseas editar:")
        if (marca != null) {
            println("Ingresa el nuevo nombre de la marca:")
            val nombre = readLine().orEmpty()
            marca.nombre = nombre

            println("Ingresa el nuevo país de la marca:")
            val pais = readLine().orEmpty()
            marca.pais = pais

            println("Ingresa el nuevo año de creación de la marca:")
            val anioCreacion = readLine()?.toIntOrNull() ?: 0
            marca.anioCreacion = anioCreacion

            println("Ingresa la nueva sede de la marca:")
            val sede = readLine().orEmpty()
            marca.sede = sede

            println("Marca editada exitosamente.")
        }
    }

    private fun eliminarMarca() {
        val marca = seleccionarMarca("Ingresa el número de la marca que deseas eliminar:")
        if (marca != null) {
            marcas.remove(marca)
            println("Marca eliminada exitosamente.")
        }
    }

    private fun agregarBicicleta() {
        val marca = seleccionarMarca("Ingresa el número de la marca a la que deseas agregar una bicicleta:")
        if (marca != null) {
            println("Ingresa el modelo de la bicicleta:")
            val modelo = readLine().orEmpty()

            println("Ingresa el tipo de la bicicleta:")
            val tipo = readLine().orEmpty()

            println("Ingresa el año de la bicicleta:")
            val anio = readLine()?.toIntOrNull() ?: 0

            println("Ingresa el material de la bicicleta:")
            val material = readLine().orEmpty()

            println("Ingresa el precio de la bicicleta:")
            val precio = readLine()?.toDoubleOrNull() ?: 0

            val bicicleta = Bicicleta(modelo, tipo, anio, material, precio)
            marca.agregarBicicleta(bicicleta)
            println("Bicicleta agregada exitosamente.")
        }
    }

    private fun editarBicicleta() {
        val marca = seleccionarMarca("Ingresa el número de la marca a la que pertenece la bicicleta que deseas editar:")
        if (marca != null) {
            val bicicletas = marca.obtenerBicicletas()
            if (bicicletas.isEmpty()) {
                println("No hay bicicletas disponibles para editar.")
                return
            }

            println("Ingresa el número de la bicicleta que deseas editar:")
            mostrarBicicletas(bicicletas)

            val indice = readLine()?.toIntOrNull()
            if (indice != null && indice in 0 until bicicletas.size) {
                println("Ingresa el nuevo modelo de la bicicleta:")
                val modelo = readLine().orEmpty()

                println("Ingresa el nuevo tipo de la bicicleta:")
                val tipo = readLine().orEmpty()

                println("Ingresa el nuevo año de la bicicleta:")
                val anio = readLine()?.toIntOrNull() ?: 0

                println("Ingresa el nuevo material de la bicicleta:")
                val material = readLine().orEmpty()

                println("Ingresa el nuevo precio de la bicicleta:")
                val precio = readLine()?.toDoubleOrNull() ?: 0

                val bicicleta = Bicicleta(modelo, tipo, anio, material, precio)
                marca.editarBicicleta(indice, bicicleta)
                println("Bicicleta editada exitosamente.")
            } else {
                println("Índice inválido. Inténtalo nuevamente.")
            }
        }
    }

    private fun eliminarBicicleta() {
        val marca = seleccionarMarca("Ingresa el número de la marca a la que pertenece la bicicleta que deseas eliminar:")
        if (marca != null) {
            val bicicletas = marca.obtenerBicicletas()
            if (bicicletas.isEmpty()) {
                println("No hay bicicletas disponibles para eliminar.")
                return
            }

            println("Ingresa el número de la bicicleta que deseas eliminar:")
            mostrarBicicletas(bicicletas)

            val indice = readLine()?.toIntOrNull()
            if (indice != null && indice in 0 until bicicletas.size) {
                marca.eliminarBicicleta(indice)
                println("Bicicleta eliminada exitosamente.")
            } else {
                println("Índice inválido. Inténtalo nuevamente.")
            }
        }
    }

    private fun mostrarMarcas() {
        if (marcas.isEmpty()) {
            println("No hay marcas registradas.")
            return
        }

        for ((index, marca) in marcas.withIndex()) {
            println("${index + 1}. Marca: ${marca.nombre}")
            val bicicletas = marca.obtenerBicicletas()
            if (bicicletas.isNotEmpty()) {
                println("   Bicicletas:")
                mostrarBicicletas(bicicletas)
            }
        }
    }

    private fun seleccionarMarca(mensaje: String): Marca? {
        if (marcas.isEmpty()) {
            println("No hay marcas registradas.")
            return null
        }

        println(mensaje)
        mostrarMarcas()

        val indice = readLine()?.toIntOrNull()
        if (indice != null && indice in 0 until marcas.size) {
            return marcas[indice]
        } else {
            println("Índice inválido. Inténtalo nuevamente.")
            return null
        }
    }

    private fun mostrarBicicletas(bicicletas: List<Bicicleta>) {
        for ((index, bicicleta) in bicicletas.withIndex()) {
            println("   ${index + 1}. Modelo: ${bicicleta.modelo}, Tipo: ${bicicleta.tipo}, Año: ${bicicleta.anio}, " +
                    "Material: ${bicicleta.material}, Precio: ${bicicleta.precio}")
        }
    }
}

fun main() {
    val main = Main()
    main.ejecutar()
}
