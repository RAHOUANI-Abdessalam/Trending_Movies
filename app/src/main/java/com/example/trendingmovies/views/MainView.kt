package com.example.trendingmovies.views

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendingmovies.adapters.MovieAdapter
import com.example.trendingmovies.databinding.MainActivityBinding
import com.example.trendingmovies.models.Movie

class MainView(private val binding: MainActivityBinding, private val context: Context) {

    fun initializeView() {
        binding.rvMoviesList.layoutManager = LinearLayoutManager(context)
        binding.rvMoviesList.setHasFixedSize(true)
    }

    fun setMovies(movies: List<Movie>) {
        binding.rvMoviesList.adapter = MovieAdapter(movies)
    }
}
