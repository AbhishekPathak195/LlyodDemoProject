package com.example.llyoddemoproject.ui.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.llyoddemoproject.data.model.response.LocalResponse
import dagger.hilt.android.AndroidEntryPoint
import com.example.llyoddemoproject.ui.detail.DetailScreen
import com.example.llyoddemoproject.ui.lists.ListScreen
import com.example.llyoddemoproject.ui.theme.ImagesApplicationTheme
import com.example.llyoddemoproject.util.NavDestinations

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagesApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinations.LIST_SCREEN,
                    ) {
                        composable(NavDestinations.LIST_SCREEN) {
                            ListScreen(navController)
                        }
                        composable("${NavDestinations.DETAIL_SCREEN}/{newsId}") {
                            it.arguments?.getString("newsId")?.toInt()?.let { newsId ->
                                DetailScreen(newsId, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImagesApplicationTheme {
        ListScreen(
            navController = rememberNavController(),
            imagesList = LocalResponse.mock().photos ?: emptyList()
        )
    }
}