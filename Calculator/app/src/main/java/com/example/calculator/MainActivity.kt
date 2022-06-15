package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var enterA: EditText
    private lateinit var enterB: EditText
    private lateinit var but1: Button
    private lateinit var but2: Button
    private lateinit var resul: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterA= findViewById(R.id.PtextEn)
        enterB= findViewById(R.id.PtextEn2)
        resul = findViewById(R.id.textRes)
        but1 = findViewById(R.id.bminus)
        but2 = findViewById(R.id.bplus)

        but1.setOnClickListener {
            if (enterA.text.isNullOrEmpty() || enterB.text.isNullOrEmpty()) resul.text = "error"
            else {
                val a: Int = enterA.text.toString().toInt()
                val b: Int = enterB.text.toString().toInt()
                val min = a - b
                resul.text = "$min"
            }
        }
        but2.setOnClickListener {
            if (enterA.text.isNullOrEmpty() || enterB.text.isNullOrEmpty()) resul.text = "error"
            else {
                val a: Int = enterA.text.toString().toInt()
                val b: Int = enterB.text.toString().toInt()
                val plus: Int = a + b
                resul.text = "$plus"
            }
        }
    }
}