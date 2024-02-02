package com.example.llyoddemoproject.ui.lists

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.llyoddemoproject.R
import com.example.llyoddemoproject.data.model.response.LocalResponse
import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.ui.base.AppComposable
import com.example.llyoddemoproject.ui.theme.ImagesApplicationTheme
import com.example.llyoddemoproject.util.NavDestinations

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {

    val stateData = viewModel.state.value
    val newsList = stateData.imageList

    AppComposable(
        viewModel = viewModel,
        content = { ListScreen(navController, newsList) }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navController: NavController,
    imagesList: List<Images>?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Data List", fontSize = 17.sp) },
            )
        }
    )
    {
        LazyColumn {
            items(imagesList!!) { images ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${NavDestinations.DETAIL_SCREEN}/${images.id}")
                        },
                ) {
                    Column {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            painter = rememberImagePainter(
                                data = images.url,
                                builder = {
                                    placeholder(R.drawable.placeholder)
                                    error(R.drawable.placeholder)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                        Column(Modifier.padding(8.dp)) {
                            Text(
                                images.title ?: "",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 3
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ImagesApplicationTheme {
        ListScreen(
            navController = rememberNavController(),
            imagesList = LocalResponse.mock().photos ?: emptyList()
        )
    }
}