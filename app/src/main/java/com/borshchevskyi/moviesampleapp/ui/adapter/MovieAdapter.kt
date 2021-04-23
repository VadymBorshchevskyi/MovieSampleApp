package com.borshchevskyi.moviesampleapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.borshchevskyi.domaine.entity.Movie
import com.borshchevskyi.moviesampleapp.databinding.ItemMovieBinding

class HomeAdapter(
        private var movies : List<Movie> = arrayListOf(),
        private val itemClick : (Movie) -> Unit
): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun update(movies : List<Movie>){
        this.movies = movies
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : Movie){
            binding.title.text = movie.title
            binding.root.setOnClickListener { itemClick.invoke(movie) }
        }
    }
}