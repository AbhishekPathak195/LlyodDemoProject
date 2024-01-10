package com.example.llyoddemoproject.data.network

import com.example.llyoddemoproject.BuildConfig
import com.example.llyoddemoproject.data.network.moshiFactories.MyKotlinJsonAdapterFactory
import com.example.llyoddemoproject.data.network.moshiFactories.MyStandardJsonAdapters
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

const val timeoutRead = 30
const val contentType = "Content-Type"
const val contentTypeValue = "application/json"
const val timeoutConnect = 30
const val baseUrl="https://api.slingacademy.com/v1/"
//https://api.slingacademy.com/v1/sample-data/photos

@Singleton
class ServiceGenerator  @Inject constructor(){

    @Inject
    lateinit var service : Service

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var protectedApiHeader:ApiHeader.ProtectedApiHeader

}