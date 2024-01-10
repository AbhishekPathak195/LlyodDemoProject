package com.example.llyoddemoproject.ui.dashboard.compose

import com.example.llyoddemoproject.R

sealed class NavDrawerItem(var route: String, var icon: Int, var title: Int) {

    object Dashboard : NavDrawerItem(
        "dashboard",
        R.drawable.ic_home,
        R.string.dashboard
    )

    object Logout : NavDrawerItem(
        "logout",
        R.drawable.ic_logout,
        R.string.logout
    )


}