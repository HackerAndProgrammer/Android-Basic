package com.example.androidmaster.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disable
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when (taskCategory) {
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }

            TaskCategory.Business -> {
                tvCategoryName.text = "Business"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.business_category)
                )
            }

            TaskCategory.Other -> {
                tvCategoryName.text = "Other"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.other_category)
                )
            }
        }
    }
}