package com.example.mvvmKotlinJetpackCompose


import com.example.example.ImageAPIResponse
import com.example.llyoddemoproject.data.network.DataError
import com.example.llyoddemoproject.data.network.Resource
import com.example.llyoddemoproject.data.network.Success
import com.example.llyoddemoproject.data.network.model.LoginResponse
import com.example.llyoddemoproject.util.NO_INTERNET_CONNECTION
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.File


class TestDataClassGenerator {

    val moshi = Moshi.Builder().build()

    //generic function to  generate data classes from json file path
    private inline fun <reified T> buildDataClassFromJson(json: String): T {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        return jsonAdapter.fromJson(json)!!
    }



    fun getSuccessLoginResponse(): Resource<LoginResponse> {
        val jsonString = getJson("LoginApiResponse.json")

        return Success(buildDataClassFromJson(jsonString))
    }



    fun getFailedLoginResponse():Resource<LoginResponse> {
        val jsonString = getJson("LoginFailedApiResponse.json")
        return Success(buildDataClassFromJson(jsonString))
    }


    fun getSuccessDashboardResponse(): Resource<ImageAPIResponse> {
        val data = ImageAPIResponse()
        return Success(data)
    }

    fun getNoNetworkError():Resource<Any> {
        return DataError(NO_INTERNET_CONNECTION)
    }

    private fun getJson(resourceName: String): String {
        val file = File("src/test/resources/$resourceName")
        return String(file.readBytes())
    }

}
