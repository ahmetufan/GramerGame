package com.ahmet.gramer.service

import com.ahmet.gramer.models.KategoriModel
import com.ahmet.gramer.models.QuestionModel
import com.ahmet.gramer.models.TestModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ApiServiceRepository @Inject constructor(private val apiService: ApiService) {

    fun getHomeAPI():Single<KategoriModel> {
        return apiService.homeAPI()
    }

    fun getTestAPI(kategoriID : Int): Single<TestModel> {
        return apiService.testAPI(kategoriID)
    }

    fun getQuestionAPI(testID:Int): Single<QuestionModel> {
        return apiService.questionAPI(testID)
    }
}