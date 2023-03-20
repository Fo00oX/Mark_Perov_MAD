package com.example.lectureexamples.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.lectureexamples.models.getMovies

@Composable
fun FavoriteList() {
    Column{
        TopAppBar(title={ Text(text="Favorites") })
        LazyColumn{
            items(getMovies()){ movie->
                MovieRow(movie=movie)
            }
        }
    }
}