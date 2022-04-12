package org.rasulov.countryflagsquiz

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.rasulov.countryflagsquiz.question.Country
import org.rasulov.countryflagsquiz.question.Question
import org.rasulov.countryflagsquiz.question.TestFactory

class QuestionActivity : AppCompatActivity() {

    private var isFinished: Boolean = false
    private var canSubmit: Boolean = false
    private var imgFlag: ImageView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvsAnswers: MutableList<TextView> = mutableListOf()
    private var btnSubmit: Button? = null

    private var selected: Int = -1
    private var question: Question? = null
    private var testFactory: TestFactory = TestFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        testFactory = TestFactory()
        imgFlag = findViewById(R.id.img_flag)
        progressBar = findViewById(R.id.bar_progress)
        tvProgress = findViewById(R.id.tv_progress)

        tvsAnswers.add(findViewById<TextView>(R.id.tv_option_one).also {
            it.setOnClickListener {
                setAppearanceToTVs(0)
            }
        })
        tvsAnswers.add(findViewById<TextView>(R.id.tv_option_two).also {
            it.setOnClickListener {
                setAppearanceToTVs(1)
            }
        })
        tvsAnswers.add(findViewById<TextView>(R.id.tv_option_three).also {
            it.setOnClickListener {
                setAppearanceToTVs(2)
            }
        })
        tvsAnswers.add(findViewById<TextView>(R.id.tv_option_four).also {
            it.setOnClickListener {
                setAppearanceToTVs(3)
            }
        })
        btnSubmit =
            findViewById<Button>(R.id.btn_submit).also { it.setOnClickListener { click() } }

        if (savedInstanceState == null) {
            setQuestion()
            canSubmit = true
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        if (testFactory.hasNext()) {
            question = testFactory.next()

            question?.flag?.let { imgFlag?.setImageResource(it) }
            val i = testFactory.count
            progressBar?.progress = i
            tvProgress?.text = "$i / ${Country.size}"

            val answers = question?.answers ?: mutableListOf()
            tvsAnswers.forEachIndexed { index, it ->
                it.text = answers[index]
            }

        }

    }

    private fun setAppearanceToTVs(i: Int) {
        if (canSubmit && !isFinished) {
            selected = i
            tvsAnswers.forEachIndexed { index, it ->
                if (index != i) {
                    it.typeface = Typeface.DEFAULT
                    it.background = ContextCompat.getDrawable(this, R.drawable.option_border_bg)
                    it.setTextColor(ContextCompat.getColor(this, R.color.blue_dark))
                } else {
                    it.typeface = Typeface.DEFAULT_BOLD
                    it.background =
                        ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
                }
            }
        }

    }

    private fun click() {
        if (isFinished) return
        if (canSubmit) {
            submit()
        } else {
            next()
        }
    }

    private fun submit() {
        if (selected != -1) {
            checkAnswer()
            canSubmit = false
            if (!testFactory.hasNext()) {
                btnSubmit?.text = "finish"
                isFinished = true
            } else {
                btnSubmit?.text = "next"
            }
        }
    }

    private fun next() {
        btnSubmit?.text = "Submit"
        canSubmit = true
        setAppearanceToTVs(-1)
        setQuestion()
    }

    private fun checkAnswer() {
        if (selected != question?.rightIndex) {
            tvsAnswers[selected].background =
                ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
            tvsAnswers[selected].setTextColor(ContextCompat.getColor(this, R.color.white))
        }
        question?.rightIndex?.let {
            tvsAnswers[it].background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
            tvsAnswers[it].setTextColor(ContextCompat.getColor(this, R.color.white))
        }

    }


}