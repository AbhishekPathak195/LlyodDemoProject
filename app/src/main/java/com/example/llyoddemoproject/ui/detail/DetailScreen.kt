package com.example.llyoddemoproject.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.llyoddemoproject.data.model.response.LocalResponse
import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.ui.base.AppComposable
import com.example.llyoddemoproject.ui.composable.WebPageView
import com.example.llyoddemoproject.ui.theme.ImagesApplicationTheme

@Composable
fun DetailScreen(
    newsId: Int,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val images by viewModel.imageLive.observeAsState(initial = null)
    val showSmartMode by viewModel.showSmartMode.observeAsState(initial = true)
    viewModel.getImagesById(newsId)

    AppComposable(
        viewModel = viewModel,
        content = {
            DetailScreen(
                navController,
                images,
                showSmartMode,
                toggleMode = { viewModel.toggleMode() })
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    navController: NavController,
    images: Images?,
    showSmartMode: Boolean,
    toggleMode: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        images?.title ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = toggleMode
                    ) {
                        Text(text = if (showSmartMode) "WEB" else "SMART", color = Color.White)
                    }
                }
            )
        }
    ) {
        images?.let {
            if (showSmartMode) {
                SmartView(images = it)
            } else {
                WebPageView(urlToRender = it.url!!)
            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    ImagesApplicationTheme {
        DetailScreen(
            navController = rememberNavController(),
            images = LocalResponse.mock().photos?.firstOrNull(),
            showSmartMode = true,
            toggleMode = {}
        )
    }
}
