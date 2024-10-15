package com.example.androidmaster.imccalculator

//Importing all the required packages
import android.content.Intent
import java.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean =
        true                        //We set the isMaleSelected variable to true
    private var isFemaleSelected: Boolean =
        false                     //We set the isFemaleSelected variable to false
    private var currentWeight: Int =
        55                               //We set a default weight (The user will change it)
    private var currentAge: Int =
        30                                  //We set a default age (The user will change it)
    private var currentHeight: Int =
        120                              //We set a default height (The user will change it)

    //Accessing to all the components in the UI
    private lateinit var viewMale: CardView                           //We set the further access to the viewMale CardView component
    private lateinit var viewFemale: CardView                         //We set the further access to the viewFemale CardView component
    private lateinit var tvHeight: TextView                           //We set the further access to the tvHeight TextView component
    private lateinit var rsHeight: RangeSlider                        //We set the further access to the rsHeight RangeSlider component (For the Height)
    private lateinit var btnSubtractWeight: FloatingActionButton      //We set the further access to the btnSubtractWeight (The button for weight subtraction)
    private lateinit var btnPlusWeight: FloatingActionButton          //We set the further access to the btnPlusWeight (The button for weight adding)
    private lateinit var tvWeight: TextView                           //We set the further access to the tvWeight TextView (The text that will display the weight entered by the user)
    private lateinit var tvAge: TextView                              //We set the further access to the tvAge TextView (The text that will display the age entered by the user)
    private lateinit var btnSubtractAge: FloatingActionButton         //We set the further access to the btnSubtractAge (The button for age subtraction)
    private lateinit var btnPlusAge: FloatingActionButton             //We set the further access to the btnPlusAge (The button for age adding)
    private lateinit var btnCalculate: Button                         //We set the further access to the btnCalculate (The button that will calculate the IMC, and send the result to the next screen)

    companion object {                                                //We create a companion object so it will be an universal object:
        const val IMC_KEY =
            "IMC_RESULT"                              //We set the IMC_KEY, so we can make the screen shifting
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponents()   //The initComponents function (that will access to all components in the UI) call
        initListeners()    //The initListeners function (that will set all the conditions in our app) call
        initUI()           //The initUI function call

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents() {
        viewMale =
            findViewById(R.id.viewMale)                            //We access the viewMale CardView component
        viewFemale =
            findViewById(R.id.viewFemale)                        //We access the viewFemale CardView component
        tvHeight =
            findViewById(R.id.tvHeight)                            //We access the tvHeight TextView component
        rsHeight =
            findViewById(R.id.rsheight)                            //We access the rsHeight RangeSlider component
        btnSubtractWeight =
            findViewById(R.id.btnSubtractWeight)          //We access the btnSubtractWeight Button component
        btnPlusWeight =
            findViewById(R.id.btnPlusWeight)                  //We access the btnPlusWeight Button component
        tvWeight =
            findViewById(R.id.tvWeight)                            //We access the tvWeight TextView component
        tvAge =
            findViewById(R.id.tvAge)                                  //We access the tvAge TextView component
        btnSubtractAge =
            findViewById(R.id.btnSubtractAge)                //We access the btnSubtractAge component
        btnPlusAge =
            findViewById(R.id.btnPlusAge)                        //We access the btnPlusAge Button component
        btnCalculate =
            findViewById(R.id.btnCalculate)                    //We access the btnCalculate Button component
    }

    private fun initListeners() {
        viewMale.setOnClickListener {                                     //Is the viewMale cardView pressed?
            changeGender()     //We call the changeGender function
            setGenderColor()   //We call the setGenderColor function
        }
        viewFemale.setOnClickListener {                                   //is the viewFemale cardView pressed?
            changeGender()    //We call the changeGender function
            setGenderColor()  //We call the setGenderColor function
        }
        rsHeight.addOnChangeListener { _, value, _ ->                     //Update the value entered by the user once he/she clicked the RangeSlider
            val df = DecimalFormat("#.##")    //We set a variable with a format function in it
            currentHeight = df.format(value)
                .toInt() //We set the currentHeight variable with the formatting function, passing it to the result (entered by the user)
            tvHeight.text =
                "$currentHeight cm"      //We set a tvHeight variable as text, and assign it the actual height value
        }
        btnSubtractWeight.setOnClickListener {                           //Is the button for weight subtraction pressed?
            currentWeight -= 1   //We subtract 1 to the current weight
            setWeight()          //We call the setWeight function
        }
        btnPlusWeight.setOnClickListener {                               //Is the button for weight adding pressed?
            currentWeight += 1   //We add 1 to the current weight
            setWeight()          //We call the setWeight function
        }
        btnSubtractAge.setOnClickListener {                             //Is the button for age subtraction pressed?
            currentAge -= 1      //We subtract 1 to the current age
            setAge()             //We call the setAge function
        }
        btnPlusAge.setOnClickListener {                                 //Is the button for age adding pressed?
            currentAge += 1      //We add 1 to the current age
            setAge()             //We call the setAge function
        }
        btnCalculate.setOnClickListener {                               //Is the button for the IMC calculation pressed?
            val result = calculateIMC() //We set the result variable with the function for the IMC calculating
            navigateToResult(result)    //We call the navigateToResult (that will send the user to the next screen) function with the IMC already calculated as a parameter
        }
    }

    private fun navigateToResult(result: Double) {    //This is the function that will send the user to the next screen
        val intent = Intent(this, ResultIMCActivity::class.java)    //We set an Intent so that the next screen will catch it
        intent.putExtra(IMC_KEY, result)                                          //We add the IMC_KEY, and the IMC already calculated to the Intent
        startActivity(intent)                                                     //We start the Activity with the Intent in it
    }

    private fun calculateIMC(): Double {              //This is the function for the IMC calculation
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)  //This is the formula, stored in an imc variable
        return imc    //We return the number (Which is a Double) calculated by the formula
    }

    private fun setWeight() {                         //This is the function for the weight setting
        tvWeight.text = currentWeight.toString() //We convert the tvWeight to text so we convert the actual weight into a text, stored in the variable.
    }

    private fun setAge() {                           //This is the function for the age setting
        tvAge.text = currentAge.toString()       //We convert the tvAge to text so we convert the actual age into a text, stored in the variable.
    }

    private fun changeGender() {                     //This is the function for selecting the gender
        if (viewMale.isPressed) {                    //Is the user a boy?
            isMaleSelected = true     //Male color is selected
            isFemaleSelected = false  //Female color is waiting to be selected
        } else if (viewFemale.isPressed) {           //is the user a girl?
            isMaleSelected = false    //Male color is waiting to be selected
            isFemaleSelected = true   //Female color is waiting to be selected
        }
    }

    private fun setGenderColor() {                   ////This is the function for changing the color according the genDer selected by the user.

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))     //We change the background color of the male component (Only if Male is selected)
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected)) //We change the background color of the female component (Only if Female is selected)
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {         //We set the color of any component, only if its selected

        val colorReference = if (isSelectedComponent) {    //We ask: is the component selected? And the result, we store it in a colorReference variable.
            R.color.background_component_selected   //We change the color of the component
        } else {                                           //if is NOT selected:
            R.color.background_component            //We just live the component as it is
        }

        return ContextCompat.getColor(this, colorReference)   //This will change the color according the ColorReference
    }

    private fun initUI() {                          //We set the UI
        setGenderColor()  //Call the setGenderColor function
        setWeight()       //Call the setWeight function
        setAge()          //call the setAge function
    }
}