package com.example.lecture8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.buttonView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        val changeButton = findViewById<ImageView>(R.id.changeButton)

        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }


        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }
        viewModel.init(object : DataCallback {
            override fun provideText(text: String) = runOnUiThread {
                button.isEnabled = true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }

            override fun provideIconRes(id: Int) = runOnUiThread {
                changeButton.setImageResource(id)
            }
        })

    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}