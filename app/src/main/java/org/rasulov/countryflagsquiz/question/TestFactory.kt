package org.rasulov.countryflagsquiz.question

import java.lang.IndexOutOfBoundsException

class TestFactory {

    var count = 0
        private set

    fun hasNext(): Boolean = count < Country.size

    fun next(): Question {
        if (count < Country.size) {
            val question = Generator().generate(count)
            count++
            return question
        }
        throw IndexOutOfBoundsException("Test is ended")
    }

}