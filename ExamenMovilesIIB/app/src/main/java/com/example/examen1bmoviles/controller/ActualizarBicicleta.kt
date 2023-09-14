package com.example.examen1bmoviles.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.examen1bmoviles.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarBicicleta : AppCompatActivity() {
    private var idMarca: String? = ""
    private var idBicicleta: String? = ""
    private var modelo: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_bicicleta)


        idMarca = intent.getStringExtra("idMarca")
        idBicicleta = intent.getStringExtra("idBicicleta")
        modelo = intent.getStringExtra("modelo")

        findViewById<TextView>(R.id.txt_seleccionado_bicicleta).setText(modelo)

        consultarBicicletaVieja(idMarca!!, idBicicleta!!)

        val botonActualizarBicicleta = findViewById<Button>(R.id.btn_actualizar_bicicleta)

        botonActualizarBicicleta.setOnClickListener {
            actualizarBicicleta()
            finish()
        }
    }

    fun consultarBicicletaVieja(idMarca : String, idBicicleta : String){
        val db = Firebase.firestore
        val bicicletasReferencia = db.collection("marcas").document(idMarca).
            collection("bicicletas").document(idBicicleta)
        bicicletasReferencia
            .get()
            .addOnSuccessListener {

                findViewById<EditText>(R.id.input_update_modelo).setText(it.data?.get("modelo") as String?)
                findViewById<EditText>(R.id.input_update_tipo).setText(it.data?.get("tipo") as String?)
                val anioValue = it.data?.get("anio")
                if (anioValue is Long) {
                    val anio = anioValue.toInt()
                    findViewById<EditText>(R.id.input_update_anio).setText(anio.toString())
                }
                findViewById<EditText>(R.id.input_update_precio).setText((it.data?.get("precio") as Double?).toString())
                val disponibleValue = it.data?.get("disponible") as Boolean?
                val checkboxDisponible = findViewById<CheckBox>(R.id.input_update_disponible)

                // Verificar si la propiedad "disponible" es true o false y establecer el estado del checkbox en consecuencia
                checkboxDisponible.isChecked = disponibleValue ?: false

            }
    }

    private fun actualizarBicicleta() {
        val inputUpdateModelo = findViewById<TextView>(R.id.input_update_modelo).text.toString()
        val inputUpdateTipo = findViewById<TextView>(R.id.input_update_tipo).text.toString()
        val inputUpdateAnio = findViewById<TextView>(R.id.input_update_anio).text.toString().toInt()
        val inputUpdatePrecio = findViewById<TextView>(R.id.input_update_precio).text.toString().toDouble()
        val disponibleCheckbox = findViewById<CheckBox>(R.id.input_update_disponible)
        val inputUpdateDisponible = disponibleCheckbox.isChecked
            val db = Firebase.firestore
            val bicicletasReferencia =
                db.
                collection("marcas").document(idMarca!!).
                collection("bicicletas").document(idBicicleta!!)

            bicicletasReferencia.set(
                hashMapOf(
                    "modelo" to inputUpdateModelo,
                    "tipo" to inputUpdateTipo,
                    "anio" to inputUpdateAnio,
                    "precio" to inputUpdatePrecio,
                    "disponible" to inputUpdateDisponible
                )
            )
    }

}