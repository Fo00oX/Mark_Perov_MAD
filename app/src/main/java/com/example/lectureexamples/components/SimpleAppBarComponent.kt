package com.example.lectureexamples.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable

@Composable
fun SimpleAppBar(
    title: String,
    onBackClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    isFavoriteScreen: Boolean = false
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            if (isFavoriteScreen) {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorites")
                }
            } else {
                IconButton(onClick = onFavoritesClick) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorites")
                }
            }
        }
    )
}