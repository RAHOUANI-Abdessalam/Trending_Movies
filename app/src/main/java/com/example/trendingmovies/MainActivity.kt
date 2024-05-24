package com.example.trendingmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trendingmovies.databinding.MainActivityBinding
import com.example.trendingmovies.models.Movie
import com.example.trendingmovies.models.MovieResponse
import com.example.trendingmovies.services.MovieApiService
import com.example.trendingmovies.services.MoviesApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMoviesList.layoutManager = LinearLayoutManager(this)
        binding.rvMoviesList.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            binding.rvMoviesList.adapter = MovieAdapter(movies)
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MoviesApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle the failure case
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    callback(it.movies)
                }
            }
        })
    }
}
