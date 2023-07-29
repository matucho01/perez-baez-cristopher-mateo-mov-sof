package com.example.twitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MensajesView : AppCompatActivity() {
    private val arregloMessages = DBMemoria.arregloMensajes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensajes)

        initRVMessages(R.id.rv_message, arregloMessages)
        val botonIrHome = findViewById<ImageButton>(R.id.btn_home)
        botonIrHome.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    fun initRVMessages(reference: Int, arreglo: List<Message>){
        val recyclerView = findViewById<RecyclerView>(reference)
        val adapter = RVAdapterMessage(
            this,
            arreglo,
            recyclerView
        )
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.notifyDataSetChanged()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}