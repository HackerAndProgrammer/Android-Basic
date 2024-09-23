package com.example.androidmaster.todoapp

sealed class TaskCategory(var isSelected: Boolean = true) {
    object Personal : TaskCategory() //Personal category object (TaskCategory type)
    object Business : TaskCategory() //Business category object (TaskCategory type)
    object Other : TaskCategory()    //Other category object (TaskCategory type)
}