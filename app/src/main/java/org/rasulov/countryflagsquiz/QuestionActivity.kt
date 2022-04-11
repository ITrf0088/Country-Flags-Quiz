package org.rasulov.countryflagsquiz

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.rasulov.countryflagsquiz.question.TestFactory

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val button = findViewById<Button>(R.id.test)
        button.setOnClickListener {
            if (TestFactory.hasNext()) {
                TestFactory.next()
            } else {
                Toast.makeText(this, "Is ended", Toast.LENGTH_SHORT).show()
            }
        }

    }
}