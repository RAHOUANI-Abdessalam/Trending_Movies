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

    // View binding for activity_main layout
    private lateinit var binding: MainActivityBinding

    // Instance of MainView to handle view initialization and data setting
    private lateinit var mainView: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check for internet connectivity
        if (!isInternetAvailable(this)) {
            // Show a dialog if there's no internet connection
            showNoInternetDialog()
        } else {
            // Initialize the view and fetch movie data if internet is available
            mainView = MainView(binding, this)
            mainView.initializeView()
            getMovieData { movies: List<Movie> ->
                mainView.setMovies(movies)
            }
        }
    }

    // Function to fetch movie data from the API
    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MoviesApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            // Handle failure in API call
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle the failure case
            }

            // Handle successful API response
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    callback(it.movies)
                }
            }
        })
    }

    // Function to show a dialog if there's no internet connection
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
