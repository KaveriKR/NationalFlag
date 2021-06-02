package com.kaverikr.nationalflag.nationalflag.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaverikr.nationalflag.nationalflag.network.ApiService
import com.kaverikr.nationalflag.nationalflag.models.Country
import com.kaverikr.nationalflag.nationalflag.DaggerApiComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel :ViewModel(){

    @Inject
    lateinit var apiService : ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val list  = MutableLiveData<List<Country>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun refresh(){

        fetchList()
    }

    private fun fetchList() {

        loading.value = true
        disposable.add(apiService.getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(value: List<Country>?) {

                        list.value  = value
                        error.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {

                        error.value = true
                        loading.value = true
                    }


                }))



    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }


}