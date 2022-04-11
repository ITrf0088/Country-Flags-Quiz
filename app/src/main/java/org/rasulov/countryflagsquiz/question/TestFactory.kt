package org.rasulov.countryflagsquiz.question

import java.lang.IndexOutOfBoundsException

class TestFactory {
    companion object {
        private var count = 0


        fun hasNext(): Boolean = count < Country.items.size
        fun next(): Question {
            if (count < Country.items.size) {
                val question = Generator().generate(count)
                count++
                return question
            }
            throw IndexOutOfBoundsException("Test is ended")
        }
    }
}