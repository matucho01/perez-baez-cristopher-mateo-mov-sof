package com.example.examenmovilesib.dao

import com.example.examenmovilesib.BDDMemoria
import com.example.examenmovilesib.modelo.Bicicleta

class BicicletaDAO(): DAO<Bicicleta>() {
    override fun delete(id: Int): Boolean {
        return BDDMemoria.listaBicicletas.removeIf { it.getId() == id }
    }

    override fun get(id: Int): Bicicleta? {
        return BDDMemoria.listaBicicletas.firstOrNull { bicicleta: Bicicleta -> bicicleta.getId() == id }
    }

    override fun getLista(): List<Bicicleta> {
        return BDDMemoria.listaBicicletas
    }

    fun getLista(marcaId: Int): List<Bicicleta> {
        return BDDMemoria.listaBicicletas.filter { it.getMarcaId() == marcaId }
    }

    override fun edit(t: Bicicleta) {
        val indice = BDDMemoria.listaBicicletas.indexOfFirst { it.getId() == t.getId() }
        BDDMemoria.listaBicicletas.set(indice,t)
    }

    override fun add(t: Bicicleta) {
        val idFinal = BDDMemoria.listaBicicletas.last().getId()
        t.setId(idFinal + 1);
        BDDMemoria.listaBicicletas.add(t)
    }
}