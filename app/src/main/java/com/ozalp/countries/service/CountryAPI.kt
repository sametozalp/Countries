package com.ozalp.countries.service

import com.ozalp.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {
    //GET, POST

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>> // single neden ? bir defa yapar ve durur
}