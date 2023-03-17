package com.example.lectureexamples.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {

    movieId?.let {
        Text(text = "Hello Detailscreen $movieId")
    }



}