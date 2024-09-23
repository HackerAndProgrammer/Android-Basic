package com.example.androidmaster.todoapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import com.example.androidmaster.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(       //This is are the types of categories we have
        Personal,
        Business,
        Other
    )

    private val tasks = mutableListOf<Task>()          //This are the tasks the user is going to start with (empty)

    private lateinit var rvCategories: RecyclerView
    private lateinit var CategoriesAdapter: CategoriesAdapter
    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var fabAddTask: FloatingActionButton

    private fun getUserName(): String {                                                 // For getting access to the name entered by the user in the FirstAppActivity
        val sharedPreferences = getSharedPreferences(     // Access the sharedPreferences file by calling the function that will do that for us
            "user_prefs",                           // This is the name of the file we are going to access
            MODE_PRIVATE                                  // This is the mode in which we are going to access the file
        )                                                 // Access to the sharedPreferences file (stored in the FirstAppActivity)
        return sharedPreferences.getString("userName", "")// Access to the name entered by the user in the FirstAppActivity
            .orEmpty()                                    // Return the name entered by the user
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListeners()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val userName =
            getUserName()                            // We get the name entered by the user and store it in a variable named userName
        val welcomeText =
            "Welcome, $userName"                  // Define the text we are going to print in the screen
        val tvWelcome =
            findViewById<TextView>(R.id.tvWelcome)  // Access to the Welcome TextView component
        tvWelcome.text =
            welcomeText                            // Set the text of the TextView (defined it in the welcomeText variable)

        // Print a message if there are NO TASKS AT ALL
        if (tasks.isEmpty()){                                            //Is the list if task empty?
            val tvNoTasks: TextView = findViewById(R.id.tvNoTasks) // Access to the TextView "No Tasks" component
            tvNoTasks.visibility = View.VISIBLE                    // Show the TextView "No Task" component (because, since there are no tasks, we tell the user: "look, create a task")
        }
    }

    private fun initListeners() {                                // All the listeners integrated in the activity:
        fabAddTask.setOnClickListener { showDialog() } // When the button for adding a new task is clicked, the showDialog function is called (
    }

    private fun showDialog() {                                   // This is the function for showing the dialog for adding a new Task

        val dialog = Dialog(this)                                       //Open the Dialog window
        dialog.setContentView(R.layout.dialog_task)                            // Access the dialog_task layout (The UI of this component)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)          // Access to the button for adding a new Task
        val etTask: EditText = dialog.findViewById(R.id.etTask)                // Access to the EditText for adding a new Task
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)  // Access to the RadioGroup (that contains all the RadioButtons for selecting the category of the new task)

        btnAddTask.setOnClickListener {                         // Is the button for Adding a new task clicked?
            val currentTask = etTask.text.toString().trim()                                     // Get the text entered by the user in the EditText for adding a new task
            if (currentTask.isNotEmpty()) {                                                     // Is the text entered by
                val selectedId = rgCategories.checkedRadioButtonId                              // Is the id of the CheckBox, once is checked
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)    // Is the id of the RadioButton, once is checked
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {           // When the text of the Selected Radio Button is:
                    getString(R.string.todo_dialog_category_personal) -> Personal                                                      // "Personal" then the category is Personal
                    getString(R.string.todo_dialog_category_business) -> Business                                                      // "Business" then the category is Business
                    else -> Other                                                               // Else, it HAS to be Other (because it's the last category)
                }
                tasks.add(                       //  We add to the tasks:
                    Task(
                        tasks.size + 1,       // We assign a unique id to the task, by incrementing the size of the task, so we can assign a different id to each new Task added by the user
                        currentTask,             // This is the name of the task entered by the user (currentTask)
                        false,         // Is the component selected? (false, because is the default value)
                        currentCategory         // Is the category of the Task added by the User)
                    )
                )
                updateTasks()                   // Call the function that updates the Tasks
                dialog.hide()                   // Hide the dialog (Once this is finished)
            }
        }
        dialog.show()                           // Show the dialog
    }

    private fun initComponent() {                              // This is the function that initializes all the UI components
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {                                                             //This is the function that access to all the UI components
        CategoriesAdapter = CategoriesAdapter(categories) { updateCategories(it) }                // This is function that access the CategoriesAdapter with the list of the categories, and the position of the selected item
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)  // This is the LinearLayoutManager, that will manage all the LinearLayout features: For example, the orientation of the LL
        rvCategories.adapter = CategoriesAdapter                                                  // We access to the CategoriesAdapter
        tasksAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }               // We access to the TasksAdapter class, with its two parameters:
        rvTasks.layoutManager = LinearLayoutManager(this)                                 //  We use the design manager for the Tasks RecyclerView
        rvTasks.adapter = tasksAdapter                                                            // We set the adapter of the RecyclerView equeal to the TasksAdapter class
    }

    private fun onItemSelected(position: Int) {           // This function will tell Android what item is selected (by its position)
        var task = tasks[position].isSelected     // We assign the value of the property isSelected to the actual task in the Tasks list. (basically, according the position, tell Android if it's selected or not)
        task = !tasks[position].isSelected        // We assign the opposite value of the property isSelected so we can change the color of the task
        updateTasks()                             // We call the function that updates the tasks
    }

    private fun updateCategories(position: Int) {                               // This is the function that updates the categories
        categories[position].isSelected = !categories[position].isSelected   // We assign the opposite value of the property isSelected so we can change the color of the category
        CategoriesAdapter.notifyItemChanged(position)                        // We notify the CategoriesAdapter that the item has changed
        updateTasks()                                                        // We call the function that updates the tasks
    }

    private fun updateTasks() {                                                                 //This is the function in charge of updating the tasks
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected } // We filter the categories that are selected (by the user)

        val hasTasks = tasks.any { selectedCategories.contains(it.category) }            // Check if there are any tasks after filtering

        val newTasks = tasks.filter { selectedCategories.contains(it.category) }         // We filter the tasks that are selected (by the user)
        tasksAdapter.tasks = newTasks                                                    // We update the task of the TaskAdapter class with the new tasks
        tasksAdapter.notifyDataSetChanged()                                              // We notify the TasksAdapter that the item has changed

        // Update the visibility of the "No Tasks" text based on the presence of tasks
        val tvNoTasks = findViewById<TextView>(R.id.tvNoTasks)
        tvNoTasks.visibility = if (hasTasks) View.GONE else View.VISIBLE
    }
}