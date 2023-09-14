package com.example.examen1bmoviles.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.examen1bmoviles.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarMarca : AppCompatActivity() {
    private var idItemSeleccionadoMarca = 0
    private var idMarca =""
    private var nombreMarca: String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_marca)

        idItemSeleccionadoMarca = intent.getIntExtra("idItemSeleccionado", 0)
        idMarca = intent.getStringExtra("idMarca").toString()
        nombreMarca = intent.getStringExtra("nombreMarca").toString()


        findViewById<TextView>(R.id.txt_seleccionado_marca).setText(nombreMarca)
        consultarMarcaVieja(idMarca)
        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_marca)
        botonActualizar.setOnClickListener {
            actualizarMarca()
            finish()
        }
    }
    fun consultarMarcaVieja(idMarca : String){
        val db = Firebase.firestore
        val marcaReferencia = db.collection("marcas")
        marcaReferencia
            .document(idMarca)
            .get()
            .addOnSuccessListener {

                findViewById<EditText>(R.id.input_update_nombre_res).setText(it.data?.get("nombre") as String?)
                findViewById<EditText>(R.id.input_update_pais).setText(it.data?.get("pais") as String?)
                findViewById<EditText>(R.id.input_update_fecha_creacion).setText(it.data?.get("fechaCreacion") as String?)
                findViewById<EditText>(R.id.input_update_sede).setText(it.data?.get("sede") as String?)

            }
    }

    private fun actualizarMarca() {
        var db = Firebase.firestore

        val nuevoNombreMarca = findViewById<EditText>(R.id.input_update_nombre_res).text.toString()
        val nuevoPais = findViewById<EditText>(R.id.input_update_pais).text.toString()
        val nuevaFechaCreacion = findViewById<EditText>(R.id.input_update_fecha_creacion).text.toString()
        val nuevaSede = findViewById<EditText>(R.id.input_update_sede).text.toString()

        var marcasRef = db.collection("marcas").document(idMarca)
        marcasRef.set(
            hashMapOf(
                "nombre" to nuevoNombreMarca,
                "pais" to nuevoPais,
                "fechaCreacion" to nuevaFechaCreacion,
                "sede" to nuevaSede
            )
        )
    }

}


