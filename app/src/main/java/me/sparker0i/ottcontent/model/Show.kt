package me.sparker0i.ottcontent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants

@Entity(tableName = "show")
@JsonClass(generateAdapter = true)
data class Show(
    @PrimaryKey @Json(name = Constants.ID) var id: Int,
    @Json(name = Constants.NAME) var name: String,
    @Json(name = Constants.DESCRIPTION) var description: String,
    @Json(name = Constants.TRAILER) var trailer: String,
    @Json(name = Constants.POSTER) var poster: String
)