package modelo

data class Marca(
    var id: Int,
    var nombre: String,
    var pais: String,
    //var fechaCreacion: LocalDate,
    var fechaCreacion: String,
    var sede: String,

    var bicicletas: MutableList<Bicicleta> = mutableListOf()
)