package com.example.imcupgradedactivity.imcupgradedcalculator

//Importing all the required packages
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imcupgradedactivity.R
import com.example.imcupgradedactivity.imcupgradedcalculator.ImcUpgradedCalculatorActivity.Companion.IMC_KEY
import java.text.DecimalFormat

class ResultImcUpgradedActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView                  //We set the further access to the tvResult TextView component
    private lateinit var tvIMC: TextView                     //We set the further access to the tvIMC TextView component
    private lateinit var tvDescription: TextView             //We set the further access to the tvDescription TextView component
    private lateinit var btnRecalculate: Button              //We set the further access to the btnRecalculate Button component


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imc_upgraded)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0    //We receive the intent sent by the previous screen, and get the Double value we seted, ask for the IMC, and if its null, we just send -1.0
        initCompontents()  //Call the initComponents function
        initUI(result)     //Call the initIU function
        initListeners()    //Call the initListeners function

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initListeners() {                                 //This is the function for every component that is pressed
        btnRecalculate.setOnClickListener { onBackPressed() }    //Is the button for repeating the operation pressed? If it is, we call the onBackPressed function, that basically, does everything backwards
    }

    private fun initUI(result: Double) {                               //This is the function that will start the UI
        val df = DecimalFormat("#.##")                   //We just define a variable with a formatting instruction in it
        val formattedIMC = df.format(result)                    //we just define a variable with the format function, passing it to the result
        tvIMC.text = formattedIMC                               //We convert the IMC into a string, and pass the variable formattedIMC in it

        when (result) {
            in 0.00..18.50 -> {                                               //Low weight
                tvResult.text = getString(R.string.title_low_weight)
                tvDescription.text = getString(R.string.description_low_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.low_weight))
            }

            in 18.51..24.99 -> {                                              //Normal weight
                tvResult.text = getString(R.string.title_normal_weight)
                tvDescription.text = getString(R.string.description_normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
            }

            in 25.00..29.99 -> {                                              //Overweight
                tvResult.text = getString(R.string.title_overweight)
                tvDescription.text = getString(R.string.description_overweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.overweight))
            }

            in 30.00..99.00 -> {                                              //Obesity
                tvResult.text = getString(R.string.title_obesity)
                tvDescription.text = getString(R.string.description_obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
            }

            else -> {                                                               //Error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initCompontents() {                                     //This function will access to all components in the UI
        tvResult = findViewById(R.id.tvResult)               //Access to the tvResult TextView component
        tvIMC = findViewById(R.id.tvIMC)                     //Access to the tvIMC TextView component
        tvDescription = findViewById(R.id.tvDescription)     //Access to the tvDescription TextView component
        btnRecalculate = findViewById(R.id.btnRecalculate)   //Access to the btnRecalculate Button component
    }
}
