package com.example.llyoddemoproject.domain.utils


sealed class ResultData<T>(val data: T? = null, val message: String? = null, val errorCode: Int? = 0) {
    class Loading<T>(data: T? = null) : ResultData<T>(data)
    class Success<T>(data: T?) : ResultData<T>(data)
    class Error<T>(message: String, data: T? = null, errorCode: Int) : ResultData<T>(data, message,errorCode)
}
