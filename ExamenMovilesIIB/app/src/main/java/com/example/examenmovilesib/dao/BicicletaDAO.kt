package com.example.examenmovilesib.dao

import android.content.Context
import android.util.Log
import com.example.examenmovilesib.modelo.Bicicleta
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BicicletaDAO(
    context: Context?,
): DAO<Bicicleta>(context) {

    private val databaseReference: CollectionReference = Firebase.firestore.collection("bicicletas")

    companion object {
        var listaLocalBicicletas = mutableListOf<Bicicleta>()
        var listaLocalBicicletaMarca = mutableListOf<Bicicleta>()
    }

    override fun add(bicicleta: Bicicleta) {
        bicicleta.setId((System.currentTimeMillis() % 100000).toInt()) // Usar el setter para establecer el ID.
        val bicicletaData = HashMap<String, Any>()
        bicicletaData["modelo"] = bicicleta.getModelo()
        bicicletaData["tipo"] = bicicleta.getTipo()
        bicicletaData["anio"] = bicicleta.getAnio()
        bicicletaData["precio"] = bicicleta.getPrecio()
        bicicletaData["disponible"] = bicicleta.getDisponible()
        bicicletaData["marcaId"] = bicicleta.getMarcaId()

        databaseReference.document(bicicleta.getId().toString())
            .set(bicicletaData)
            .addOnSuccessListener {
                println("Bicicleta agregada")
                listaLocalBicicletas.add(bicicleta)
            }
            .addOnFailureListener { ex ->
                println("Error al agregar bicicleta: $ex")
            }
    }

    override fun edit(bicicleta: Bicicleta) {
        val bicicletaData = HashMap<String, Any>()
        bicicletaData["modelo"] = bicicleta.getModelo()
        bicicletaData["tipo"] = bicicleta.getTipo()
        bicicletaData["anio"] = bicicleta.getAnio()
        bicicletaData["precio"] = bicicleta.getPrecio()
        bicicletaData["disponible"] = bicicleta.getDisponible()

        databaseReference.document(bicicleta.getId().toString())
            .set(bicicletaData)
            .addOnSuccessListener {  }
            .addOnFailureListener {
                ex ->
                println("Error al editar bicicleta: $ex")
            }
    }

    override fun delete(id: Int): Boolean {
        var flag = false;
        databaseReference.document(id.toString())
            .delete()
            .addOnSuccessListener {
                flag = true
                listaLocalBicicletas.removeIf { it.getId() == id }
            }
            .addOnFailureListener {
                ex ->
                println("Error al eliminar bicicleta: $ex")
            }
        return true
    }

    override fun get(id: Int): Bicicleta? {
        var bicicletaAux:Bicicleta ?= null
        databaseReference.document(id.toString()).get().addOnSuccessListener { document ->
            val data = document.data
            val bicicletaA = Bicicleta(
                document.id.toInt(),
                data?.get("modelo") as String,
                data.get("tipo") as String,
                data.get("anio") as Int,
                data.get("precio") as Double,
                data.get("disponible") as Boolean,
                data.get("marcaId") as Int
            )
            bicicletaAux = bicicletaA
        }.addOnFailureListener {

        }

        if(bicicletaAux == null) {
            bicicletaAux = listaLocalBicicletas.find { it.getId() == id }
        }

        if(bicicletaAux == null) {
            bicicletaAux = listaLocalBicicletaMarca.find { it.getId() == id }
        }

        return bicicletaAux
    }

    override fun getLista(): List<Bicicleta> {
        val listaBicicletas: MutableList<Bicicleta> = mutableListOf<Bicicleta>()
        databaseReference.get().addOnSuccessListener {
            documents ->
            for (document in documents) {
                val data = document.data
                val bicicletaAux = Bicicleta(
                    document.id.toInt(),
                    data?.get("modelo") as String,
                    data.get("tipo") as String,
                    data.get("anio") as Int,
                    data.get("precio") as Double,
                    data.get("disponible") as Boolean,
                    data.get("marcaId") as Int
                )
                listaBicicletas.add(bicicletaAux)
            }
            listaLocalBicicletas = listaBicicletas
        }.addOnFailureListener {
            ex ->
            println(ex.toString())
        }

        return listaLocalBicicletas
    }

    fun getLista(marcaId: Int): List<Bicicleta> {
        val listaBicicletas: MutableList<Bicicleta> = mutableListOf<Bicicleta>()
        databaseReference.whereEqualTo("marcaId", marcaId.toString()).get().addOnSuccessListener {
            documents ->
            for (document in documents) {
                val data = document.data
                val bicicletaAux = Bicicleta(
                    document.id.toInt(),
                    data?.get("modelo") as String,
                    data.get("tipo") as String,
                    data.get("anio") as Int,
                    data.get("precio") as Double,
                    data.get("disponible") as Boolean,
                    data.get("marcaId") as Int
                )
                listaBicicletas.add(bicicletaAux)
                Log.d("","\n.\n.\n.\n.\n.\n.\n.\n.\n.\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + marcaId)
            }
            listaLocalBicicletaMarca = listaBicicletas
        }.addOnFailureListener {
            ex ->
            println(ex.toString())
        }

        return listaLocalBicicletaMarca
    }

}