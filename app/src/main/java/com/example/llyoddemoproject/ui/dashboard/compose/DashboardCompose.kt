package com.example.llyoddemoproject.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.example.Photos
import com.example.llyoddemoproject.R
import com.example.llyoddemoproject.data.others.MenuItem


@ExperimentalFoundationApi
@Composable
fun DashboardContent(photoData: List<Photos>) {

    LazyColumn {
        items(photoData) { item ->
            Card(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.dp_5))
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.medium,
                elevation = 5.dp,
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Row(
                    Modifier.padding(vertical = dimensionResource(id = R.dimen.dp_10), horizontal = dimensionResource(id = R.dimen.dp_5)).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.dp_100), dimensionResource(id = R.dimen.dp_100) ).fillMaxSize()
                            .clip(RoundedCornerShape(4)),
                        contentScale = ContentScale.Fit,
                        painter = rememberImagePainter(
                            data = item?.url,
                        ),

                        contentDescription = item?.description,
                        //modifier = Modifier.fillMaxSize()
                    )

                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dp_5)))
                    item.title?.let {
                        Text(
                            text = it,
                            color = Color.DarkGray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.caption,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun Menu(menuItems: List<MenuItem>, openActivity: (String) -> Unit) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
    ) {
        items( count = menuItems.size) { item ->
            MenuItemCompose(Modifier
                .padding(top = dimensionResource(R.dimen.dp_10)),
                iconId = menuItems.get(item).icon,
                title = menuItems.get(item).text,
                onClick = {
                    openActivity(it)

                }, color = MaterialTheme.colors.primary)

        }
    }
}

@Composable
fun MenuItemCompose(
    modifier: Modifier = Modifier, iconId: Int,
    title: String,
    onClick: (String) -> Unit,
    color: Color,
) {
    Column(modifier

        .clickable { onClick(title) },
        horizontalAlignment = Alignment.Start)
    {
        Image(modifier = Modifier
            .width(dimensionResource(R.dimen.dp_40))
            .height(dimensionResource(R.dimen.dp_40)),
            painter = painterResource(iconId),
            contentDescription = "")
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dp_5)))
        Text(
            text = title.uppercase(),
            color = color,
            style = MaterialTheme.typography.caption,
        )

    }

}

