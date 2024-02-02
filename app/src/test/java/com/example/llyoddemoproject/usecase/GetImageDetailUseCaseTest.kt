package com.example.llyoddemoproject.usecase

import com.example.llyoddemoproject.MockTestUtil
import com.example.llyoddemoproject.domain.repository.ImageRepository
import com.example.llyoddemoproject.domain.usecase.GetImageDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class GetImageDetailUseCaseTest {

    @MockK
    private lateinit var repository: ImageRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getImageDetail gives images details`() = runBlocking {

        val getImageDetailUseCase = GetImageDetailUseCase(repository)
        val mockImages = MockTestUtil.createEmptyImageDetail()


        coEvery { repository.getImageDetail(any()) }
            .returns(flowOf(mockImages))

        val imageDataFlow = getImageDetailUseCase.getImageDetail(1)

        MatcherAssert.assertThat(imageDataFlow, CoreMatchers.notNullValue())

        val imageDataState = imageDataFlow.first()
        MatcherAssert.assertThat(imageDataState, CoreMatchers.notNullValue())

        MatcherAssert.assertThat(imageDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            imageDataState.data,
            CoreMatchers.`is`(mockImages.data)
        )

    }

    @After
    fun tearDown() {
    }
}