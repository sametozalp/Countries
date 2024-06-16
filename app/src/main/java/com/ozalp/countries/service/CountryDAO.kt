package com.ozalp.countries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ozalp.countries.model.Country

@Dao
interface CountryDAO {

    // Data Access Object
    // suspend coorotines için
    // vararg sayısının belli olmadığı durumlarda kullanılır

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long> // döndürdüğü long değeri primary key listesidir

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryID")
    suspend fun getCountry(countryID: Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

}