package com.example.llyoddemoproject.service

import com.example.llyoddemoproject.coroutines.MainCoroutinesRule
import com.example.llyoddemoproject.data.service.ImageService
import com.example.llyoddemoproject.di.NetworkModuleTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ImageServiceTest : NetworkModuleTest<ImageService>() {

    private lateinit var apiService: ImageService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        apiService = createService(ImageService::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test getImageData() returns list of Images`() = runBlocking {
        // Given
        enqueueResponse("/image_response.json")

        // Invoke
        val response = apiService.getImages()
        val responseBody = requireNotNull(response.body()?.photos)
        mockWebServer.takeRequest()

        // Then
        MatcherAssert.assertThat(responseBody[0].id, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(
            responseBody[0].url,
            CoreMatchers.`is`("https://api.slingacademy.com/public/sample-photos/1.jpeg")
        )
        MatcherAssert.assertThat(
            responseBody[0].title,
            CoreMatchers.`is`("Defense the travel audience hand")
        )
        MatcherAssert.assertThat(
            responseBody[0].description,
            CoreMatchers.`is`("Few address take for special development white career")
        )
        MatcherAssert.assertThat(responseBody[0].user, CoreMatchers.`is`(20))
    }
}