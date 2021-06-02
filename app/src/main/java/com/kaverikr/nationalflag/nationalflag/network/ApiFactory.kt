package com.kaverikr.nationalflag.nationalflag.network

import com.kaverikr.nationalflag.nationalflag.models.Country
import io.reactivex.Single
import retrofit2.http.GET

interface ApiFactory {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getList(): Single<List<Country>>
}