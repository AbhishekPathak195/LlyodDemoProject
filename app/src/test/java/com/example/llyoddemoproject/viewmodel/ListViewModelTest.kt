package com.example.llyoddemoproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.llyoddemoproject.coroutines.MainCoroutinesRule
import com.example.llyoddemoproject.MockTestUtil
import com.example.llyoddemoproject.domain.model.Images
import com.example.llyoddemoproject.domain.usecase.GetImageListUseCase
import com.example.llyoddemoproject.ui.lists.ListViewModel
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
class ListViewModelTest {

    private lateinit var viewModel: ListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var imageListUseCase: GetImageListUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test fetchListImages() returns list of image`() = runBlocking {

        val imagesListObserver =
            mockk<List<Images>>(relaxed = true)
        val mockImages = MockTestUtil.createImagesList()
        coEvery { imageListUseCase.getImagesList() }
            .returns(flowOf(mockImages))

        viewModel = ListViewModel(imageListUseCase)
        viewModel.state.value.copy(imagesListObserver)

        // Then
        coVerify(exactly = 1) { imageListUseCase.getImagesList() }
        verify {
            imagesListObserver.contains((match { it.url != null }))
            imagesListObserver.contains((match { it.title != null }))
            imagesListObserver.contains((match { it.user != null }))
            imagesListObserver.contains((match { it.description != null }))
            imagesListObserver.contains((match { it.id != null }))
        }

        @After
        fun tearDown() {
        }
    }
}