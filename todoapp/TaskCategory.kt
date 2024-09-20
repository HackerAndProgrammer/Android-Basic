package com.example.androidmaster.todoapp

import androidx.recyclerview.widget.RecyclerView

sealed class TaskCategory(var isSelected: Boolean = true) {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}