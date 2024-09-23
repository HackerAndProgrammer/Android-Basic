package com.example.androidmaster.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

// This is the class that will create the view holder for the recycler view
class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Access to the views in the layout
    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    // This function will bind the data to the views
    fun render(task: Task) {
        cbTask.setOnCheckedChangeListener { _, isChecked ->                                      // Is the checkbox checked?
            task.isSelected = isChecked            // Set the task as checked

            if (isChecked) {   // Is the task checked?
                tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG        // Add the strike through text
            } else {           // If is not checked:
                tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv() // Remove the strike
            }
        }

        // Set the text of the task and the state of the checkbox
        tvTask.text = task.name
        cbTask.isChecked = task.isSelected

        // Set the color of the checkbox based on the category of the task
        val color = when (task.category) {
            TaskCategory.Personal -> R.color.personal_category
            TaskCategory.Business -> R.color.business_category
            TaskCategory.Other -> R.color.other_category
        }

        // Set the tint of the checkbox based on the color of the category
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )
    }
}