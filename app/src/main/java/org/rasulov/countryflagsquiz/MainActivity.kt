package org.rasulov.countryflagsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import kotlin.math.log10

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btn_start_quiz)
        val edtName = findViewById<AppCompatEditText>(R.id.edt_name)

        btnStart.setOnClickListener {
            edtName.text?.let {
                if (it.trim().isNotEmpty()) {
                    val intent = Intent(this, QuestionActivity::class.java)
                    intent.putExtra("name", it.trim().toString())
                    startActivity(intent)
                    finish()
                }
            }

        }
    }
}
