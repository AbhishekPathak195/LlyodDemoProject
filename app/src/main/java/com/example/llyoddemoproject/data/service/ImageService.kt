package com.example.llyoddemoproject.data.service

import com.example.llyoddemoproject.data.model.response.ImageResponse
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {
    @GET("photos")
    suspend fun getImages(): Response<ImageResponse>
}