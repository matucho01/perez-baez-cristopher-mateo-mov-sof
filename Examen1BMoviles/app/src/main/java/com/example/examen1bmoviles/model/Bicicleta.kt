package com.example.examen1bmoviles.model

class Bicicleta(
    var id: String?,
    var modelo: String?,
    var tipo: String?,
    var anio: Int?,
    var precio: Double?,
    var disponible: Boolean?,
    var marcaId: Int?
) {


    init {
        this.modelo; this.tipo; this.precio
    }

    constructor(
        id: String,
        modelo: String,
        tipo: String,
        anio: Int,
        precio: Double,
        disponible: Boolean,
        marcaId: Int
    ) : this(
        id = null,
        modelo = modelo,
        tipo = tipo,
        anio = anio,
        precio = precio,
        disponible = disponible,
        marcaId = marcaId
    ) {

    }

    override fun toString(): String {
        return "Id:$id\nModelo:$modelo\nTipo:$tipo\nAño:$anio\n" +
                "Disponible:${if(disponible == true)"sí" else "no"}\nPrecio:$precio"
    }
}