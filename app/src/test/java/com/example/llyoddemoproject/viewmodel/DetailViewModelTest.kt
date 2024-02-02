package com.example.llyoddemoproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.llyoddemoproject.coroutines.MainCoroutinesRule
import com.example.llyoddemoproject.MockTestUtil
import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.domain.usecase.GetImageDetailUseCase
import com.example.llyoddemoproject.ui.detail.DetailViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailViewModelTest {

    // Subject under test
    private lateinit var viewModel: DetailViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var imageDetailUseCase: GetImageDetailUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test fetchImageDetail() returns details of image`() = runBlocking {

        val imagesListObserver =
            mockk<Observer<Images>>(relaxed = true)
        val mockImages = MockTestUtil.createEmptyImageDetail()
        coEvery { imageDetailUseCase.getImageDetail(any()) }
            .returns(flowOf(mockImages))

        viewModel = DetailViewModel(imageDetailUseCase)
        viewModel.imageLive.observeForever(imagesListObserver)

        // Then
        coVerify(exactly = 1) { imageDetailUseCase.getImageDetail(any()) }
        verify { imagesListObserver.onChanged(match { it.url != null }) }
        verify { imagesListObserver.onChanged(match { it.description != null }) }
        verify { imagesListObserver.onChanged(match { it.id != null }) }
        verify { imagesListObserver.onChanged(match { it.title != null }) }
        verify { imagesListObserver.onChanged(match { it.user != null }) }

    }

    @After
    fun tearDown() {
    }

}