package com.example.trendingmovies.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton service class for creating and managing the Retrofit instance
class MovieApiService {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"  // Base URL for the API

        // Retrofit instance variable
        private var retrofit: Retrofit? = null

        // Function to get the Retrofit instance
        fun getInstance(): Retrofit {
            // If the retrofit instance is null, create a new one
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)  // Set the base URL for the API
                    .addConverterFactory(GsonConverterFactory.create())  // Add Gson converter to handle JSON deserialization
                    .build()
            }
            // Return the existing or newly created Retrofit instance
            return retrofit!!
        }
    }
}
