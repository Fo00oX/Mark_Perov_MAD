package com.example.movieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var year: String = "",
    var genre: String = "",
    var director: String = "",
    var actors: String = "",
    var plot: String = "",
    var images: List<String> = listOf(""),
    var rating: Float = 0.0f,
    var isFavorite: Boolean = false,
)