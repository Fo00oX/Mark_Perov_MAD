package com.example.lectureexamples.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.navigation.DetailTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, movie: Movie) {
    Scaffold(
        topBar = { DetailTopBar(navController, movie.title) }
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                elevation = 5.dp
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .aspectRatio(16f / 9f)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = rememberImagePainter(movie.images[0]),
                            contentDescription = movie.title,
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier =
                            Modifier.fillMaxSize().padding(10.dp),
                            contentAlignment =
                            Alignment.TopEnd
                        ) {
                            Icon(
                                tint =
                                MaterialTheme.colors.secondary,
                                imageVector =
                                Icons.Default.FavoriteBorder,
                                contentDescription =
                                "Add to favorites"
                            )
                        }
                    }
                }
            }
            ImageList(movie.images)
        }
    }
}
@Composable
fun ImageList(images: List<String>) {
    LazyRow {
        items(images) { image ->
            Card(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(image),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}