package com.example.llyoddemoproject.data.repos

import com.example.example.ImageAPIResponse
import com.example.llyoddemoproject.data.network.ApiHelper
import com.example.llyoddemoproject.data.network.DataError
import com.example.llyoddemoproject.data.network.Resource
import com.example.llyoddemoproject.data.network.model.LoginResponse
import com.example.llyoddemoproject.data.prefs.PreferencesHelper
import com.example.llyoddemoproject.ui.base.BaseRepository
import com.example.llyoddemoproject.util.LoggedInMode
import com.example.llyoddemoproject.util.coroutines.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    appDispatcher: DispatcherProvider,
    apiHelper: ApiHelper,
    preferencesHelper: PreferencesHelper,
) : BaseRepository(appDispatcher,apiHelper, preferencesHelper) {

    fun logout() {

        flow<Unit> {
            setUserAsLoggedOut()
        }.flowOn(getAppDispatcher().computation())

    }

    suspend fun getDashboardData(): Resource<ImageAPIResponse> {
        return getApiHelper().getDashboardData()
    }


    private fun setUserAsLoggedOut() {
        getPreferencesHelper().updateUserInfo(
            null,
            null,
            LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
            null,
            null,
            null
        )
    }


}