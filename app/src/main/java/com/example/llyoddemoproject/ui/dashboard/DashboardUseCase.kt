package com.example.llyoddemoproject.ui.dashboard

import com.example.example.ImageAPIResponse
import com.example.llyoddemoproject.data.network.Resource
import com.example.llyoddemoproject.data.repos.DashboardRepository
import com.example.llyoddemoproject.ui.base.BaseUseCase
import com.example.llyoddemoproject.util.coroutines.DispatcherProvider
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    appDispatcher: DispatcherProvider,
    dashboardRepository: DashboardRepository
) : BaseUseCase<DashboardRepository>(dashboardRepository, appDispatcher) {
   //add dashboard business logic
   suspend fun getDashboardData(): Resource<ImageAPIResponse> {

        return getRepository().getDashboardData()
    }

    fun getUserId(): String {

        return getRepository().getUserId()
    }

    fun logout(){
        getRepository().logout()

    }

}