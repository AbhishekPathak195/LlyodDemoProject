package com.example.llyoddemoproject.ui.splash

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.llyoddemoproject.data.network.Resource
import com.example.llyoddemoproject.data.network.Success
import com.example.llyoddemoproject.data.repos.LoginRepository
import com.example.llyoddemoproject.ui.base.BaseViewModelRepository
import com.example.llyoddemoproject.util.LoggedInMode
import com.example.llyoddemoproject.util.SingleEvent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch


class SplashViewModel (
    loginRepository: LoginRepository,
) : BaseViewModelRepository<LoginRepository>(loginRepository) {

    @VisibleForTesting(otherwise = PRIVATE)
    val privateSingleEventOpenActivity =
        MutableLiveData<SingleEvent<Resource<Int>>>()


    val singleEventOpenActivity: LiveData<SingleEvent<Resource<Int>>> get() = privateSingleEventOpenActivity//activity

    @FlowPreview
    fun decideActivity() {
        showLoading()
        viewModelScope.launch(exceptionHandler) {

            getRepository().isUserLoggedIn()
                .collect {
                    hideLoading()
                    if (it == LoggedInMode.LOGGED_IN_MODE_SERVER.type) {
                        privateSingleEventOpenActivity.value = SingleEvent(Success(1))

                    } else {
                        privateSingleEventOpenActivity.value = SingleEvent(Success(2))

                    }
                }

        }

    }


}