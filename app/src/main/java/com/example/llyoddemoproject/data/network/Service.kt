package com.example.llyoddemoproject.data.network


import com.example.example.ImageAPIResponse
import com.example.llyoddemoproject.data.network.model.*
import retrofit2.Response
import retrofit2.http.*


interface Service {

    @POST("login")
    fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("sample-data/photos")
    suspend fun getDashboardData(): Response<ImageAPIResponse>

}
