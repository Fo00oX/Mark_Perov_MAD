package com.example.movieapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.navigation.ScreenRoute

/**

A composable function that displays a simple app bar with a title and a back button that navigates to the previous screen.
 [title] The title to be displayed on the app bar.
 [navController] The navigation controller used to navigate to the previous screen when the back button is clicked.
 */
@Composable
fun SimpleAppBar(title: String = "Movies", navController: NavHostController) {
    Row(modifier = Modifier
        .background(Color.Black)
        .fillMaxWidth()
        .padding(11.dp),
        horizontalArrangement = Arrangement.Start,
    ){
        Row {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return", tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(ScreenRoute.HomeScreenRoute.route) {
                        popUpTo(ScreenRoute.HomeScreenRoute.route) {
                            inclusive = true
                        }
                    }
                }),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(title, style = MaterialTheme.typography.h6, color = Color.White)
        }
    }
}