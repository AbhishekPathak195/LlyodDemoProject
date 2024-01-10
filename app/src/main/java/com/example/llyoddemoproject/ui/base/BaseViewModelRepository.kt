package com.example.llyoddemoproject.ui.base

import com.example.llyoddemoproject.util.coroutines.DispatcherProvider


open class BaseViewModelRepository<T:BaseRepository>(repo : T )
    : BaseViewModel<T>(repo){

    fun getRepository(): T {

        return anyType
    }


}