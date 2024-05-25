package com.example.trendingmovies.views

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendingmovies.adapters.MovieAdapter
import com.example.trendingmovies.databinding.MainActivityBinding
import com.example.trendingmovies.models.Movie

// MainView class handles the view-related logic for MainActivity
class MainView(private val binding: MainActivityBinding, private val context: Context) {

    // Initializes the RecyclerView with a LinearLayoutManager and sets fixed size
    fun initializeView() {
        binding.rvMoviesList.layoutManager = LinearLayoutManager(context)
        binding.rvMoviesList.setHasFixedSize(true)
    }

    // Sets the movie data to the RecyclerView adapter
    fun setMovies(movies: List<Movie>) {
        binding.rvMoviesList.adapter = MovieAdapter(movies)
    }
}
