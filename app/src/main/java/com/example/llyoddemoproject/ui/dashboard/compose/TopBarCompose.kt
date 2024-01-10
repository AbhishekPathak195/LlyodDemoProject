package com.example.llyoddemoproject.ui.dashboard.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.llyoddemoproject.R
import com.example.llyoddemoproject.ui.theme.CoinTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
) {

    TopAppBar(title = {

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(end = dimensionResource(id = R.dimen.dp_15)),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.dashboard),
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.subtitle2)

    },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { scaffoldState.drawerState.open() }
            }) {
                Icon(Icons.Default.Menu, "",
                    tint = MaterialTheme.colors.onSecondary)
            }
        },
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 0.dp
    )


}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProvideComposeLightPreview() {
    CoinTheme {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()
        TopBar(scope, scaffoldState)
    }
}
