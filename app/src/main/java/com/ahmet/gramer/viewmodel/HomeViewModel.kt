package com.ahmet.gramer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmet.gramer.models.KategoriModel
import com.ahmet.gramer.service.ApiServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ApiServiceRepository) :ViewModel() {

    private val compositeDisposable=CompositeDisposable()

    val homeLiveData= MutableLiveData<KategoriModel>()

    fun getHomeData(){
        getDataHomeAPI()
    }

    private fun getDataHomeAPI() {

        compositeDisposable.add(
            repository.getHomeAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<KategoriModel>(){
                    override fun onSuccess(t: KategoriModel) {
                        homeLiveData.value=t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}