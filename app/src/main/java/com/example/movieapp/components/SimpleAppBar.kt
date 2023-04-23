package com.example.movieapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.movieapp.screens.Screen


@Composable
fun SimpleAppBar(title: String = "Movies", navController: NavHostController) {
    Row(modifier = Modifier
        .background(Color.Blue)
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
    ){
        Row {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return", tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) {
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