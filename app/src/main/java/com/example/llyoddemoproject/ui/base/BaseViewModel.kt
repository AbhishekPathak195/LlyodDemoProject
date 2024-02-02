package com.example.llyoddemoproject.ui.base

import androidx.lifecycle.*
import com.example.llyoddemoproject.data.model.RequestException
import java.net.HttpURLConnection

open class BaseViewModel : LifecycleObserver, ViewModel() {

    val loading: LiveData<Boolean>
        get() = _loading.distinctUntilChanged()

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val unauthorized: LiveData<Boolean> get() = _unauthorized
    private val _unauthorized: MutableLiveData<Boolean> = MutableLiveData()

    val error: LiveEvent<String> get() = _error
    private val _error: LiveEvent<String> = LiveEvent()

    protected fun onCallError(error: Throwable) {
        checkIsUnauthorized(error)
        setError(error.message.orEmpty())
    }

    protected fun setLoading(isLoading: Boolean) = _loading.postValue(isLoading)
    protected fun setError(errorMessage: String) = _error.postValue(errorMessage)
    private fun checkIsUnauthorized(error: Throwable) {
        if (error is RequestException && error.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            _unauthorized.postValue(true)
        }
    }
}