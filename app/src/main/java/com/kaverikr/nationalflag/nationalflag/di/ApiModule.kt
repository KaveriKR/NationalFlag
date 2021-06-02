package com.kaverikr.nationalflag.nationalflag.di

import com.kaverikr.nationalflag.nationalflag.network.ApiFactory
import com.kaverikr.nationalflag.nationalflag.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com"


    @Provides
    fun provideApi (): ApiFactory {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiFactory::class.java)
    }

    @Provides
    fun provideApiAervice(): ApiService {
        return ApiService()
    }



}