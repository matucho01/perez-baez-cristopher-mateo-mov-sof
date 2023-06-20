package entidades

data class Bicicleta(
    var id: Int,
    var modelo: String,
    var tipo: String,
    var anio: Int,
    var precio: Double,
    var marcaId: Int
)