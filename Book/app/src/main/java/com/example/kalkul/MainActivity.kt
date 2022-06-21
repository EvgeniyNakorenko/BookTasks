package com.example.kalkul
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.math.abs
private  const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var lastButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var messText: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,true),
        Question(R.string.question_africa,true),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true))
    private var currentIndex = 0
    private var countAns = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider= ViewModelProviders.of(this)
        val quizViewModel=provider.get(QuizViewModel::class.java)
        Log.d(TAG,"Got a QuizViewModel")

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        lastButton = findViewById(R.id.last_button)
        questionTextView = findViewById(R.id.question_text_view)
        messText = findViewById(R.id.mess_text_view)
        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            unvis()
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            unvis()
        }
        nextButton.setOnClickListener {
            currentIndex = (abs(currentIndex - 1)) % questionBank.size
            updateQuestion()
            vis()
        }
        lastButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            vis()
        }
        updateQuestion()
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }
    private fun updateQuestion(){
        val questionTextResId= questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer=questionBank[currentIndex].answer
        val messageResId=if (userAnswer==correctAnswer){
            R.string.correct_toast
        } else R.string.incorrect_toast
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
        if (userAnswer==correctAnswer) countAns+=1
        val mess :Double=(countAns/6.0)*100
        if (currentIndex==5) messText.text="$mess %"
        else messText.text = ""
    }
    private fun unvis(){
        trueButton.isEnabled = false
        falseButton.isEnabled= false
    }
    private fun vis(){
        trueButton.isEnabled= true
        falseButton.isEnabled= true
    }
}