package me.sparker0i.ottcontent.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants

@JsonClass(generateAdapter = true)
data class Show(
    @Json(name = Constants.ID) var id: Int,
    @Json(name = Constants.NAME) var name: String,
    @Json(name = Constants.DESCRIPTION) var description: String,
    @Json(name = Constants.TRAILER) var trailer: String
)