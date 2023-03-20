package com.example.movieapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.components.MovieCard
import com.example.movieapp.components.SimpleAppBar
import com.example.movieapp.models.Movie

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, movie: Movie) {
    Scaffold(
        topBar = {
            SimpleAppBar(
                title = movie.title,
                onBackClick = { navController.navigateUp() },
            )
        }
    ) {
        Column {
            MovieCard(movie = movie)
            ImageList(movie.images)
        }
    }
}

@Composable
fun ImageList(images: List<String>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(images) { image ->
            Card(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(image),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}