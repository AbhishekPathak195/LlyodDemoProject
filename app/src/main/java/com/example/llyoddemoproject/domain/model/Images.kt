package com.example.llyoddemoproject.domain.model

import com.squareup.moshi.Json

data class Images(
    @Json(name = "url")
    var url: String? = null,
    @Json(name = "title")
    var title: String?= null,
    @Json(name = "user")
    var user: Int?= null,
    @Json(name = "description")
    var description: String?= null,
    @Json(name= "id")
    var id: Int?= null
)