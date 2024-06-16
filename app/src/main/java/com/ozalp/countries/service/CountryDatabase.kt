package com.ozalp.countries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ozalp.countries.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDAO

    // Singleton

    companion object {

        @Volatile // farklı threadlere görünür hale getirir
        private var instance: CountryDatabase? = null

        private val lock = Any()

        //synchronized kullanınca farklı threadler aynı anda buraya ulaşamaz
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context)
                .also {// bunu yap ekstra şunu da yap demektir
                    instance = it
                }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "countrydatabase"
        ).build()
    }
}