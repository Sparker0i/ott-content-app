package me.sparker0i.ottcontent.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.model.Platform

@Dao
interface ContentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertCountry(country: Country)
    @Insert(onConflict = OnConflictStrategy.IGNORE) fun insertCountries(countries: List<Country>)
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertPlatforms(platforms: List<Platform>)

    @Query("DELETE FROM COUNTRY WHERE CODE NOT IN (:countries)") fun deleteNonExistingCountries(countries: List<String>)
    @Query("SELECT * FROM COUNTRY") fun getCountries(): LiveData<List<Country>>

    @Query("DELETE FROM PLATFORM WHERE COUNTRYCODE = :country AND PLATFORMID NOT IN (:platforms)") fun deleteNonExistingPlatformsForCountry(country: String, platforms: List<Int>)
    @Query("SELECT * FROM PLATFORM WHERE COUNTRYCODE = :country") fun getPlatforms(country: String): LiveData<List<Platform>>
}