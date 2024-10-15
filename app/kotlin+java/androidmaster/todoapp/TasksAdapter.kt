package com.example.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

//This is a class that takes two arguments: a list of task objects and a lambda function that is called when a task is selected
class TasksAdapter(var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit) :
    RecyclerView.Adapter<TasksViewHolder>() //This will receive a RecyclerView.Adapter that will receive a TasksViewHolder class
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder //This function will take two parameters: a ViewGroup and an Int
    {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false) // This will "inflate" the layout item_todo_task into a view
        return TasksViewHolder(view)  // This will return a new instance of TasksViewHolder with the view
    }

    override fun getItemCount() = tasks.size  // This function will return the size of the tasks list

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) // This function will bind the data to the ViewHolder
    {
        holder.render(tasks[position])       // This will call the render function of the TasksViewHolder with the current task
        holder.itemView.setOnClickListener() // This is a click listener for the item view:
        {
            val task = tasks[position]         // This will get the current task (as a position)
            task.isSelected = !task.isSelected // This will switch the state of the task (if is selected or not)
            onTaskSelected(position)           // This calls the onTaskSelected function and passes as a parameter the position of the task, which will be used to update the UI
            notifyItemChanged(position)        // We notify the adapter that the item has changed
        }
    }
}