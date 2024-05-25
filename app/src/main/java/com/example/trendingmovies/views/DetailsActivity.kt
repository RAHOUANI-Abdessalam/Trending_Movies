package com.example.trendingmovies.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.trendingmovies.databinding.ActivityDetailsBinding
import com.example.trendingmovies.helpers.extractYear
import com.example.trendingmovies.models.Movie
import com.example.trendingmovies.services.MovieApiService
import com.example.trendingmovies.services.MoviesApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val TAG = "DetailsActivity"

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the movie ID from the intent
        val movieId = intent.getStringExtra("MOVIE_ID")
        if (movieId != null) {
            Log.d(TAG, "Movie ID: $movieId")
            getMovieDetails(movieId) // Fetch movie details using the retrieved ID
        } else {
            Log.e(TAG, "Movie ID is null")
        }
    }

    // Function to fetch movie details from the API
    private fun getMovieDetails(movieId: String) {
        val apiService = MovieApiService.getInstance().create(MoviesApiInterface::class.java)
        apiService.getMovieDetails(movieId.toInt()).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e(TAG, "Failed to fetch movie details", t)
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    response.body()?.let { movie ->
                        Log.d(TAG, "Movie Details: $movie")

                        // Set movie details to the respective views
                        binding.movieTitle.text = movie.title
                        binding.movieReleaseYear.text = movie.release?.let { extractYear(it) }
                        binding.movieOverview.text = movie.overview

                        // Load movie poster using Glide
                        Glide.with(this@DetailsActivity)
                            .load("https://image.tmdb.org/t/p/w500/" + movie.poster)
                            .into(binding.moviePoster)
                    } ?: run {
                        Log.e(TAG, "Movie details response body is null")
                    }
                } else {
                    Log.e(TAG, "Response is not successful: ${response.code()}")
                }
            }
        })
    }
}
