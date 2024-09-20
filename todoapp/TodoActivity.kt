package com.example.androidmaster.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
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

    private val categories = listOf(
        Personal,
        Business,
        Other
    )

    private val tasks = mutableListOf(
        Task(1, "Test_Personal", true, Personal),
        Task(2, "Test_Business", true, Business),
        Task(3, "Test_Other", true, Other)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var CategoriesAdapter: CategoriesAdapter
    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var fabAddTask: FloatingActionButton

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
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString().trim() // Trim leading/trailing whitespaces
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    getString(R.string.todo_dialog_category_business) -> Business
                    else -> Other
                }
                tasks.add(Task(tasks.size + 1, currentTask, false, currentCategory)) // Use unique id
                updateTasks()
                dialog.hide()
            }
        }
        dialog.show()
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        CategoriesAdapter = CategoriesAdapter(categories) { updateCategories(it) }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = CategoriesAdapter
        tasksAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun onItemSelected(position: Int) {
        var task = tasks[position].isSelected
        task = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        CategoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun updateTasks() {
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }
}