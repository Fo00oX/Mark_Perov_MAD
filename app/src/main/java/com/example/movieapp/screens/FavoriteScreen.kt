package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.components.SimpleAppBar
import com.example.movieapp.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController) {
    Column{
        SimpleAppBar(
            title="Favorites",
            onBackClick={navController.navigateUp()}
        )

        LazyColumn{
            items(getMovies()){ movie->
                MovieRow(movie=movie)
            }
        }
    }
}