package com.myapp.millionaire.model

data class Question(
    var question: String? = null,
    var content: ArrayList<String>? = null,
    var correct: Int = 0,
)
