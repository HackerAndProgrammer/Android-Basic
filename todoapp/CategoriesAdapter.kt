package com.example.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

// This is the class that we will initialize with two parameters: List of tasks categories, and the item selected (The position, as a Lambda function)
class CategoriesAdapter(
    private val categories: List<TaskCategory>,
    private val onItemSelected: (Int) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false) // This will inflate the layout item_task_category.xml
        return CategoriesViewHolder(view)                 //This will return the view holder with the view already inflated
    }

    override fun getItemCount() = categories.size        // This will return the size of the list of categories

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {  // This will bind the view holder with the data
        holder.render(categories[position], onItemSelected)               // This will render the view holder with the data of the list of categories
    }
}