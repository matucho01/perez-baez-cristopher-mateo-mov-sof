package com.example.examenmovilesib.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.examenmovilesib.modelo.Marca
import java.time.LocalDate

class MarcaDAO(
    context: Context?,
) : DAO<Marca>(context) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaMarca =
            """
            CREATE TABLE MARCA(
               ID INTEGER PRIMARY KEY AUTOINCREMENT,
               NOMBRE VARCHAR(100),
               PAIS VARCHAR(50),
               FECHA_CREACION VARCHAR(50),
               SEDE VARCHAR(100)
            )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaMarca)

        val scriptCrearTablaBicicleta =
            """
            CREATE TABLE BICICLETA(
               ID INTEGER PRIMARY KEY AUTOINCREMENT,
               NOMBRE VARCHAR(100),
               ANIO INTEGER,
               PRECIO REAL,
               DISPONIBLE INTEGER,
               MARCA_ID INTEGER,
               FOREIGN KEY(MARCA_ID) REFERENCES MARCA(ID) ON DELETE CASCADE
            )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaBicicleta)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun add(marca: Marca) {
        val conexionEscritura = writableDatabase
        val valores = ContentValues()
        valores.put("NOMBRE", marca.getNombre())
        valores.put("PAIS", marca.getPais())
        valores.put("FECHA_CREACION", marca.getFechaCreacion().toString())
        valores.put("SEDE", marca.getSede())
        conexionEscritura.insert("MARCA", null, valores)
        conexionEscritura.close()
    }

    override fun edit(marca: Marca) {
        val conexionEscritura = writableDatabase
        val valores = ContentValues()
        valores.put("NOMBRE", marca.getNombre())
        valores.put("PAIS", marca.getPais())
        valores.put("FECHA_CREACION", marca.getFechaCreacion().toString())
        valores.put("SEDE", marca.getSede())
        val parametrosConsulta = arrayOf(marca.getId().toString())
        val resultadoUpdate = conexionEscritura.update(
            "MARCA",
            valores,
            "ID = ?",
            parametrosConsulta
        )
        conexionEscritura.close()
    }

    override fun delete(id: Int): Boolean {
        val conexionEscritura = this.writableDatabase
        val parametrosConsulta = arrayOf(id.toString())
        val resultadoDelete = conexionEscritura.delete(
            "MARCA",
            "ID = ?",
            parametrosConsulta
        )
        conexionEscritura.close()
        return resultadoDelete != -1
    }

    override fun get(id: Int): Marca? {
        val conexionLectura = readableDatabase
        val scriptLectura =
            """
            SELECT * FROM MARCA WHERE ID = ?
            """.trimIndent()
        val parametrosConsulta = arrayOf(id.toString())
        val resultadoConsulta = conexionLectura.rawQuery(
            scriptLectura,
            parametrosConsulta
        )

        val existeMarca: Marca?
        if(resultadoConsulta.moveToFirst()) {
            val id = resultadoConsulta.getInt(0)
            val nombre = resultadoConsulta.getString(1)
            val pais = resultadoConsulta.getString(2)
            val stringFechaCreacion = resultadoConsulta.getString(3)
            val sede = resultadoConsulta.getString(4)

            val fechaCreacion = LocalDate.parse(stringFechaCreacion)
            existeMarca = Marca(id, nombre, pais, fechaCreacion, sede)
        } else {
            existeMarca = null
        }

        resultadoConsulta.close()
        conexionLectura.close()
        return existeMarca
    }

    override fun getLista(): List<Marca> {
        val conexionLectura = readableDatabase
        val scriptLectura =
            """
            SELECT * FROM MARCA
            """.trimIndent()

        val resultadoConsulta = conexionLectura.rawQuery(
            scriptLectura,
            null
        )

        val listaMarcas = mutableListOf<Marca>()

        while (resultadoConsulta.moveToNext()) {
            val id = resultadoConsulta.getInt(0)
            val nombre = resultadoConsulta.getString(1)
            val pais = resultadoConsulta.getString(2)
            val stringFechaCreacion = resultadoConsulta.getString(3)
            val sede = resultadoConsulta.getString(4)

            val fechaCreacion = LocalDate.parse(stringFechaCreacion)
            val marca = Marca(id, nombre, pais, fechaCreacion, sede)
            listaMarcas.add(marca)
        }

        resultadoConsulta.close()
        conexionLectura.close()

        return listaMarcas
    }

    fun existe(id: Int): Boolean {
        return get(id) != null
    }
}