package com.example.example

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ImageAPIResponse (
  @Json(name = "success")
  var success: Boolean?= null,
  @Json(name = "total_photos")
  var totalPhotos: Int?= null,
  @Json(name = "message")
  var message: String?= null,
  @Json(name = "offset")
  var offset: Int?= null,
  @Json(name = "limit")
  var limit: Int?= null,
  @Json(name = "photos")
  var photos: List<Photos> = listOf()
)