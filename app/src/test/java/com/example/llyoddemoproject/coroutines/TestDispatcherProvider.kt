package com.example.mvvmKotlinJetpackCompose.util.coroutines

import com.example.llyoddemoproject.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestDispatcherProvider : DispatcherProvider {

    val testDispatcher = TestCoroutineDispatcher()

    override fun computation(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun io(): CoroutineDispatcher {

        return testDispatcher
    }


    override fun main(): CoroutineDispatcher {
        return testDispatcher
    }
}