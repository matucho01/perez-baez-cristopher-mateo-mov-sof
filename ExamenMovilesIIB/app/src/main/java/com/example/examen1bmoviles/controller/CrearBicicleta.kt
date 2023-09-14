package com.example.examen1bmoviles.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.examen1bmoviles.BaseDatosMemoria
import com.example.examen1bmoviles.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class CrearBicicleta : AppCompatActivity() {
    private var idMarca = String?:""
    private var idItemSeleccionado = 0
    private var nombreMarca = String?:""
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_bicicleta)

        idMarca = intent.getStringExtra("idMarca")!!
        nombreMarca = intent.getStringExtra("nombreMarca")!!

        findViewById<TextView>(R.id.txt_nombre_marca_interno).text = nombreMarca as String

        val botonAniadir = findViewById<Button>(R.id.btn_aniadir_bicicleta_interno)
        botonAniadir.setOnClickListener {
            if (validarDatos()) {
                crearBicicleta()
                finish()
            } else {
                mostrarSnackBar("Formato de datos ingresados incorrectos")
            }
        }
    }

    private fun validarDatos(): Boolean {
        val modelo = findViewById<TextView>(R.id.input_modelo).text.toString()
        val tipo = findViewById<TextView>(R.id.input_tipo).text.toString()
        val anio = findViewById<EditText>(R.id.input_anio).text.toString()
        val precio = findViewById<TextView>(R.id.input_precio).text.toString()

        // Validación input vacío
        if (modelo.isEmpty() || tipo.isEmpty() || anio.isEmpty() || precio.isEmpty()) {
            return false
        }
        // Validación formato incorrecto
        if (precio.toDoubleOrNull() == null) {
            return false
        }

        if(anio.toIntOrNull() == null){
            return false
        }
        return true
    }
    private fun mostrarSnackBar(mensaje: String) {
        val rootView = findViewById<TextView>(R.id.txt_nombre_marca_interno)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

    private fun crearBicicleta() {

        if (validarDatos()) {
            val modelo = findViewById<EditText>(R.id.input_modelo).text.toString()
            val tipo = findViewById<EditText>(R.id.input_tipo).text.toString()
            val anio = findViewById<EditText>(R.id.input_anio).text.toString().toInt()
            val precio = findViewById<EditText>(R.id.input_precio).text.toString().toDouble()
            val disponibleCheckbox = findViewById<CheckBox>(R.id.input_disponible)
            val disponible = disponibleCheckbox.isChecked

            val nuevaBicicleta = hashMapOf(
                "modelo" to modelo,
                "tipo" to tipo,
                "anio" to anio,
                "precio" to precio,
                "disponible" to disponible
            )

            val bicicletasRef = db.collection("marcas").document(idMarca.toString()).collection("bicicletas")

            bicicletasRef
                .add(nuevaBicicleta)
                .addOnSuccessListener { documentReference ->
                    mostrarSnackBar("Bicicleta creada satisfactoriamente con ID: ${documentReference.id}")
                    finish()
                }
                .addOnFailureListener { e ->
                    mostrarSnackBar("Error al crear la bicicleta: $e")
                }
        } else {
            mostrarSnackBar("Formato de datos ingresados incorrectos")
        }
    }

    fun mostrarNombreMarca() {
        val textViewMarca = findViewById<TextView>(R.id.txt_nombre_marca_interno)
        textViewMarca.text =
            BaseDatosMemoria.arregloMarca[idItemSeleccionado].nombre
    }

}