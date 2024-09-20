package com.example.androidmaster.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)
    fun render(task: Task) {
        cbTask.setOnCheckedChangeListener { _, isChecked ->
            task.isSelected = isChecked
            if (isChecked) {
                tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        tvTask.text = task.name
        cbTask.isChecked = task.isSelected

        val color = when (task.category) {
            TaskCategory.Personal -> R.color.personal_category
            TaskCategory.Business -> R.color.business_category
            TaskCategory.Other -> R.color.other_category
        }

        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )
    }
}