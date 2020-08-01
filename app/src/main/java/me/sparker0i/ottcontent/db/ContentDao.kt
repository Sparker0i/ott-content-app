package me.sparker0i.ottcontent.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.sparker0i.ottcontent.model.Country

@Dao
interface ContentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertCountry(country: Country)

    @Query("SELECT * FROM COUNTRY") fun getCountries(): LiveData<List<Country>>
}