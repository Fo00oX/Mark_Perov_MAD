package com.example.lectureexamples.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lectureexamples.R
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies

@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Greeting()
            Text(
                style = MaterialTheme.typography.h6,
                text= "Movie List"
            )
            MyList(navController)
        }
        //MyList()
        //Greeting()
        //WelcomeText(modifier = Modifier.padding(16.dp), text = "welcome to my app!")
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
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar2),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Show details")
            }
        }
    }
}

@Preview
@Composable
fun WelcomeText(modifier: Modifier = Modifier, text: String = "default") {
    Row(
        modifier = modifier
            .padding(16.dp)
            .background(Color.Blue)
            .fillMaxWidth()
    ) {
        Text(modifier = modifier, text = "Hola")
        Text(text = text)
    }

}

@Preview
@Composable
fun Greeting() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember {
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it},
            label = { Text("Name")}
        )


        /*
        // step 2 - add a mutableStateOf to fire the event for recomposition

       var name = mutableStateOf("")   // use a state holder to register changes
        // var name  by mutableStateOf("")
        Text(text = "Hello ${name.value}!")   // get value of state holder object

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },    // change its value accordingly
            label = { Text("Name")}
        )
        */



        /*
        // step 3 - use remember
        var name by remember {         // use remember to skip overwriting after first composition
            mutableStateOf("")
        }

        Text(text = "Hello ${name}!")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name")}
        )

         */
    }
}