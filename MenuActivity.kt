package com.example.androidmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.firstapp.FirstAppActivity
import com.example.androidmaster.imccalculator.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnGreetApp = findViewById<Button>(R.id.GreetApp)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)

        btnIMCApp.setOnClickListener { navigateToIMCApp() }

        fun navigateToGreetApp() {
            val intent = Intent(this, FirstAppActivity::class.java)
            startActivity(intent)
        }

        btnGreetApp.setOnClickListener { navigateToGreetApp() }
    }

    private fun navigateToIMCApp() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}
