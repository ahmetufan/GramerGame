package com.ahmet.gramer.models

data class Kategori(
    val id: String,
    val name: String
)
data class KategoriModel(val kategori: List<Kategori>)



data class TestModel( val test: List<Test>)
data class Test(
    val id: String,
    val kategori_id: String,
    val test_name: String
)


data class QuestionModel(val question: List<Question>)
data class Question(
    val correct_ans: String,
    val id: String,
    val option_four: String,
    val option_one: String,
    val option_three: String,
    val option_two: String,
    val question: String,
    val test_id: String
)
