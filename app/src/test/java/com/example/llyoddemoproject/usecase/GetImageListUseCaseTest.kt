package com.example.llyoddemoproject.usecase

import com.example.llyoddemoproject.MockTestUtil
import com.example.llyoddemoproject.domain.repository.ImageRepository
import com.example.llyoddemoproject.domain.usecase.GetImageListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetImageListUseCaseTest {

    @MockK
    private lateinit var repository: ImageRepository
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }
    @Test
    fun `test getImagesList gives list of images`() = runBlocking {

        val getImageListUseCase = GetImageListUseCase(repository)
        val mockImages = MockTestUtil.createImagesList()

        coEvery { repository.getImageList() }
            .returns(flowOf(mockImages))

        val imageListFlow = getImageListUseCase.getImagesList()

        MatcherAssert.assertThat(imageListFlow, CoreMatchers.notNullValue())

        val imageListDataState = imageListFlow.first()
        MatcherAssert.assertThat(imageListDataState, CoreMatchers.notNullValue())

        MatcherAssert.assertThat(imageListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(imageListDataState.data?.size, `is`(mockImages.data?.size))
    }

}