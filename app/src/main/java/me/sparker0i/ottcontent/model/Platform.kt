package me.sparker0i.ottcontent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants

@Entity(tableName = "platform")
@JsonClass(generateAdapter = true)
data class Platform(
    @PrimaryKey @Json(name = Constants.PLATFORM_ID) var platformId: Int,
    @Json(name = Constants.LOCALIZED_NAME) var localizedName: String
)