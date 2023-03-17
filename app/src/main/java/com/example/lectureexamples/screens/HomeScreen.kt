package com.example.lectureexamples.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.navigation.TopBar
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopBar(navController)
            MyList(navController)
        }
    }
}


@Preview
@Composable
fun MyList(navController: NavController = rememberNavController(),
           movies: List<Movie> = getMovies()){
    LazyColumn{
        items(movies) {movie ->
            MovieRow(
                movie = movie,
            )  { movieId ->
                Log.d("MyList", "item clicked $movieId")
                // navigate to detailscreen
                navController.navigate("detail/$movieId")
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    val showDetails = remember { mutableStateOf(false) }
    val iconRotation = animateFloatAsState(
        targetValue = if (showDetails.value) 180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    //val randomIndex = remember { Random.nextInt(movie.images.indices) }



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
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(movie.images[0]),
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

            Row(
                modifier =
                Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement =
                Arrangement.SpaceBetween
            ) {
                Text(movie.title, style =
                MaterialTheme.typography.h6)
                IconButton(onClick =
                { showDetails.value =
                    !showDetails.value }) {
                    Icon(
                        imageVector =
                        Icons.Default.KeyboardArrowUp,
                        contentDescription =
                        if (showDetails.value) "Hide details" else "Show details",
                        modifier =
                        Modifier.rotate(iconRotation.value)
                    )
                }
            }

            AnimatedVisibility(visible=
            showDetails.value) {
                // Display movie details here
                Column(modifier=
                Modifier.padding(8.dp)) {
                    Text("Director: ${movie.director}")
                    Spacer(modifier=
                    Modifier.height(4.dp))
                    Text("Release year: ${movie.year}")
                    Spacer(modifier=
                    Modifier.height(4.dp))
                    Text("Plot: ${movie.plot}")
                }
            }
        }
    }
}


