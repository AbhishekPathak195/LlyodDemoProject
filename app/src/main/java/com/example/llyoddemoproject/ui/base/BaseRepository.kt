package com.example.llyoddemoproject.ui.base

import com.example.llyoddemoproject.data.network.ApiHelper
import com.example.llyoddemoproject.data.prefs.PreferencesHelper
import com.example.llyoddemoproject.util.coroutines.DispatcherProvider


open class BaseRepository(
    private val appDispatcher: DispatcherProvider,
    private val apiHelper: ApiHelper,
    private val preferencesHelper: PreferencesHelper
) {

    fun getAppDispatcher(): DispatcherProvider {
        return appDispatcher
    }

    fun getApiHelper(): ApiHelper {

        return apiHelper
    }

    fun getPreferencesHelper(): PreferencesHelper {
        return preferencesHelper
    }

    fun getUserId(): String {
        return getPreferencesHelper().getCurrentUserId() ?: ""

    }


}