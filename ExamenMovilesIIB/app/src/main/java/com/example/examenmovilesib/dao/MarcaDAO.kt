package com.example.examenmovilesib.dao

import android.content.Context
import com.example.examenmovilesib.modelo.Marca
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class MarcaDAO(
    context: Context?,
) : DAO<Marca>(context) {

    private val databaseReference: CollectionReference =  Firebase.firestore.collection("marcas")
    companion object {
        var listaLocal= mutableListOf<Marca>()
    }

    override fun add(marca: Marca) {
        marca.setId((System.currentTimeMillis() % 100000).toInt())
        val marcaData = HashMap<String, Any>()
        marcaData["nombre"] = marca.getNombre()
        marcaData["pais"] = marca.getPais()
        marcaData["fechaCreacion"] = marca.getFechaCreacion().toString()
        marcaData["sede"] = marca.getSede()

        databaseReference.document(marca.getId().toString())
            .set(marcaData)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun edit(marca: Marca) {
        val marcaData = HashMap<String, Any>()
        marcaData["nombre"] = marca.getNombre()
        marcaData["pais"] = marca.getPais()
        marcaData["fechaCreacion"] = marca.getFechaCreacion().toString()
        marcaData["sede"] = marca.getSede()

        databaseReference.document(marca.getId().toString())
            .set(marcaData)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    override fun delete(id: Int): Boolean {
        var flag = false;
        databaseReference.document(id.toString())
            .delete()
            .addOnSuccessListener { flag = true }
            .addOnFailureListener {  }
        return true
    }

    override fun get(id: Int): Marca? {
        var marcaAux:Marca ?= null
        databaseReference.document(id.toString()).get().addOnSuccessListener { document ->
            val data = document.data
            val marcaA = Marca(
                document.id.toInt(),
                data?.get("nombre") as String,
                data.get("pais") as String,
                LocalDate.parse(data?.get("fechaCreacion") as String),
                data.get("sede") as String
            )
            marcaAux = marcaA
        }.addOnFailureListener {

        }

        if (marcaAux == null) {
            marcaAux = listaLocal.find { it.getId() == id }
        }

        return marcaAux
    }

    override fun getLista(): List<Marca> {
        val listaMarcas: MutableList<Marca> = ArrayList()
        databaseReference.get().addOnSuccessListener { result ->
            for (document in result) {
                val data = document.data
                val marca = Marca(
                    document.id.toInt(),
                    data?.get("nombre") as String,
                    data.get("pais") as String,
                    LocalDate.parse(data?.get("fechaCreacion") as String),
                    data.get("sede") as String
                )
                listaMarcas.add(marca)
            }
            listaLocal = listaMarcas
        }.addOnFailureListener { ex ->
            println(ex.toString())
        }
        println(listaMarcas.size)

        return listaLocal
    }

    fun existe(id: Int): Boolean {
        var flag = false;
        databaseReference.document(id.toString())
            .get()
            .addOnSuccessListener { flag = true }
            .addOnFailureListener {  }

        if(!flag) {
            flag = listaLocal.any { marca: Marca -> marca.getId() == id }
        }

        return flag
    }
}