package org.rasulov.countryflagsquiz.question

import android.util.Log

class Generator {

    private val answers: MutableList<String> = MutableList(4) { it.toString() }
    private val rightIndex = (Math.random() * 4).toInt()
    private var count = 0

    fun generate(index: Int): Question {
        val country = Country.items[index]

        val flag = country.second
        val name = country.first
        setAnswers(index)
        answers[rightIndex] = name
        Log.d("it0088", "rightIndex: $rightIndex")
        answers.forEach { Log.d("it0088", "$it ") }
        return Question(flag, rightIndex, answers)
    }

    private fun setAnswers(index: Int) {
        up(index + 1)
        down(index - 1)
    }

    private fun up(index: Int) {
        if (count < 4 && index < Country.size) {
            if (count != rightIndex) {
                answers[count] = Country.items[index].first
            }
            count++
            up(index + 1)
        }
    }

    private fun down(index: Int) {
        if (count < 4 && index >= 0) {
            if (count != rightIndex) {
                answers[count] = Country.items[index].first
            }
            count++
            down(index - 1)
        }
    }


}