package com.example.twitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val arregloTweets = DBMemoria.arregloTweets

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRVTweets(R.id.rv_tweet, arregloTweets)
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
}