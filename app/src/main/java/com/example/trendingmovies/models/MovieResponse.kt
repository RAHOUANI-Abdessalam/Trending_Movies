package com.example.trendingmovies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse (

    @SerializedName("results")
    val movies : List<Movie> // List of movies retrieved from the API response.

) : Parcelable{
    constructor() : this(mutableListOf())
}