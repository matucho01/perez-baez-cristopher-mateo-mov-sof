package com.example.examen1bmoviles.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Marca(
    var id: String?,
    var nombre: String?,
    var pais: String?,
    var fechaCreacion: LocalDate?,
    var sede: String?,
    var bicicletas: MutableList<Bicicleta> = mutableListOf()
) {
    constructor() : this(null, null, null, null, null)

    override fun toString(): String {
        return "Id:$id\nNombre:$nombre\nPaís:$pais\nFecha creación:${
            fechaCreacion!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}\nSede:$sede"
    }


}