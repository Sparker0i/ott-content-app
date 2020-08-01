package me.sparker0i.ottcontent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.sparker0i.ottcontent.utils.Constants
import java.util.*

@Entity(tableName = "episode")
@JsonClass(generateAdapter = true)
data class Episode(
    @PrimaryKey @Json(name = Constants.ID) var id: Int,
    @Json(name = Constants.SHOW_ID) var showId: Int,
    @Json(name = Constants.SEASON_NUM) var seasonNum: Int,
    @Json(name = Constants.EPISODE_NUM) var episodeNum: Int,
    @Json(name = Constants.NAME) var name: String,
    @Json(name = Constants.DESCRIPTION) var description: String,
    @Json(name = Constants.AIR_DATE) var airDate: Date,
    @Json(name = Constants.TRAILER) var trailer: String,
    @Json(name = Constants.POSTER) var poster: String
)