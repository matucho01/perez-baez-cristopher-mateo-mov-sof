package com.example.examen1bmoviles.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.examen1bmoviles.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class CrearMarca : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_marca)

        val botonAniadirMarcaFire =
            findViewById<Button>(R.id.btn_aniadir_marca_fire)
        botonAniadirMarcaFire.setOnClickListener {
            crearMarcaFirestore()
        }
    }

    private fun crearMarcaFirestore() {

        if (validarDatos()) {

            val nombreMarca = findViewById<EditText>(R.id.input_nombre_marca).text.toString()
            val pais = findViewById<EditText>(R.id.input_pais).text.toString()
            val fechaCreacion = findViewById<EditText>(R.id.input_fecha_creacion).text.toString()
            val sede = findViewById<EditText>(R.id.input_sede).text.toString()

            val nuevaMarca = hashMapOf(
                "nombre" to nombreMarca,
                "pais" to pais,
                "fechaCreacion" to fechaCreacion,
                "sede" to sede
            )

            val marcasRef = db.collection("marcas")

            marcasRef
                .add(nuevaMarca)
                .addOnSuccessListener { documentReference ->
                    mostrarSnackBar("Marca creada satisfactoriamente con ID: ${documentReference.id}")
                    finish()
                }
                .addOnFailureListener { e ->
                    mostrarSnackBar("Error al crear la marca: $e")
                }
        } else {
            mostrarSnackBar("Formato de datos ingresados incorrectos")
        }
    }

    private fun validarDatos(): Boolean {

        val nombreMarca = findViewById<EditText>(R.id.input_nombre_marca).text.toString()
        val pais = findViewById<EditText>(R.id.input_pais).text.toString()
        val fechaCreacion = findViewById<EditText>(R.id.input_fecha_creacion).text.toString()
        val sede = findViewById<EditText>(R.id.input_fecha_creacion).text.toString()

        // Validación input vacío
        if (nombreMarca.isEmpty() || pais.isEmpty() || fechaCreacion.isEmpty() || sede.isEmpty()) {
            return false
        }

        return true
    }

    private fun mostrarSnackBar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

}


