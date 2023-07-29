package com.example.twitter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapterTweet(
    private val context: MainActivity,
    private val listaTweets: List<Tweet>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RVAdapterTweet.MyViewHolder>()  {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val usernameTextView: TextView
        val username2TextView: TextView
        val profilePictureView: ImageView
        val tweet: TextView
        val time: TextView
        val comments: TextView
        val likes: TextView
        val retweets: TextView
        val viewsNumber: TextView
        init {
            usernameTextView = view.findViewById(R.id.tv_username)
            username2TextView = view.findViewById(R.id.tv_username_2)
            tweet = view.findViewById(R.id.tv_tweet)
            profilePictureView = view.findViewById(R.id.iv_profile_picture)
            time = view.findViewById(R.id.tv_time)
            comments = view.findViewById(R.id.tv_comments_number)
            likes = view.findViewById(R.id.tv_likes_number)
            retweets = view.findViewById(R.id.tv_retweets_number)
            viewsNumber = view.findViewById(R.id.tv_views_number)
        }
    }
    //Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_tweet,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    //Setear datos iteración al iniciar el adaptador
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tweetActual = this.listaTweets[position]
        holder.tweet.text = tweetActual.tweet
        holder.usernameTextView.text = tweetActual.user
        holder.username2TextView.text = tweetActual.user2
        holder.profilePictureView.setImageResource(tweetActual.photo)
        holder.likes.text = tweetActual.likes.toString()
        holder.comments.text = tweetActual.comments.toString()
        holder.retweets.text = tweetActual.retweets.toString()
        holder.viewsNumber.text = tweetActual.views.toString()
        holder.time.text = "∙ ${tweetActual.time} ∙"
    }
    //Tamaño del arreglo
    override fun getItemCount(): Int {
        return this.listaTweets.size
    }
}