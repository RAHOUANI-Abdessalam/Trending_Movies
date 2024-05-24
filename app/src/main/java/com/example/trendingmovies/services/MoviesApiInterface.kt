package com.example.trendingmovies.services

import com.example.trendingmovies.models.Movie
import com.example.trendingmovies.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiInterface {
    @GET("/3/discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/movie/{movie_id}?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    fun getMovieDetails(@Path("movie_id") id: Int): Call<Movie>

}