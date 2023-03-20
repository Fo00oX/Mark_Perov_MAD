package com.example.lectureexamples.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lectureexamples.components.SimpleAppBar
import com.example.lectureexamples.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController) {
    Column{
        SimpleAppBar(
            title="Favorites",
            onBackClick={navController.navigateUp()},
            onFavoritesClick={},
            isFavoriteScreen=true
        )

        LazyColumn{
            items(getMovies()){ movie->
                MovieRow(movie=movie)
            }
        }
    }
}