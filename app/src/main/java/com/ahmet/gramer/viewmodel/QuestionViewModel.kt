package com.ahmet.gramer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmet.gramer.models.Question
import com.ahmet.gramer.models.QuestionModel
import com.ahmet.gramer.service.ApiServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: ApiServiceRepository):ViewModel(){

    private val compositeDisposable=CompositeDisposable()

    val questionLiveData=MutableLiveData<List<Question>>()

    fun getQuestionData(testID:Int) {
        getDataQuestionAPI(testID)
    }

    private fun getDataQuestionAPI(testID: Int) {

        compositeDisposable.add(repository.getQuestionAPI(testID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<QuestionModel>(){
                override fun onSuccess(t: QuestionModel) {
                    questionLiveData.value=t.question
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}