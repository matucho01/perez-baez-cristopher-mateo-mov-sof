package com.example.examenmovilesib.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
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
        valores.put("MODELO", bicicleta.getModelo())
        valores.put("TIPO", bicicleta.getTipo())
        valores.put("ANIO", bicicleta.getAnio())
        valores.put("PRECIO", bicicleta.getPrecio())
        valores.put("DISPONIBLE", bicicleta.getDisponible())
        valores.put("MARCA_ID", bicicleta.getMarcaId())
        conexionEscritura.insert("BICICLETA", null, valores)
        conexionEscritura.close()
    }

    override fun edit(bicicleta: Bicicleta) {
        val conexionEscritura = writableDatabase
        val valores = ContentValues()
        valores.put("MODELO", bicicleta.getModelo())
        valores.put("TIPO", bicicleta.getTipo())
        valores.put("ANIO", bicicleta.getAnio())
        valores.put("PRECIO", bicicleta.getPrecio())
        valores.put("DISPONIBLE", bicicleta.getDisponible())
        valores.put("MARCA_ID", bicicleta.getMarcaId())
        val parametrosConsulta = arrayOf(bicicleta.getId().toString())
        val resultadoUpdate = conexionEscritura.update(
            "BICICLETA",
            valores,
            "ID=?",
            parametrosConsulta
        )
        conexionEscritura.close()
    }

    override fun delete(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsulta = arrayOf(id.toString())
        val resultadoDelete = conexionEscritura.delete(
            "BICICLETA",
            "ID=?",
            parametrosConsulta
        )
        conexionEscritura.close()
        return resultadoDelete != -1
    }

    override fun get(id: Int): Bicicleta? {
        val conexionLectura = readableDatabase
        val scriptConsulta =
            """
            SELECT * FROM BICICLETA WHERE ID = ?
            """.trimIndent()
        val parametrosConsulta = arrayOf(id.toString())
        val resultadoConsulta = conexionLectura.rawQuery(
            scriptConsulta,
            parametrosConsulta
        )

        val existeBicicleta: Bicicleta?
        if(resultadoConsulta.moveToFirst()) {
            val id = resultadoConsulta.getInt(0)
            val modelo = resultadoConsulta.getString(1)
            val tipo = resultadoConsulta.getString(2)
            val anio = resultadoConsulta.getInt(3)
            val precio = resultadoConsulta.getDouble(4)
            val disponible = resultadoConsulta.getInt(5) != 0
            val marcaId = resultadoConsulta.getInt(6)
            existeBicicleta = Bicicleta(id, modelo, tipo, anio, precio, disponible, marcaId)
        } else {
            existeBicicleta = null
        }
        resultadoConsulta.close()
        conexionLectura.close()
        return existeBicicleta
    }

    override fun getLista(): List<Bicicleta> {
        val conexionLectura = readableDatabase
        val scriptConsulta =
            """
            SELECT * FROM BICICLETA
            """.trimIndent()
        val resultadoConsulta = conexionLectura.rawQuery(scriptConsulta, null)
        val arregloBicicletas = arrayListOf<Bicicleta>()
        if (resultadoConsulta.moveToFirst()) {
            do {
                val id = resultadoConsulta.getInt(0)
                val modelo = resultadoConsulta.getString(1)
                val tipo = resultadoConsulta.getString(2)
                val anio = resultadoConsulta.getInt(3)
                val precio = resultadoConsulta.getDouble(4)
                val disponible = resultadoConsulta.getInt(5) != 0
                val marcaId = resultadoConsulta.getInt(6)
                val bicicletaEncontrada = Bicicleta(id, modelo, tipo, anio, precio, disponible, marcaId)
                arregloBicicletas.add(bicicletaEncontrada)
            } while (resultadoConsulta.moveToNext())
        }
        resultadoConsulta.close()
        conexionLectura.close()
        return arregloBicicletas
    }

    fun getLista(marcaId: Int): List<Bicicleta> {
        val conexionLectura = readableDatabase
        val scriptConsulta =
            """
            SELECT * FROM BICICLETA WHERE MARCA_ID = ?
            """.trimIndent()
        val parametrosConsulta = arrayOf(marcaId.toString())
        val resultadoConsulta = conexionLectura.rawQuery(scriptConsulta, parametrosConsulta)
        val arregloBicicletas = arrayListOf<Bicicleta>()
        if (resultadoConsulta.moveToFirst()) {
            do {
                val id = resultadoConsulta.getInt(0)
                val modelo = resultadoConsulta.getString(1)
                val tipo = resultadoConsulta.getString(2)
                val anio = resultadoConsulta.getInt(3)
                val precio = resultadoConsulta.getDouble(4)
                val disponible = resultadoConsulta.getInt(5) != 0
                val marcaId = resultadoConsulta.getInt(6)
                val bicicletaEncontrada = Bicicleta(id, modelo, tipo, anio, precio, disponible, marcaId)
                arregloBicicletas.add(bicicletaEncontrada)
            } while (resultadoConsulta.moveToNext())
        }
        resultadoConsulta.close()
        conexionLectura.close()
        return arregloBicicletas
    }

}