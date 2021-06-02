package com.kaverikr.nationalflag.nationalflag.di

import com.kaverikr.nationalflag.nationalflag.network.ApiService
import com.kaverikr.nationalflag.nationalflag.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: ApiService)

    fun inject(viewModel: ListViewModel)
}