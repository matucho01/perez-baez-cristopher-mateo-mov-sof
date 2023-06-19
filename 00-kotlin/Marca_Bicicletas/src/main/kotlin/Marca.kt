class Marca(
    var nombre: String,
    var pais: String,
    var anioCreacion: Int,
    var sede: String
) {
    private val bicicletas: MutableList<Bicicleta> = mutableListOf()

    fun agregarBicicleta(bicicleta: Bicicleta) {
        bicicletas.add(bicicleta)
        bicicleta.marca = this
    }

    fun editarBicicleta(indice: Int, bicicleta: Bicicleta) {
        if (indice >= 0 && indice < bicicletas.size) {
            bicicletas[indice] = bicicleta
            bicicleta.marca = this
        } else {
            throw IndexOutOfBoundsException("El índice está fuera del rango válido.")
        }
    }

    fun eliminarBicicleta(indice: Int) {
        if (indice >= 0 && indice < bicicletas.size) {
            val bicicleta = bicicletas[indice]
            bicicleta.marca = null
            bicicletas.removeAt(indice)
        } else {
            throw IndexOutOfBoundsException("El índice está fuera del rango válido.")
        }
    }

    fun obtenerBicicletas(): List<Bicicleta> {
        return bicicletas
    }
}