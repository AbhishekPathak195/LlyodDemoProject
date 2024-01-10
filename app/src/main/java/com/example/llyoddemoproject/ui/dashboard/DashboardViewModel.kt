package com.example.llyoddemoproject.ui.dashboard

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example.ImageAPIResponse
import com.example.llyoddemoproject.data.network.Resource
import com.example.llyoddemoproject.data.network.Success
import com.example.llyoddemoproject.ui.base.BaseViewModelUseCase
import com.example.llyoddemoproject.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    dashboardUseCase: DashboardUseCase
) : BaseViewModelUseCase<DashboardUseCase>(dashboardUseCase) {

    @VisibleForTesting(otherwise = PRIVATE)
    val dashboardDataPrivate = MutableLiveData<Resource<ImageAPIResponse>>()
    val dashboardData: LiveData<Resource<ImageAPIResponse>> get() = dashboardDataPrivate


    @VisibleForTesting(otherwise = PRIVATE)
    val userIdDataPrivate = MutableLiveData<Resource<String>>()
    val userIdData: LiveData<Resource<String>> get() = userIdDataPrivate


    @VisibleForTesting(otherwise = PRIVATE)
    val logoutPrivate = MutableLiveData<SingleEvent<Resource<Boolean>>>()
    val logoutData: LiveData<SingleEvent<Resource<Boolean>>> get() = logoutPrivate

    init {
        getDashBoarData()
    }

    fun getDashBoarData() {

        viewModelScope.launch(exceptionHandler) {
            showLoading()

            val dashboardData = getUseCase().getDashboardData()
            dashboardDataPrivate.value = dashboardData

            val userId = getUseCase().getUserId()
            userIdDataPrivate.value = Success(userId)

            hideLoading()

        }
    }

    fun logout() {

        viewModelScope.launch(exceptionHandler) {

            getUseCase().logout()
            logoutPrivate.value = SingleEvent(Success(true))

        }

    }


}