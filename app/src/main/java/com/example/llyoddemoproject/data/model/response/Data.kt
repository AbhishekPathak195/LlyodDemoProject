package com.example.llyoddemoproject.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "token")
    var token: String,
    @Json(name = "userId")
    var userId: String,
    @Json(name = "userType")
    var userType: String
)