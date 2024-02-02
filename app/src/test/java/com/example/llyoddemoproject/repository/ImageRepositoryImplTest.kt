package com.example.llyoddemoproject.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.llyoddemoproject.MockTestUtil
import com.example.llyoddemoproject.coroutines.MainCoroutinesRule
import com.example.llyoddemoproject.data.repository.ImageRepositoryImpl
import com.example.llyoddemoproject.data.service.ImageService
import com.example.llyoddemoproject.domain.repository.ImageRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImageRepositoryImplTest {

    private lateinit var repository: ImageRepository

    @MockK
    private lateinit var imageService: ImageService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getImageList returns list of images`() = runBlocking {
        // Given
        repository = ImageRepositoryImpl(imageService)

        // When
        coEvery { imageService.getImages() }.returns(MockTestUtil.getServiceResponse())

        // Invoke
        val imagesFlow = repository.getImageList()

        // Then
        MatcherAssert.assertThat(imagesFlow, CoreMatchers.notNullValue())

        val images = imagesFlow.first()
        MatcherAssert.assertThat(images, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(images.data, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(images.data?.size, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(images.data?.get(0)?.id, CoreMatchers.`is`(1))
    }

    @Test
    fun `test getImageList returns zero list of images`() = runBlocking {
        // Given
        repository = ImageRepositoryImpl(imageService)
        val imagesList = MockTestUtil.getServiceZeroResponse()

        // When
        coEvery { imageService.getImages() }.returns(imagesList)

        // Invoke
        val imagesFlow = repository.getImageList()

        // Then
        MatcherAssert.assertThat(imagesFlow, CoreMatchers.notNullValue())

        val images = imagesFlow.first()
        MatcherAssert.assertThat(images, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(images.data, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            images.data?.size,
            CoreMatchers.`is`(imagesList.body()?.photos)
        )
    }

    @After
    fun tearDown() {

    }
}