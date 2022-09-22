package com.ahmet.gramer.service

import com.ahmet.gramer.models.KategoriModel
import com.ahmet.gramer.models.QuestionModel
import com.ahmet.gramer.models.TestModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("eng_kategori.php")
    fun homeAPI(): Single<KategoriModel>

    //  http://www.adddisyon.online/  eng_test.php

    @GET("eng_test.php")
    fun testAPI(
        @Query("kategori_id") kategoriId: Int
    ): Single<TestModel>

    // http://www.adddisyon.online/  eng_new_question.php

    @GET("eng_new_question.php")
    fun questionAPI(
        @Query("test_id") testId: Int
    ): Single<QuestionModel>
}