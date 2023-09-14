package com.example.examenmovilesib.modelo

class Bicicleta {
    private var id: Int = 0
    private var modelo: String = ""
    private var tipo: String = ""
    private var anio: Int = 0
    private var precio: Double = 0.00
    private var disponible: Boolean = false
    private var marcaId = 0

    constructor(id: Int, modelo: String, tipo: String, anio: Int, precio: Double, disponible: Boolean, marcaId: Int) {
        this.id = id
        this.modelo = modelo
        this.tipo = tipo
        this.anio = anio
        this.precio = precio
        this.disponible = disponible
        this.marcaId = marcaId
    }

    fun getId(): Int {
        return this.id
    }

    fun getModelo(): String {
        return this.modelo
    }

    fun getTipo(): String {
        return this.tipo
    }

    fun getAnio(): Int {
        return this.anio
    }

    fun getPrecio(): Double {
        return this.precio
    }

    fun getDisponible(): Boolean {
        return this.disponible
    }

    fun getMarcaId(): Int {
        return this.marcaId
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setModelo(modelo: String) {
        this.modelo = modelo
    }

    fun setTipo(tipo: String) {
        this.tipo = tipo
    }

    fun setAnio(anio: Int) {
        this.anio = anio
    }

    fun setPrecio(precio: Double) {
        this.precio = precio
    }

    fun setDisponible(disponible: Boolean) {
        this.disponible = disponible
    }

    fun setMarcaId(marcaId: Int) {
        this.marcaId = marcaId
    }

    override fun toString(): String {
        return "Id:$id\nModelo:$modelo\nTipo:$tipo\nAño:$anio\n" +
                "Disponible:${if(disponible)"sí" else "no"}\nPrecio:$precio"
    }
}