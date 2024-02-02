package com.example.llyoddemoproject.data.model

class RequestException(val code: Int, message: String) : Throwable(message)