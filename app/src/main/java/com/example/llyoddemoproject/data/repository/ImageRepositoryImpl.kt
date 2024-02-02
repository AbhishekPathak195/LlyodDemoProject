package com.example.llyoddemoproject.data.repository

import com.example.llyoddemoproject.data.service.ImageService
import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.domain.repository.ImageRepository
import com.example.llyoddemoproject.domain.utils.ResultData
import com.example.llyoddemoproject.util.Constants.ERROR_OCCURRED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.HttpURLConnection
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageService: ImageService
) : ImageRepository {

    private val imagesList: MutableList<Images> = mutableListOf()

    override suspend fun getImageList(): Flow<ResultData<List<Images>>> = flow {
        emit(ResultData.Loading())
        val apiResponse = imageService.getImages().body()
        if (apiResponse!!.success == true) {
            val imageList = apiResponse!!.photos ?: emptyList()
            imagesList.addAll(imageList)
            emit(ResultData.Success(imageList))
        } else {
            emit(
                ResultData.Error(
                    errorCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = ERROR_OCCURRED
                )
            )
        }
    }.catch {
        emit(
            ResultData.Error(
                errorCode = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ERROR_OCCURRED
            )
        )
    }.flowOn(Dispatchers.IO)


    override suspend fun getImageDetail(id: Int): Flow<ResultData<Images>> = flow {
        emit(ResultData.Loading())
        imagesList.find { it.id == id }?.let { images ->
            emit(ResultData.Success(images))
        } ?: run {
            //emit(Result.failure(Exception("An error occurred when get new detail")))
        }
    }.catch {
        emit(
            ResultData.Error(
                errorCode = HttpURLConnection.HTTP_INTERNAL_ERROR, message = ERROR_OCCURRED
            )
        )
    }.flowOn(Dispatchers.IO)

}

