package com.example.llyoddemoproject.data.network

import com.example.example.ImageAPIResponse
import com.example.llyoddemoproject.data.network.model.LoginResponse


interface ApiHelper {

    fun getApiHeader(): ApiHeader?
    fun updateToken(token: String)
    fun login(email: String, password: String): Resource<LoginResponse>

    suspend fun getDashboardData(): Resource<ImageAPIResponse>
}
