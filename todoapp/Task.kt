package com.example.androidmaster.todoapp

data class Task(
    val id: Int,  //Unic task id
    val name: String, //Task name
    var isSelected: Boolean, //Checks if is selected (true) or not (false)
    val category: TaskCategory //Category of the Task
)