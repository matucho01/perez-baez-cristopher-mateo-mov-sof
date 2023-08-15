package com.example.examenmovilesib.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.examenmovilesib.DB
import com.example.examenmovilesib.modelo.Bicicleta

class BicicletaDAO(
    context: Context?,
): DAO<Bicicleta>(context) {

    override fun onCreate(db: SQLiteDatabase?) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun add(bicicleta: Bicicleta) {
        val conexionEscritura = writableDatabase
        val valores = ContentValues()
        valores.put("NOMBRE", bicicleta.getModelo())
        valores.put("TIPO", bicicleta.getTipo())
        valores.put("ANIO", bicicleta.getAnio())
        valores.put("PRECIO", bicicleta.getPrecio())
        valores.put("DISPONIBLE", bicicleta.getDisponible())
        valores.put("MARCA_ID", bicicleta.getMarcaId())
        conexionEscritura.insert("BICICLETA", null, valores)
        conexionEscritura.close()
    }

    override fun edit(bicicleta: Bicicleta) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Bicicleta? {
        TODO("Not yet implemented")
    }

    override fun getLista(): List<Bicicleta> {
        TODO("Not yet implemented")
    }

}