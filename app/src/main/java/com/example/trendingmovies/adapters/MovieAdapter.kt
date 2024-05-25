package com.example.trendingmovies.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.trendingmovies.models.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.trendingmovies.views.DetailsActivity
import com.example.trendingmovies.databinding.MovieItemBinding
import com.example.trendingmovies.helpers.extractYear

// Adapter for the RecyclerView to display a list of movies.
class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // ViewHolder class to hold references to the views of each item in the RecyclerView.
    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        // Function to bind movie data to the views in the ViewHolder.
        fun bindMovie(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.movieReleaseYear.text = movie.release?.let { extractYear(it) } // Extracting and displaying the release year of the movie.
            Glide.with(binding.root.context).load(IMAGE_BASE + movie.poster).into(binding.moviePoster) // Loading movie poster image using Glide library.

            // Setting a click listener to open DetailsActivity when a movie item is clicked.
            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("MOVIE_ID", movie.id) // Passing movie ID to DetailsActivity.
                }
                context.startActivity(intent)
            }
        }
    }

    // Creating ViewHolder instances.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    // Getting the total number of movies in the list.
    override fun getItemCount(): Int = movies.size

    // Binding movie data to ViewHolder at the specified position.
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }
}
