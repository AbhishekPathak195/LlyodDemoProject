package com.example.example


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos (
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