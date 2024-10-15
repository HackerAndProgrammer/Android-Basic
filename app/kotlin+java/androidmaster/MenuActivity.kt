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
import com.example.androidmaster.settings.SettingsActivity
import com.example.androidmaster.superheroapp.SuperHeroListActivity
import com.example.androidmaster.todoapp.TodoActivity

// This is the main activity of the entire Android app, it contains all the buttons to navigate to the other apps
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

        // Find the buttons in the layout
        val btnGreetApp = findViewById<Button>(R.id.GreetApp)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        val btnTODOApp = findViewById<Button>(R.id.btnTODO)
        val btnSuperHero = findViewById<Button>(R.id.btnSuperHero)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        // These are all the listeners, when some button is clicked, it will call the corresponding function
        btnGreetApp.setOnClickListener { navigateToGreetApp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnTODOApp.setOnClickListener { navigateToTODOApp() }
        btnSuperHero.setOnClickListener { navigateToSuperHeroApp() }
        btnSettings.setOnClickListener { navigateToSettings() }
    }

    // This is the function that will transport the user to the Settings
    private fun navigateToSettings() {
        // Set the intent with the target to the Settings app, and launch it
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    // This is the function that will transport the user to the SuperHeroApp
    private fun navigateToSuperHeroApp() {
        // Set the intent with the target to the SuperHeroApp, and launch it
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    // This is the function that will transport the user to the GreetApp
    private fun navigateToGreetApp() {
        // Set the intent with the target to the GreetApp, and launch it
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    // This is the function that will transport the user to the TODOApp
    private fun navigateToTODOApp() {
        // Set the intent with the target to the TODOApp, and launch it
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    // This is the function that will transport the user to the IMCApp
    private fun navigateToIMCApp() {
        // Set the intent with the target to the IMCApp, and launch it
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}
