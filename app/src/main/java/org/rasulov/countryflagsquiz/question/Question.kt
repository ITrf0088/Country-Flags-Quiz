package org.rasulov.countryflagsquiz.question

data class Question(
    val flag: Int,
    val rightIndex: Int,
    val answers: List<String>
)