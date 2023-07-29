package com.example.twitter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapterMessage(
    private val context: MensajesView,
    private val listaMensajes: List<Message>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RVAdapterMessage.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val usernameTextView: TextView
        val username2TextView: TextView
        val profilePictureView: ImageView
        val message: TextView
        val time: TextView
        init {
            usernameTextView = view.findViewById(R.id.tv_username)
            username2TextView = view.findViewById(R.id.tv_username_2)
            message = view.findViewById(R.id.tv_mensaje)
            profilePictureView = view.findViewById(R.id.iv_profile_picture)
            time = view.findViewById(R.id.tv_time)
        }
    }
    //Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_message,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    //Setear datos iteración al iniciar el adaptador
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mensajeActual = this.listaMensajes[position]
        holder.message.text = mensajeActual.message
        holder.usernameTextView.text = mensajeActual.user
        holder.username2TextView.text = mensajeActual.user2
        holder.profilePictureView.setImageResource(mensajeActual.photo)
        holder.time.text = "∙ ${mensajeActual.time} ∙"
    }
    //Tamaño del arreglo
    override fun getItemCount(): Int {
        return this.listaMensajes.size
    }
}