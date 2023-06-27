package com.example.movilessw2023a

class BEntrenador(
    var id: Int,
    var nombre: String?,
    var descripcion: String?,
){
    override fun toString(): String {
        return "${id} - ${nombre} - ${descripcion}"
    }
}