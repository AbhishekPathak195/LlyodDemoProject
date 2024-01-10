package com.example.llyoddemoproject.data.network

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import com.example.example.ImageAPIResponse
import com.example.llyoddemoproject.data.network.model.Data
import com.example.llyoddemoproject.data.network.model.LoginResponse
import com.example.llyoddemoproject.util.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject


class AppApiHelper @Inject constructor(
    @ApplicationContext val context: Context,
    private val apiHeader: ApiHeader,
) : ApiHelper {

    @Inject
    lateinit var serviceGenerator: ServiceGenerator

    override fun getApiHeader(): ApiHeader {

        return apiHeader
    }

    override fun updateToken(token: String) {
        serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader.apply {
            accessToken = token
        }

    }

    override fun login(email: String, password: String): Resource<LoginResponse> {
//        val service = serviceGenerator.service
//
//        val request = LoginRequest(email, password, "", "")
//
//        return when (val responseBodyPojo = processCall { service.login(request) }
//        ) {
//            is LoginResponse -> Success(data = responseBodyPojo)
//
//            else -> DataError(responseBodyPojo as String)
//        }
        val data = Data(
            "ab123ab12345",
            "abhiniet4@gmail.com", "User"
        )
        return Success(LoginResponse(data, "User login successful", true))

    }

    override suspend fun getDashboardData(): Resource<ImageAPIResponse> {
        //delay(3000)

       ///serviceGenerator.protectedApiHeader = getApiHeader().protectedApiHeader
        val service = serviceGenerator.service
        val response = service.getDashboardData();

        return when (val response = processCall { service.getDashboardData() }) {

            is ImageAPIResponse -> Success(response)
           else -> DataError(response as String)
        }

    }

   /* override fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            fetchAndInsertPopularMovies(movieApiService, popularMoviesDao, movieDao)
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
        // single source of truth we will emit data from db only and not directly from remote
        emit(Resource.Success(getPopularMoviesFromDb(movieDao)))
    }*/


    @VisibleForTesting(otherwise = PRIVATE)
    inline fun processCall(responseCall: () -> Response<*>): Any? {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            return NO_INTERNET_CONNECTION
        }
        return try {
          //  popularMoviesDao.insertPopularMovies(remotePopularMovies.toPopularMoviesEntity())
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                getResponseCodeString(responseCode)
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    public fun getResponseCodeString(responseCode: Int): String {
        if (responseCode in 400..499) {
            return CLIENT_SIDE_ERROR
        } else if (responseCode in 500..599) {
            return SERVER_SIDE_ERROR
        }
        return responseCode.toString()+SOMETHING_WENT_WRONG
    }

}