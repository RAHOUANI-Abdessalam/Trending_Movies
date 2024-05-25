package com.example.trendingmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import com.example.trendingmovies.databinding.MainActivityBinding
import com.example.trendingmovies.helpers.isInternetAvailable
import com.example.trendingmovies.models.Movie
import com.example.trendingmovies.models.MovieResponse
import com.example.trendingmovies.services.MovieApiService
import com.example.trendingmovies.services.MoviesApiInterface
import com.example.trendingmovies.views.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var mainView: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isInternetAvailable(this)) {
            showNoInternetDialog()
        } else {
            mainView = MainView(binding, this)
            mainView.initializeView()
            getMovieData { movies: List<Movie> ->
                mainView.setMovies(movies)
            }
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

    private fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setTitle("No Internet Connection")
            .setMessage("This app requires an internet connection. Please enable internet access and restart the app.")
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                finish() // Close the app
            }
            .show()
    }
}
