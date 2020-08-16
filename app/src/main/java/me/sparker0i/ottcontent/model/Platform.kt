package me.sparker0i.ottcontent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants

@Entity(tableName = "platform")
@JsonClass(generateAdapter = true)
data class Platform(
    @PrimaryKey @Json(name = Constants.ID) var id: Int,
    @Json(name = Constants.PLATFORM_ID) var platformId: Int,
    @Json(name = Constants.COUNTRY_CODE) var countryCode: String,
    @Json(name = Constants.AVAILABILITY) var availability: String,
    @Json(name = Constants.ICON) var icon: String,
    @Json(name = Constants.LOCALIZED_NAME) var localizedName: String
)