package com.ahmet.gramer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmet.gramer.models.TestModel
import com.ahmet.gramer.service.ApiServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GramerViewModel @Inject constructor(private val repository: ApiServiceRepository) : ViewModel(){

    private val compositeDisposable=CompositeDisposable()

    val gramerLiveData=MutableLiveData<TestModel>()

    fun getGramerData(kategoriID: Int) {
        getDataGramerAPI(kategoriID)
    }

    private fun getDataGramerAPI(kategoriID:Int) {

        compositeDisposable.add(
            repository.getTestAPI(kategoriID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TestModel>() {
                    override fun onSuccess(t: TestModel) {
                        gramerLiveData.value=t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}