package com.example.trendingmovies.services

import com.example.trendingmovies.models.Movie
import com.example.trendingmovies.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Interface for defining API endpoints for movie-related data
interface MoviesApiInterface {

    // Endpoint to get a list of movies
    // Makes a GET request to the /3/discover/movie endpoint with the API key
    @GET("/3/discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    fun getMovieList(): Call<MovieResponse>

    // Endpoint to get details of a specific movie
    // Makes a GET request to the /3/movie/{movie_id} endpoint with the movie ID and API key
    @GET("/3/movie/{movie_id}?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    fun getMovieDetails(@Path("movie_id") id: Int): Call<Movie>
}
