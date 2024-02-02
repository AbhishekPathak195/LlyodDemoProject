package com.example.llyoddemoproject.domain.repository

import com.example.llyoddemoproject.data.model.response.ImageResponse
import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.domain.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImageList(): Flow<ResultData<List<Images>>>
    suspend fun getImageDetail(id: Int): Flow<ResultData<Images>>
}