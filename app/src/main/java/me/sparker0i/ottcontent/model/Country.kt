package me.sparker0i.ottcontent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants

@Entity(tableName = "country")
@JsonClass(generateAdapter = true)
data class Country(
    @PrimaryKey @Json(name = Constants.CODE) var code: String,
    @Json(name = Constants.NAME) var name: String
)