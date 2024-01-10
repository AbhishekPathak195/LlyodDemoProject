package com.example.llyoddemoproject.ui.dashboard

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.example.ImageAPIResponse
import com.example.example.Photos
import com.example.llyoddemoproject.data.network.DataError
import com.example.llyoddemoproject.data.network.Success
import com.example.llyoddemoproject.ui.base.BaseComponentActivity
import com.example.llyoddemoproject.ui.dashboard.compose.BottomNavData
import com.example.llyoddemoproject.ui.dashboard.compose.TopBar
import com.example.llyoddemoproject.ui.login.LoginActivity
import com.example.llyoddemoproject.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseComponentActivity<DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()

    @ExperimentalFoundationApi
    @Composable
    override fun ProvideCompose() {

        val dashboardData = remember {
            mutableStateOf(ImageAPIResponse().photos)
        }

        val userId = remember { mutableStateOf("") }

        observe(viewModel.userIdData) {
            when (it) {
                is Success -> {
                    userId.value = it.data!!
                }
                else -> {}
            }
        }

        observe(viewModel.dashboardData) {
            when (it) {
                is DataError -> {

                }
                is Success -> {
                    if(it.data?.success == true){
                        it.data.let {
                            dashboardData.value = it!!.photos
                        }
                    }
                }
            }
        }

        observe(viewModel.logoutData) {
            when (it.getContentIfNotHandled()) {
                is DataError -> println()
                is Success -> {
                    startActivity<LoginActivity>()
                    finish()
                }
                else -> {}
            }

        }

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        SetUpDashboardCompose(dashboardData.value,
            userId.value,
            currentRoute,
            {
                navController.navigate(it) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            }) {

            NavHost(navController = navController,
                startDestination = BottomNavData.Home.route) {

                composable(route = BottomNavData.Home.route) {
                    DashboardContent(dashboardData.value)
                }

                composable(route = BottomNavData.History.route) {
                   Text(text = "History",
                   modifier = Modifier.fillMaxWidth(),
                   textAlign = TextAlign.Center)
                }

                composable(route = BottomNavData.Other.route) {
                      Text(text = "Other",
                          modifier = Modifier.fillMaxWidth(),
                          textAlign = TextAlign.Center)

                }



            }
        }


    }

    @ExperimentalFoundationApi
    @Composable
    private fun SetUpDashboardCompose(
        dashboardData: List<Photos>,
        userId: String,
        currentRoute: String?,
        onItemClick: (String)->Unit={},
        SetupNavHost: @Composable () -> Unit,
    ) {
        val scaffoldState =
            rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()

        Scaffold(scaffoldState = scaffoldState,
            topBar = {
                TopBar(scope,
                    scaffoldState)
            },
            drawerShape = RoundedCornerShape(0.dp),
            drawerContent = {
                DrawerCompose(scope, scaffoldState,
                    userId) { viewModel.logout() }
            },
            /*bottomBar = {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.onSecondary,
                    contentColor = MaterialTheme.colors.onSecondary
                ) {

                    val items = BottomNavData.values()
                    items.forEach {
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(it.iconId),
                                    contentDescription = it.title
                                )
                            },
                            label = { Text(text = it.title) },
                            selectedContentColor = MaterialTheme.colors.secondary,
                            unselectedContentColor = MaterialTheme.colors.primary,
                            alwaysShowLabel = true,
                            selected = currentRoute == it.route,
                            onClick = {
                                onItemClick(it.route)

                            }
                        )
                    }

                }


            }*/)  {

            SetupNavHost()

        }
    }


    @ExperimentalFoundationApi
    @Preview(showBackground = true,
        uiMode = UI_MODE_NIGHT_NO)
    @Composable
    override fun ProvideComposeLightPreview() {

        SetUpDashboardCompose(ImageAPIResponse().photos, "userId",
            "") {

            DashboardContent(ImageAPIResponse().photos)
        }
    }


}

