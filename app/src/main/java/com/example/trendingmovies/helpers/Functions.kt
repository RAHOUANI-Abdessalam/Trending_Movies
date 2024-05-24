package com.example.trendingmovies.helpers

fun extractYear(releaseDate: String): String {
    return releaseDate.substring(0, 4)
}