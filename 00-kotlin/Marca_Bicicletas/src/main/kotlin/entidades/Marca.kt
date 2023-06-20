package entidades

import java.time.LocalDate

data class Marca(
    var id: Int,
    var nombre: String,
    var pais: String,
    //var fechaCreacion: LocalDate,
    var sede: String,

    var bicicletas: MutableList<Bicicleta> = mutableListOf()
)