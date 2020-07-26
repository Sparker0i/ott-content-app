package me.sparker0i.ottcontent.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants

@JsonClass(generateAdapter = true)
data class Platform(
    @Json(name = Constants.PLATFORM_ID) var platformId: Int,
    @Json(name = Constants.LOCALIZED_NAME) var localizedName: String
)