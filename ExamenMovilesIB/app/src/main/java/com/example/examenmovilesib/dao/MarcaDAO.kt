package com.example.examenmovilesib.dao

import com.example.examenmovilesib.BDDMemoria
import com.example.examenmovilesib.modelo.Marca

class MarcaDAO(): DAO<Marca>() {
    override fun delete(id: Int): Boolean {
        return BDDMemoria.listaMarcas.removeIf { it.getId() == id }
    }

    override fun get(id: Int): Marca? {
        return BDDMemoria.listaMarcas.firstOrNull { marca: Marca -> marca.getId() == id }
    }

    override fun getLista(): List<Marca> {
        return BDDMemoria.listaMarcas
    }

    override fun edit(t: Marca) {
        val indice = BDDMemoria.listaMarcas.indexOfFirst { it.getId() == t.getId() }
        BDDMemoria.listaMarcas.set(indice,t)
    }

    override fun add(t: Marca) {
        val idFinal = BDDMemoria.listaMarcas.last().getId()
        t.setId(idFinal + 1);
        BDDMemoria.listaMarcas.add(t)
    }

    fun existe(id: Int):Boolean{
        return BDDMemoria.listaMarcas.any { it.getId()==id }
    }
}