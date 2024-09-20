package com.example.androidmaster.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.example.androidmaster.todoapp.TodoActivity

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStart =
            findViewById<AppCompatButton>(R.id.btnStart)                 //Access the button component by its id
        val etName =
            findViewById<AppCompatEditText>(R.id.etName)                   //Access the editText component by its id

        val name =
            etName.text.toString()                                       //We convert the value of the editText component to String

        btnStart.setOnClickListener {                                               //Is the button clicked?
            if (name.isNotEmpty()) { //We check if the name is not empty
                val intent = Intent(
                    this,
                    ResultActivity::class.java
                ) //We create an intent to go to the ResultActivity
                intent.putExtra(
                    "EXTRA_NAME",
                    name
                )                           //We add the name to the intent
                startActivity(intent)                                               //We start the activity
            }
        }

        btnStart.setOnClickListener {                       //Is the button clicked?
            if (name.isNotEmpty()) {                        //We check if the name is not empty
                val intent = Intent(
                    this,
                    TodoActivity::class.java
                ) //We create an intent to go to the TodoActivity
                intent.putExtra(
                    "user_name",
                    name
                )                           //We add the name to the intent
                startActivity(intent)                                               //We start the activity
            }
        }
    }
}
