package com.example.androidmaster.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

//This class will get the View of the item and return a ViewHolder
class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Access to the UI components of the item
    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    //This function will render the UI of the item
    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        // We set the color by asking if the category is selected or not
        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disable
        }

        // We set the background color of the card
        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        // Once the user clicks on the card, we call the function onItemSelected for the position of the card
        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        // Set the color of the card's divider, and the text of the category name according to the category
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