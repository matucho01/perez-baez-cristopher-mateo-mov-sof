package com.example.examen1bmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen1bmoviles.view.ListViewMarca

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIniciar = findViewById<Button>(
            R.id.btn_empezar
        )
        botonIniciar.setOnClickListener {
            irActividad(ListViewMarca::class.java)
        }
    }

    private fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)

    }
}