package com.example.llyoddemoproject.ui.base

import com.example.llyoddemoproject.util.coroutines.DispatcherProvider


open class BaseViewModelUseCase<T>(private val useCase: T) : BaseViewModel<T>(useCase) {

    fun getUseCase(): T {

        return useCase
    }

}