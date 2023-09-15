package com.example.lecture8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val button = findViewById<CorrectButton>(R.id.buttonView)
        val progressBar = findViewById<CorrectProgress>(R.id.progressBar)
        val textView = findViewById<CorrectTextView>(R.id.textView)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        val changeButton = findViewById<CorrectImageButton>(R.id.changeButton)

        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }


        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            viewModel.getJoke()
        }

        viewModel.observe(this) { state ->
            state.showView(progressBar, button, textView, changeButton)
        }
    }

}