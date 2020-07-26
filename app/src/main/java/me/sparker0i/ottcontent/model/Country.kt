package me.sparker0i.ottcontent.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = Constants.CODE) var code: String,
    @Json(name = Constants.NAME) var name: String
)