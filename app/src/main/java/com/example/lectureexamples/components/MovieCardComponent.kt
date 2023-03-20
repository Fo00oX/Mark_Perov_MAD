package com.example.lectureexamples.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lectureexamples.models.Movie

@Composable
fun MovieCard(movie: Movie, onItemClick: (String) -> Unit = {}) {
    val showDetails = remember { mutableStateOf(false) }
    val iconRotation = animateFloatAsState(
        targetValue = if (showDetails.value) 180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(movie.images[0]),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
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

            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)

                IconButton(onClick = { showDetails.value = !showDetails.value }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Show details",
                        modifier = Modifier.rotate(iconRotation.value).size(36.dp)
                    )
                }
            }

            AnimatedVisibility(visible = showDetails.value) {
                // Display movie details here
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Director: ", fontWeight = FontWeight.Bold)
                    Text(movie.director)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Release year: ", fontWeight = FontWeight.Bold)
                    Text(movie.year)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Plot: ", fontWeight = FontWeight.Bold)
                    Text(movie.plot)
                }
            }
        }
    }
}
