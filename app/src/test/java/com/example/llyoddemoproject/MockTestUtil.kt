package com.example.llyoddemoproject

import com.example.llyoddemoproject.data.model.response.ImageResponse
import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.domain.utils.ResultData
import com.squareup.moshi.Json
import retrofit2.Response


class MockTestUtil {
    companion object {
        fun createEmptyImageDetail(): ResultData<Images> {
            return ResultData.Success(
                Images(
                    url = "",
                    title = "",
                    user = 0,
                    description = "",
                    id = 0
                )
            )
        }
        fun createImagesList(): ResultData<List<Images>> {
            return ResultData.Success(
                createImageResultsList()
            )
        }
        fun getServiceResponse(): Response<ImageResponse> {
            return Response.success(
                ImageResponse(
                    success = true,
                    totalPhotos = 1,
                    message = "Success",
                    offset = 1,
                    limit = 1,
                    photos = createImageResultsList()
                )
            )
        }

        fun getServiceZeroResponse(): Response<ImageResponse> {
            return Response.success(
                ImageResponse(
                    success = true,
                    totalPhotos = 1,
                    message = "Success",
                    offset = 1,
                    limit = 1,
                    photos = emptyList()
                )
            )
        }
        private fun createImageResultsList() = listOf(
            Images(
                url = "https://api.slingacademy.com/public/sample-photos/1.jpeg",
                title = "Defense the travel audience hand",
                user = 28,
                id = 1,
                description = "Leader structure safe or black late wife newspaper her pick central forget single likely."
            )
        )


        private fun createImageData() = Images(
            url = "https://api.slingacademy.com/public/sample-photos/1.jpeg",
            title = "Defense the travel audience hand",
            user = 28,
            id = 1,
            description = "Leader structure safe or black late wife newspaper her pick central forget single likely."
        )

    }
}
