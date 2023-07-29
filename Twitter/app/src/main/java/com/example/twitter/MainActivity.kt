package com.example.twitter

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val arregloTweets = DBMemoria.arregloTweets

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRVTweets(R.id.rv_tweet, arregloTweets)
        val botonIrMensajes = findViewById<ImageButton>(R.id.btn_messages)
        botonIrMensajes.setOnClickListener {
            irActividad(Mensajes::class.java)
        }
    }

    fun initRVTweets(reference: Int, arreglo: List<Tweet>){
        val recyclerView = findViewById<RecyclerView>(reference)
        val adapter = RVAdapterTweet(
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