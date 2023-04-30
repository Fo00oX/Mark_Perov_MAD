package com.example.movieapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.data.models.Movie
import com.example.movieapp.ui.screens.defaultMovie

/**

A composable that represents a card view for displaying movie information including title, director, year,

genre, actors, plot, images, rating, and favorite status. By default, the movie information is set to a default

movie. Clicking on the card navigates to the details page for that movie.

 movie the [Movie] object containing the information to be displayed on the card view

 onFavoriteClick a callback function that is called when the favorite button is clicked. Updates the favorite

status of the movie and the [FavoritesViewModel].

 onItemClick a callback function that is called when the card view is clicked. Navigates to the details page

for the movie.

 [onDeleteClick] a callback function that is called when the delete button is clicked. Deletes the movie from

 movieViewModel and the FavoriteViewModel.

 [showDeleteIcon] a boolean indicating whether the delete icon should be displayed on the card view. Defaults

to true.
 */
@Composable
fun MovieCard(
    movie: Movie = defaultMovie,
    onFavoriteClick: (Movie) -> Unit = {},
    onItemClick: (String) -> Unit = {},
    onDeleteClick: (Movie) -> Unit = {},
    showDeleteIcon: Boolean = true
) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val showDetails = remember { mutableStateOf(false)
    }
    val iconRotation = animateFloatAsState(
        targetValue = if (showDetails.value) 180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    var deleteState by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onItemClick(movie.id.toString())
            },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = movie.images[0])
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                )
                val painterState = painter.state
                if (painterState is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }
                var favoriteState by remember {
                    mutableStateOf(movie.isFavorite)
                }
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painter,
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(
                            tint = MaterialTheme.colors.secondary,
                            imageVector =
                            if
                                    (favoriteState) Icons.Default.Favorite
                            else
                                Icons.Default.FavoriteBorder,
                            contentDescription = "Add to favorites",
                            modifier = Modifier
                                .clickable {
                                    favoriteState = !favoriteState
                                    onFavoriteClick(movie)
                                }
                        )
                        if (showDeleteIcon) {
                            Icon(
                                tint = Color.White,
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Movie",
                                modifier = Modifier
                                    .clickable {
                                        deleteState = !deleteState
                                        onDeleteClick(movie)
                                    }
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                Icon(
                    imageVector =
                    if
                            (expandedState) Icons.Default.KeyboardArrowDown
                    else
                        Icons.Default.KeyboardArrowUp,
                    contentDescription = "Show details",
                    modifier = Modifier
                        .rotate(iconRotation.value).size(36.dp)
                        .clickable(onClick = {
                            expandedState = !expandedState
                        })
                )
            }

            AnimatedVisibility(visible = expandedState) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text("Director: ", fontWeight = FontWeight.Bold)
                    Text(movie.director)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Release Date: ", fontWeight = FontWeight.Bold)
                    Text(movie.year)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Summary: ", fontWeight = FontWeight.Bold)
                    Text(movie.plot)
                }
            }
        }
    }
}
