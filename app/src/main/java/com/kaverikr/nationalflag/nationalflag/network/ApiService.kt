package com.kaverikr.nationalflag.nationalflag.network

import com.kaverikr.nationalflag.nationalflag.DaggerApiComponent
import com.kaverikr.nationalflag.nationalflag.models.Country
import io.reactivex.Single
import javax.inject.Inject

class ApiService {


    @Inject
    lateinit var api: ApiFactory

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getList(): Single<List<Country>> {
        return api.getList()
    }


    
}