package com.example.llyoddemoproject.domain.usecase

import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.domain.repository.ImageRepository
import com.example.llyoddemoproject.domain.utils.ResultData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImageDetailUseCase @Inject constructor(private val imageRepository: ImageRepository) {

    suspend fun getImageDetail(id: Int): Flow<ResultData<Images>> {
        return imageRepository.getImageDetail(id)
    }
}

