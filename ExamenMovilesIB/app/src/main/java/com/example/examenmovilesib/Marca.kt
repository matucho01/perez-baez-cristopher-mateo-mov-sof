package com.example.examenmovilesib

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Marca {
    private var id: Int = 0
    private var nombre: String = ""
    var pais: String = ""
    private var fechaCreacion: LocalDate? = null
    private var sede: String = ""

    //var bicicletas: MutableList<Bicicleta> = mutableListOf()

    constructor(id: Int, nombre: String, pais: String, fechaCreacion: LocalDate, sede: String) {
        this.id = id
        this.nombre = nombre
        this.pais = pais
        this.fechaCreacion = fechaCreacion
        this.sede = sede
    }

    fun getId(): Int {
        return this.id
    }

    fun getNombre(): String {
        return this.nombre
    }

    fun getPais(): String {
        return this.pais
    }

    fun getFechaCreacion(): LocalDate? {
        return this.fechaCreacion
    }

    fun getSede(): String {
        return this.sede
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setPais(pais: String) {
        this.pais = pais
    }

    fun setFechaCreacion(fechaCreacion: LocalDate) {
        this.fechaCreacion = fechaCreacion
    }

    fun setSede(sede: String) {
        this.sede = sede
    }

    override fun toString(): String {
        return "Id:$id\nNombre:$nombre\nPaís:$pais\nFecha creación:${
            fechaCreacion!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}\nSede:$sede"
    }
}